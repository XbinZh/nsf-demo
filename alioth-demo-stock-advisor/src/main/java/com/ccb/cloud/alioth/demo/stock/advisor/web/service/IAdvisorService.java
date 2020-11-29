package com.ccb.cloud.alioth.demo.stock.advisor.web.service;


import java.util.List;

import com.ccb.cloud.alioth.demo.stock.advisor.web.entity.Stock;

import javax.servlet.http.HttpServletRequest;

public interface IAdvisorService {
	public List<Stock> getHotStocks() throws Exception;
	
	public List<String> batchHi();
	
	public String deepInvoke(int times);

	public String echoProvider(HttpServletRequest request);
}
