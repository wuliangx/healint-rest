package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * Data returned from the database
 */
@Entity
@Table(name = "store_order")
public class Order {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="ORDER_DATE")
    private Date orderDate;
	
	@Column(name="SHIP_DATE")
    private Date shipDate;
	
	@Column(name="SHIP_MODE")	
    private String shipMode;
	
	@Column(name="QUANTITY")
    private int quantity;
    
	@Column(name="DISCOUNT")
    private BigDecimal discount;
    
	@Column(name="PROFIT")
    private BigDecimal profit;
    
    @Column(name="PRODUCT_ID")
	private String productId;
	
	@Column(name="CUSTOMER_NAME")
    private String customerName;
    
	@Column(name="CATEGORY")
	private String category;
    
	@Column(name="CUSTOMER_ID")
    private String customerId;
    
	@Column(name="PRODUCT_NAME")
    private String productName;

    public Order() {
    }
    
    public Order(Integer id, String orderId, Date orderDate, Date shipDate, String shipMode, int quantity, BigDecimal discount,
			BigDecimal profit, String productId, String customerName, String category, String customerId,
			String productName) {
    	this.id = id;
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.shipMode = shipMode;
		this.quantity = quantity;
		this.discount = discount;
		this.profit = profit;
		this.productId = productId;
		this.customerName = customerName;
		this.category = category;
		this.customerId = customerId;
		this.productName = productName;
	}
    
    public Integer getId() {
    	return id;
    }

	public String getOrderId() {
		return orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public String getShipMode() {
		return shipMode;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public String getProductId() {
		return productId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCategory() {
		return category;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getProductName() {
		return productName;
	}
	
	public void update(Order newOrder) {
		if (newOrder.orderId != null) {
			this.orderId = newOrder.orderId;
		}
		if (newOrder.orderDate != null) {
			this.orderDate = newOrder.orderDate;
		}
		if (newOrder.shipDate != null) {
			this.shipDate = newOrder.shipDate;
		}
		if (newOrder.shipMode != null) {
			this.shipMode = newOrder.shipMode;
		}
		if (newOrder.quantity != 0) {
			this.quantity = newOrder.quantity;
		}
		if (newOrder.discount != null) {
			this.discount = newOrder.discount;
		}
		if (newOrder.profit != null) {
			this.profit = newOrder.profit;
		}
		if (newOrder.productId != null) {
			this.productId = newOrder.productId;
		}
		if (newOrder.customerName != null) {
			this.customerName = newOrder.customerName;
		}
		if (newOrder.category != null) {
			this.category = newOrder.category;
		}
		if (newOrder.customerId != null) {
			this.customerId = newOrder.customerId;
		}
		if (newOrder.productName != null) {
			this.productName = newOrder.productName;
		}
	}
    
	public static class Builder {
		private Integer id;
		private String orderId;
	    private Date orderDate;
	    private Date shipDate;
	    private String shipMode;
	    private int quantity;
	    private BigDecimal discount;
	    private BigDecimal profit;
		private String productId;
	    private String customerName;
	    private String category;
	    private String customerId;
	    private String productName;
	    
	    public Order build() {
	    	return new Order(id, orderId, orderDate, shipDate, shipMode, quantity, discount,
	    			profit, productId, customerName, category, customerId,
	    			productName);
	    }
	    
	    public Builder setId(Integer id) {
	    	this.id = id;
	    	return this;
	    }
	    
	    public Builder setOrderId(String orderId) {
	    	this.orderId = orderId;
	    	return this;
	    }

	    public Builder setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
	    	return this;
		}

		public Builder setShipDate(Date shipDate) {
			this.shipDate = shipDate;
	    	return this;
		}

		public Builder setShipMode(String shipMode) {
			this.shipMode = shipMode;
	    	return this;
		}

		public Builder setQuantity(int quantity) {
			this.quantity = quantity;
	    	return this;
		}

		public Builder setDiscount(BigDecimal discount) {
			this.discount = discount;
	    	return this;
		}

		public Builder setProfit(BigDecimal profit) {
			this.profit = profit;
	    	return this;
		}

		public Builder setProductId(String productId) {
			this.productId = productId;
	    	return this;
		}

		public Builder setCustomerName(String customerName) {
			this.customerName = customerName;
	    	return this;
		}

		public Builder setCategory(String category) {
			this.category = category;
	    	return this;
		}

		public Builder setCustomerId(String customerId) {
			this.customerId = customerId;
	    	return this;
		}

		public Builder setProductName(String productName) {
			this.productName = productName;
	    	return this;
		}
	}
}
