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
 * A domain class that represents a user of the StockWatcher application. 
 * Users are identified by their email address.
 * 
 * @author Bruno Guedes
 */
@Table
@Data
public class User {
	
	@PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.PARTITIONED)
	private UUID id;
	
	@Column("first_name")
	private String firstName;
	
	@Column("last_name")
	private String lastName;
	
	@Column("display_name")
	private String displayName;
	
	@Column("email_address")
	private String emailAddress;
	
	@Column("postal_code")
	private String postalCode;
	
	private Date created;
	
	@Column
	private boolean active;
	
	@Column
	private Date updated;
	
	@Transient
	private transient int watchListCount;

}