package com.soen387.datascraper.data;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "Genres", strict = false)
public class Genres {
	
	@ElementList(inline=true)
    private List<Genre> genre;

	public List<Genre> getList() {
		return genre;
	}

	public void setList(List<Genre> list) {
		this.genre = list;
	}
}
