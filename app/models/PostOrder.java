package models;

/**
 * Order that was sent over a POST request.
 */
public class PostOrder {
    
    private String orderId;
    private String orderDate;
    private String shipDate;
    private String shipMode;
    private int quantity;
    private String discount;
    private String profit;
	private String productId;
    private String customerName;
    private String category;
    private String customerId;
    private String productName;

    public PostOrder() {
    }
    
    public PostOrder(String orderId, String orderDate, String shipDate, String shipMode, int quantity,
			String discount, String profit, String productId, String customerName, String category, String customerId,
			String productName) {
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

	public String getOrderId() {
		return orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getShipDate() {
		return shipDate;
	}

	public String getShipMode() {
		return shipMode;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDiscount() {
		return discount;
	}

	public String getProfit() {
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
	
	public static class Builder {
		private String orderId;
	    private String orderDate;
	    private String shipDate;
	    private String shipMode;
	    private int quantity;
	    private String discount;
	    private String profit;
		private String productId;
	    private String customerName;
	    private String category;
	    private String customerId;
	    private String productName;
	    
	    public PostOrder build() {
	    	return new PostOrder(orderId, orderDate, shipDate, shipMode, quantity, discount,
	    			profit, productId, customerName, category, customerId,
	    			productName);
	    }
	    
	    public Builder setOrderId(String orderId) {
	    	this.orderId = orderId;
	    	return this;
	    }

	    public Builder setOrderDate(String orderDate) {
			this.orderDate = orderDate;
	    	return this;
		}

		public Builder setShipDate(String shipDate) {
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

		public Builder setDiscount(String discount) {
			this.discount = discount;
	    	return this;
		}

		public Builder setProfit(String profit) {
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
