package org.soen387.beans;

import java.io.Serializable;

public class AdvancedSearchBean implements Serializable{

	private static final long serialVersionUID = -6687987549363966472L;
	
	private String title;
	private String console;
	private String year;
	private String publisher;
	
	public AdvancedSearchBean(String title, String console, String year, String publisher) {
		super();
		this.title = title;
		this.console = console;
		this.year = year;
		this.publisher = publisher;
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
	
}
