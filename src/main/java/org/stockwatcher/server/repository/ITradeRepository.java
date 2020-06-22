package org.stockwatcher.server.repository;

import java.util.Date;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.stockwatcher.server.domain.Trade;

@Repository
public interface ITradeRepository extends CassandraRepository<Trade, Trade.TradePrimaryKey> {

	public Trade findByKeyStockSymbolAndKeyTradeDate(String stockSymbol, Date tradeDate);
}
