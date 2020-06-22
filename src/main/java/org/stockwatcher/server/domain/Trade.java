/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.stockwatcher.server.domain;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NonNull;

/**
 * A domain class that represents a transaction on a stock exchange. 
 * 
 * @author Bruno Guedes
 */
@Table(value = "Trade")
@Data
public class Trade {
	
	@PrimaryKeyClass
	@Data
	public static class TradePrimaryKey implements Serializable {

		private static final long serialVersionUID = -7688615149329617276L;

		@NonNull
		@CassandraType(type = Name.TEXT)
		@PrimaryKeyColumn(name = "stock_symbol", ordinal = 0, type = PARTITIONED)
		private String stockSymbol;

		@NonNull
		@CassandraType(type = Name.TIMESTAMP)
		@PrimaryKeyColumn(name = "trade_date", ordinal = 1, type = PARTITIONED)
		private Date tradeDate;
		
		@CassandraType(type = Name.TIMESTAMP)
		@PrimaryKeyColumn(name = "trade_timestamp", ordinal = 2, type = CLUSTERED)
		private Date tradeTimestamp;

		@CassandraType(type = Name.TIMEUUID)
		@PrimaryKeyColumn(name = "trade_id", ordinal = 2, type = CLUSTERED)
		//UUIDs.timeBased();
		private UUID tradeId;		
	}	
	
	
	@PrimaryKey
	private Trade.TradePrimaryKey key;
	
	@Column("exchange_id")
	private String exchangeId;
	
	@Column("share_price")
	private BigDecimal sharePrice;
	
	@Column("share_quantity")
	private int shareQuantity;    
}