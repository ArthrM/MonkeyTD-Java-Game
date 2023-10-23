package scenes;

import java.awt.image.BufferedImage;

import main.Game;

public class GameScene {
	
	protected Game game;
	protected int tick;
	protected int animationIndex;
	protected int ANIMATION_SPEED = 25;
	
	public GameScene(Game game) {
		this.game = game;
	}

	
	public Game getGame() {
		return game;
	}
	
	protected boolean isAnimation(int spriteID) {
		return game.getTileManager().isSpriteAnimation(spriteID);
	}
	
	protected void updateTick() {
		tick ++;
		if(tick >= ANIMATION_SPEED) {
			tick = 0;
			animationIndex++;
			if(animationIndex >= 3)
				animationIndex = 0;
		}	
	}
	
	protected BufferedImage getSprite(int spriteID) {
		return getGame().getTileManager().getSprite(spriteID);
	}
	
	protected BufferedImage getSprite(int spriteID, int animationIndex) {
		return getGame().getTileManager().getAniSprite(spriteID,animationIndex);
	}

}
