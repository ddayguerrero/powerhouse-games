package com.soen387.datascraper;

import java.io.IOException;
import java.util.List;

import com.soen387.datascraper.data.Game;
import com.soen387.datascraper.data.GameResponse;
import com.soen387.datascraper.data.PlatformGamesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class App {
	
	public static List<Game> games;
	
	public static void main(String[] args) throws IOException {
		Controller ctrl = new Controller();
		final int xbone = 4920;
		final int ps4 = 4919;
		final int nswitch = 4971;
		
		Call<PlatformGamesResponse> platformGameCall = ctrl.getPlatformGames(xbone);
		PlatformGamesResponse platformGameResp = platformGameCall.execute().body();
		List<Game> xboneGames = platformGameResp.getList().subList(0, 20);
		
		for (Game g: xboneGames) {
			Call<GameResponse> gameCall = ctrl.fetchGameInfo(g.getId());
			gameCall.enqueue(new Callback<GameResponse>() {

				public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
					Game g = response.body().getGame();
					games.add(response.body().getGame());
				}

				public void onFailure(Call<GameResponse> call, Throwable t) {
					// TODO Auto-generated method stub
					
				}
				
			});
//			games.add(gameResp.getGame());
		}
		System.out.println("hi");
	}

}
