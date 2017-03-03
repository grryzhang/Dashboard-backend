package com.zhongzhou.Excavator.model.NC.report;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;

/**
 * User for data select from NC DB as report
 * @author zhanghuanping
 *
 */
@Entity( value="BIReports" )
public class SaleOrderItemState {
	
	/** 销售订单号*/
	public String orderId;
	
	/** 客户订单号*/
	public String customerOrderId;
	
	public String code;
	
	public String type;
	
	public String name;
	
	public String specification;
	
	
	public Float totalNumber;
	
	public String unit;
	
	public Float unitPrice;
	
	public Float totalPrice;
	
	/** 预计交货日期 */
	public String forecastDeliveryTime;

	/** 预计出厂日期*/
	public String deliveryTime;
	
	/* production */
	/** 实际交货日期，实际出厂日期，工厂生产完成日期 ，公司收货日期*/
	public String productionEndTime;
	
	
	
	/* QC */
	/** 质检结果*/
	public String inspection;
	
	/** 质检日期*/
	public String inspectionDate;
	
	public List<FreightInfo> freightInfos;
	
	/* finance */
	public String invoicedTime;
	
	public String paymentTerm;
}
