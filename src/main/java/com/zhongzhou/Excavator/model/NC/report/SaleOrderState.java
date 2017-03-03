package com.zhongzhou.Excavator.model.NC.report;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;


/**
 * User for data select from NC DB as report
 * @author zhanghuanping
 *
 */
public class SaleOrderState {
	
	/* For system */
	public boolean isTheLastVersion;
	
	public long version;
	
	public long lastVersion;
	
	public List<String> changed; 
	/* For system */
	
	private String lastUpdateTime;
	public String getLastUpdateTime() {
		
		this.lastUpdateTime = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date(version));
		return this.lastUpdateTime;
	}

	/** 销售订单号*/
	public String orderId;
	
	/** 客户订单号*/
	public String customerOrderId;
	
	/** 订单时间*/
	public String orderTime;
	
	
	public String invoiceCode;
	
	
	/** 客户名字*/
	public String customerName;
	
	/** 客户采购员 */
	public String customerBuyer;
	
	/** 价格条款 */
	public String tradeTerm;
	
	/** 业务员 */
	public String businessUser;
	
	public List<SaleOrderItemState> items;
}
