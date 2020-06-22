package org.stockwatcher.server.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.stockwatcher.server.domain.Stock;

public interface IStockRepository extends CassandraRepository<Stock, String> {

}
