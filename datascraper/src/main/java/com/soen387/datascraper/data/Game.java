package com.soen387.datascraper.data;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Game", strict = false)
public class Game {
	
	@Element(name="id")
    private int id;
	
	@Element(name="GameTitle", required=false)
	private String GameTitle;
	
	@Element(name="ReleaseDate", required=false)
	private String ReleaseDate;
	
	@Element(name="Overview", required=false)
	private String Description;
	
	@Element(name="Platform", required=false)
	private String Console;
	
	@Element(name="Players", required=false)
	private String Players;
	
	@Element(name="Co-op", required=false)
	private String Coop;
	
	@Element(name="Genres", required=false)
	private Genres genres;
	
	@Element(name="Developer", required=false)
	private String Developer;
	
	@Element(name="Publisher", required=false)
	private String Publisher;
	
	@Element(name="Images", required=false)
	private Images images;

	public Genres getGenres() {
		return genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
