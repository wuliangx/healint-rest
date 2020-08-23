package models;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderCodec {
	
	private final DateFormat dateFormatter;
	
	public OrderCodec() {
		dateFormatter = new SimpleDateFormat("dd.mm.yy");
	}
	
	public PostOrder getPostOrder(Order order) {
		PostOrder.Builder builder = new PostOrder.Builder();
		
		builder.setOrderId(order.getOrderId());
		builder.setOrderDate(dateFormatter.format(order.getOrderDate()));
		builder.setShipDate(dateFormatter.format(order.getShipDate()));
		builder.setShipMode(order.getShipMode());
		builder.setQuantity(order.getQuantity());
		builder.setDiscount(order.getDiscount().toPlainString());
		builder.setProfit(order.getProfit().toPlainString());
		builder.setProductId(order.getProductId());
		builder.setCustomerName(order.getCustomerName());
		builder.setCategory(order.getCategory());
		builder.setCustomerId(order.getCustomerId());
		builder.setProductName(order.getProductName());
		
		return builder.build();
	}
	
	public Order getOrder(PostOrder postOrder) {
		Order.Builder builder = new Order.Builder();
		
		builder.setOrderId(postOrder.getOrderId());
		builder.setOrderDate(parseDate(postOrder.getOrderDate()));
		builder.setShipDate(parseDate(postOrder.getShipDate()));
		builder.setShipMode(postOrder.getShipMode());
		builder.setQuantity(postOrder.getQuantity());
		builder.setDiscount(parseDecimal(postOrder.getDiscount()));
		builder.setProfit(parseDecimal(postOrder.getProfit()));
		builder.setProductId(postOrder.getProductId());
		builder.setCustomerName(postOrder.getCustomerName());
		builder.setCategory(postOrder.getCategory());
		builder.setCustomerId(postOrder.getCustomerId());
		builder.setProductName(postOrder.getProductName());
		
		return builder.build();
	}
	
	public Order getOrder(String[] parts) {
		Order.Builder builder = new Order.Builder();
		//Row ID,Order ID,Order Date,Ship Date,Ship Mode,Customer ID,Customer Name,Segment,Country,City,State,Postal Code,Region,Product ID,Category,Sub-Category,Product Name,Sales,Quantity,Discount,Profit
		builder.setOrderId(parts[1]);
		builder.setOrderDate(parseDate(parts[2]));
		builder.setShipDate(parseDate(parts[3]));
		builder.setShipMode(parts[4]);
		builder.setCustomerId(parts[5]);
		builder.setCustomerName(parts[6]);
		builder.setProductId(parts[13]);
		builder.setCategory(parts[14]);
		builder.setProductName(parts[16]);
		builder.setQuantity(Integer.parseInt(parts[18]));
		builder.setDiscount(parseDecimal(parts[19]));
		builder.setProfit(parseDecimal(parts[20]));
		
		return builder.build();
	}
	
	private BigDecimal parseDecimal(String value) {
		if (value != null) {
			return new BigDecimal(value);
		} else {
			return null;
		}
	}
	 
	private Date parseDate(String dateStr) {
		if (dateStr != null) {
			String[] parts = dateStr.split("\\.");
			int year = Integer.parseInt(parts[2]);
			int month = Integer.parseInt(parts[1]);
			int date = Integer.parseInt(parts[0]);
			
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(year, month -1, date);
			return cal.getTime();
		} else {
			return null;
		}
	}
}
