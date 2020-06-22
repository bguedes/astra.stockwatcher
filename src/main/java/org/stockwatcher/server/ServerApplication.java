package org.stockwatcher.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.stockwatcher.server.domain.Industry;
import org.stockwatcher.server.domain.Stock;
import org.stockwatcher.server.repository.IIndustrieRepository;
import org.stockwatcher.server.repository.IStockRepository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import io.micrometer.core.instrument.util.IOUtils;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(ServerApplication.class);	
	
	@Autowired
	private IStockRepository stockRepository;	
	
	@Autowired
	private IIndustrieRepository industrieRepository;	
	
	@Autowired
	private CqlSession dseSession;
	
    @Value("classpath:cql/stockwatcher.cql")
    private Resource cqlImport;		
	
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		loadDataUsingCQL();
		databaseDataLoading();
	}
			
	private void databaseDataLoading() {
		loadStocks();
		loadIndustries();
	}
	
	private void loadStocks() {
		List<Stock> stocks = loadObjectList(Stock.class, "csv/stocks.csv");
				
		for (Stock stock : stocks) {
			stockRepository.save(stock);
		}
	}
	
	private void loadIndustries() {
		List<Industry> industries = loadObjectList(Industry.class, "csv/industries.csv");
		
		for (Industry industrie : industries) {
			industrieRepository.save(industrie);
		}
	}	
	
		
	private <T> List<T> loadObjectList(Class<T> type, String fileName) {
		try {
			CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
			CsvMapper mapper = new CsvMapper();
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<T> readValues = mapper
					.readerFor(type)
					.with(bootstrapSchema)
					.readValues(file);
			return readValues.readAll();
		} catch (Exception e) {
			LOGGER.error("Error occurred while loading object list from file " + fileName, e);
			return Collections.emptyList();
		}
	}	
	
	private void loadDataUsingCQL() {
		
        List<String> cqlScripts = new ArrayList<>();

        try(InputStream is = cqlImport.getInputStream()) {
            String string =  IOUtils.toString(is);
            cqlScripts = Arrays.asList(string.split(";"));
        } catch (IOException e) {
            e.printStackTrace();
        }		
        
        for (String cqlScript : cqlScripts) {
        	dseSession.execute(cqlScript);
		}
	}	
}
