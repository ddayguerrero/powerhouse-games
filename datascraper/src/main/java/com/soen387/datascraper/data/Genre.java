package com.soen387.datascraper.data;

import org.simpleframework.xml.Text;

public class Genre {
	@Text
    private String genre;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
