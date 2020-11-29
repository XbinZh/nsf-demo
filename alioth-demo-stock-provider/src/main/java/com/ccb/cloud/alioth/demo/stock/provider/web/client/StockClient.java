package com.ccb.cloud.alioth.demo.stock.provider.web.client;

import java.util.List;

import com.ccb.cloud.alioth.demo.stock.provider.web.entity.Stock;

public interface StockClient {

	public Stock getStockById(String stockId) throws Exception;
	
	public List<Stock> getStockBatchByIds(String stockIds) throws Exception;
	
}
