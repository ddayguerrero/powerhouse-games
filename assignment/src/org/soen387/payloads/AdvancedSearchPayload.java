package org.soen387.payloads;

import java.io.Serializable;

public class AdvancedSearchPayload implements Serializable{

	private static final long serialVersionUID = -6687987549363966472L;
	
	private String title;
	private String console;
	private String year;
	private String publisher;
	private String genre;
	
	public AdvancedSearchPayload(String title, String console, String year, String publisher, String genre) {
		super();
		this.title = title;
		this.console = console;
		this.year = year;
		this.publisher = publisher;
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
