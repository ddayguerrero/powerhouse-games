package com.soen387.datascraper.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Data", strict=false)
public class GameResponse {

	@Element(name="Game")
	private Game game;

	public Game getGame() {
		return game;
	}
}