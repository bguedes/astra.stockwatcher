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

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

/**
 * A domain class that represents a service where investors can buy and sell 
 * shares of listed stocks. Each stock is traded on a single exchange.
 * 
 * @author Bruno Guedes
 */
@Table
@Data
public class Exchange {
	
	@PrimaryKeyColumn(name = "exchange_id", type = PrimaryKeyType.PARTITIONED)
	private String exchangeId;
	
	@Column("exchange_name")
	private String name;
	
	@Column("currency_code")
	private String currency;
	
	private boolean active;
}