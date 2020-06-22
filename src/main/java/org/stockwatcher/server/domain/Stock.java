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

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvDate;

import lombok.Data;

/**
 * A domain class that represents a stock that is traded on an exchange. 
 * Stocks are identified by their symbol. The source of this data is the 
 * Yahoo Finance site (http://finance.yahoo.com).
 * 
 * @author Bruno Guedes
 */
@Table
@Data
public class Stock {
	
	@PrimaryKeyColumn(name = "stock_symbol", 
			type = PrimaryKeyType.PARTITIONED)	
	private String symbol;
	
	@Column("company_name")
	private String companyName;
	
	@Column("exchange_id")
	private String exchangeId;
	
	@Column("industry_id")
	private Integer industryId;
	
	@Column("industry_name")
	private String industryName;
	
	@Column("sector_id")
	private Integer sectorId;
	
	@Column("sector_name")
	private String sectorName;	
	
	@Column("current_price")
	private transient BigDecimal currentPrice;
	
	@Column("price_updated")
	@CsvDate(value = "yyyy-MM-dd hh:mm-ssZ")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private transient Date priceUpdated;
	
	@Column
	private boolean active;
}