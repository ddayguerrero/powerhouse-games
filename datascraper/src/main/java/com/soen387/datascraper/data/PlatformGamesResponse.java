package com.soen387.datascraper.data;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="Data", strict=false)
public class PlatformGamesResponse {
	
	@ElementList(inline=true)
    private List<Game> list;

	public List<Game> getList() {
		return list;
	}

	public void setList(List<Game> list) {
		this.list = list;
	}
}
