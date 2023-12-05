package main;

public enum GameStates {

	PLAYING,MENU,SETTINGS,EDIT,CREDITS,GAMEOVER;
	
	public static GameStates gameState = MENU;
	
	public static void SetGameState(GameStates state) {
		gameState = state;
	}
	
}
