package org.soen387.payloads;

import java.math.BigDecimal;
import java.sql.Date;

public class GamePayload {
	private int id;
	private String title;
	private String description;
	private String console;
	private String players;
	private boolean coop;
	private String genre;
	private Date release;
	private String developer;
	private String publisher;
	private String front_cover;
	private String back_cover;
	private BigDecimal price;
	private BigDecimal discount;
	
	
	public GamePayload(int id, String title, String description, String console, String players, boolean coop,
			String genre, Date release, String developer, String publisher, String front_cover, String back_cover,
			BigDecimal price, BigDecimal discount) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.console = console;
		this.players = players;
		this.coop = coop;
		this.genre = genre;
		this.release = release;
		this.developer = developer;
		this.publisher = publisher;
		this.front_cover = front_cover;
		this.back_cover = back_cover;
		this.price = price;
		this.discount = discount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getPlayers() {
		return players;
	}
	public void setPlayers(String players) {
		this.players = players;
	}
	public boolean isCoop() {
		return coop;
	}
	public void setCoop(boolean coop) {
		this.coop = coop;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Date getRelease() {
		return release;
	}
	public void setRelease(Date release) {
		this.release = release;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getFront_cover() {
		return front_cover;
	}
	public void setFront_cover(String front_cover) {
		this.front_cover = front_cover;
	}
	public String getBack_cover() {
		return back_cover;
	}
	public void setBack_cover(String back_cover) {
		this.back_cover = back_cover;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
