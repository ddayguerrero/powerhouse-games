package org.soen387.domain;

import java.math.BigDecimal;

public class InvoiceDetails {

	private int id;
	private int invoiceId;
	private int quantity;
	private int gameId;
	private BigDecimal gamePrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public BigDecimal getGamePrice() {
		return gamePrice;
	}
	public void setGamePrice(BigDecimal gamePrice) {
		this.gamePrice = gamePrice;
	}
	
	
}
