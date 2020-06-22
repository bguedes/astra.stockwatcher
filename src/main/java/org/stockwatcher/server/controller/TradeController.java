package org.stockwatcher.server.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.stockwatcher.server.domain.Trade;
import org.stockwatcher.server.repository.ITradeRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/trades")
public class TradeController {

	@Autowired
	private ITradeRepository tradeRepository;

	@ApiOperation(value = "Get the default schedule for employees", notes = "All employees share the same schedule.")
	@RequestMapping(value = "/trade", method = RequestMethod.GET)
	@ResponseBody
	public Trade getTradeByStockSymbolAndTradeDate(
			@RequestParam String stockSymbol,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date tradeDate) {
		
		return tradeRepository.findByKeyStockSymbolAndKeyTradeDate(
				stockSymbol, 
				tradeDate);
	}

	@PostMapping("/trade")
	@ApiOperation(value = "Add a Trade", notes = "Add a Trade, please use the right Json body.")
	public Trade create(@RequestBody Trade newTrade) {
		return tradeRepository.save(newTrade);
	}
}
