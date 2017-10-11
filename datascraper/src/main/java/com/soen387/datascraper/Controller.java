package com.soen387.datascraper;

import com.soen387.datascraper.data.DataScraperAPI;
import com.soen387.datascraper.data.Game;
import com.soen387.datascraper.data.GameResponse;
import com.soen387.datascraper.data.PlatformGamesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class Controller{
	public Retrofit retrofit;
	public DataScraperAPI dscraper;
	static final String BASE_URL = "http://thegamesdb.net/";
	
	public Controller() {
		super();
		this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();
		this.dscraper = retrofit.create(DataScraperAPI.class);
	}
	
	public Call<PlatformGamesResponse> getPlatformGames(int platformId) {
        return dscraper.loadPlatformGames(Integer.toString(platformId));
    }
	
	public Call<GameResponse> fetchGameInfo(int gameId) {
		return dscraper.fetchGameInfo(Integer.toString(gameId));
	}
	
//	public void onFailure(Call<PlatformGame> arg0l, Throwable t) {
//        t.printStackTrace();
//    }
//
//	public void onResponse(Call<PlatformGame> call, Response<PlatformGame> response) {
//        if (response.isSuccessful()) {
//        		PlatformGame platformGames = response.body();
//        		App.setPlatformGames(platformGames.getList());
//            
//        } else {
//            System.out.println(response.errorBody());
//        }
//    }

}
