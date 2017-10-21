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
	
	public String getGameTitle() {
		return GameTitle;
	}

	public void setGameTitle(String gameTitle) {
		GameTitle = gameTitle;
	}

	public String getReleaseDate() {
		return ReleaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getConsole() {
		return Console;
	}

	public void setConsole(String console) {
		Console = console;
	}

	public String getPlayers() {
		return Players;
	}

	public void setPlayers(String players) {
		Players = players;
	}

	public String getCoop() {
		return Coop;
	}

	public void setCoop(String coop) {
		Coop = coop;
	}

	public String getDeveloper() {
		return Developer;
	}

	public void setDeveloper(String developer) {
		Developer = developer;
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public void setGenres(Genres genres) {
		this.genres = genres;
	}
}
