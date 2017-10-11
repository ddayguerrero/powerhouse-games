package com.soen387.datascraper.data;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataScraperAPI {
	@GET("api/GetPlatformGames.php?")
    Call<PlatformGamesResponse> loadPlatformGames(@Query("platform") String gameId);
	
	@GET("api/GetGame.php?")
	Call<GameResponse> fetchGameInfo(@Query("id") String gameId);
}
