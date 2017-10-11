package com.soen387.datascraper.data;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "Images", strict = false)
public class Images {
	
	@ElementList(inline=true)
    private List<Boxart> boxart;

	public List<Boxart> getList() {
		return boxart;
	}

	public void setList(List<Boxart> list) {
		this.boxart = list;
	}
}
