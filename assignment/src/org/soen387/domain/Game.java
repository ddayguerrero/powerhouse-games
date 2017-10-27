package org.soen387.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public class Game {
	private int gameid;
	private String gameTitle;
	private String gameDescription;
	private String console;
	private String numPlayers;
	private boolean coop;
	private ArrayList<String> genre;
	private Date release_date;
	private String developer;
	private String publisher;
	private String front_box_art;
	private String back_box_art;
	private BigDecimal price;
	private BigDecimal discount;
	
	public int getGameid() {
		return gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public String getGameTitle() {
		return gameTitle;
	}
	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}
	public String getGameDescription() {
		return gameDescription;
	}
	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getNumPlayers() {
		return numPlayers;
	}
	public void setNumPlayers(String numPlayers) {
		this.numPlayers = numPlayers;
	}
	public boolean isCoop() {
		return coop;
	}
	public void setCoop(boolean coop) {
		this.coop = coop;
	}
	public ArrayList<String> getGenre() {
		return genre;
	}
	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
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
	public String getFront_box_art() {
		return front_box_art;
	}
	public void setFront_box_art(String front_box_art) {
		this.front_box_art = front_box_art;
	}
	public String getBack_box_art() {
		return back_box_art;
	}
	public void setBack_box_art(String back_box_art) {
		this.back_box_art = back_box_art;
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
