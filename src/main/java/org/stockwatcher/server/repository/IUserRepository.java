package org.stockwatcher.server.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import org.stockwatcher.server.domain.User;

@Repository
public interface IUserRepository extends CassandraRepository<User, UUID> {

}
