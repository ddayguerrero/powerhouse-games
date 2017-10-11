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
}
