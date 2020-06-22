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

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

/**
 * A domain class that represents a list of stocks being watched by a user. 
 * Users of the StockWatcher application can create/destroy their own watch 
 * lists and add/remove stocks (represented by WatchListItems) from them.
 * 
 * @author Bruno Guedes
 */
@Table
@Data
public class WatchList {
	
	public enum Visibility { PRIVATE, PROTECTED, PUBLIC }

	@PrimaryKeyColumn(name = "watchlist_id", type = PrimaryKeyType.PARTITIONED)
	private UUID id;
	
	@Column("user_id")	
	private UUID userId;
	
	@Column("display_name")	
	private String displayName;
		
	//private Visibility visibility = Visibility.PRIVATE;
	private int visibility;
		
	private Date created;
		
	private Date updated;
		
	private boolean active = true;
	
	@Transient
	private transient int itemCount;

}