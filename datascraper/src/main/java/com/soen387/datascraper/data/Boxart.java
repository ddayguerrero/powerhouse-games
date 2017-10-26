package com.soen387.datascraper.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Boxart {
	@Attribute
    private String side;
	
	@Attribute
	private String thumb;
	
	@Attribute
	private String height;
	
	@Attribute
	private String width;
	
	@Text
    private String boxart;

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getBoxart() {
		return boxart;
	}

	public void setBoxart(String boxart) {
		this.boxart = boxart;
	}
	
	
}
