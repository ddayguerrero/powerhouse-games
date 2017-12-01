package org.soen387.domain;

import java.math.BigDecimal;

public class CartItem<T> {
	private int quantity;
	private BigDecimal total;
	private T item;
	
	public CartItem() {
		super();
		quantity = 1;
		total = new BigDecimal(0);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getTotal() {
		Game g = (Game) item;
		this.total = g.getPrice().multiply(new BigDecimal(this.quantity));
		return total;
	}
	
	public BigDecimal getMemberPricingTotal() {
		Game g = (Game) item;
		if(g.getDiscount().compareTo(BigDecimal.ZERO) == 0) {
			this.total = g.getPrice().multiply(new BigDecimal(this.quantity));
		} else {
			BigDecimal discountedPrice = g.getPrice().subtract(g.getDiscount());
			this.total = discountedPrice.multiply(new BigDecimal(this.quantity));
		}
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
	
	
}
