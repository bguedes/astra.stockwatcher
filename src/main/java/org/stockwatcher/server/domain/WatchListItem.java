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

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

/**
 * A domain class that represents a stock on a specific watch list. Each watch
 * list item captures the stock symbol and current price at the time it is 
 * added to the watch list. Users can add/remove watch list items from their own watch lists.
 * 
 * @author Bruno Guedes
 */
@Table
@Data
public class WatchListItem {
	
	@PrimaryKeyColumn(name = "watchlist_id", type = PrimaryKeyType.PARTITIONED)	
	private UUID watchListId;
	
	@Column("start_price")	
	private BigDecimal startPrice;
	
	@Column("stock_symbol")		
	private String stockSymbol;
	
	private Date created;
	
}