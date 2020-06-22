package org.stockwatcher.server.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
@PropertySource(value = { "classpath:application.conf" })
@EnableCassandraRepositories(basePackages = "org.stockwatcher.server.repository")
public class DatabaseConfig {

	@SuppressWarnings("unused")
	private static final Log LOGGER = LogFactory.getLog(DatabaseConfig.class);

	@Value("${cassandra.keyspaceName}")
	public String keyspaceName;

	@Bean
	public CqlIdentifier keyspace() {
		return CqlIdentifier.fromCql(keyspaceName);
	}

	@Bean
	public CqlSession cqlSession() {
			return CqlSession.builder().build();
	}

	public String getKeyspaceName() {
			return keyspaceName;
	}

	public void setKeyspaceName(String keyspaceName) {
			this.keyspaceName = keyspaceName;
	}

}
