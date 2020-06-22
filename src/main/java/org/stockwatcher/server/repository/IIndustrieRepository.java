package org.stockwatcher.server.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.stockwatcher.server.domain.Industry;

public interface IIndustrieRepository extends CassandraRepository<Industry, Integer> {

}
