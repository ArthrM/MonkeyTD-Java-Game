package main;

public enum GameStates {

	PLAYING,MENU,SETTINGS,EDIT,CREDITS;
	
	public static GameStates gameState = MENU;
	
	public static void SetGameState(GameStates state) {
		gameState = state;
	}
	
}
