package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.Game;
import main.sounds;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{

//	private BufferedImage img;
//	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private Random random;
	private MyButton bPlaying, bEdit, bSettings, bQuit;
	private Clip menuSoundClip; // Adiciona um membro para armazenar o Clip do som
	
	public Menu(Game game) {
		super(game);
		initButtons();
		random = new Random();

		playMenuSound();
//		importImg();
//		loadSprites();
		
	}

	private void initButtons() {
		
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;
		bPlaying  = new MyButton("Jogar", x , y , w , h);
		bEdit     = new MyButton("Editor", x , y + yOffset, w, h);
		bSettings = new MyButton("Opções", x , y + 2*yOffset , w , h);
		bQuit     = new MyButton("Sair", x , y + 3*yOffset , w , h);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK); // Change the color as needed
        g.fillRect(0, 0, 640, 780);
		drawButtons(g);
		drawText(g);
	}
	
	
	private void drawText(Graphics g) {

		g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("BEM VINDOS AO NOSSO JOGO!", 170, 100);
		g.drawString("APROVEITEM!! :)", 240, 600);
	}

	private void drawButtons(Graphics g){
		g.setFont(new Font("Rockwell", Font.PLAIN, 15));
		bPlaying.draw(g);
		bEdit.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
	}

/*	private void importImg() {
		
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
		
		// Tenta importar a imagem, se falhar, mostra uma mensagem de erro
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {          
			e.printStackTrace();                
		}
		
	}
	
	private void loadSprites() {
		
		for(int y = 0; y < 12; y++) {
			for(int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x*32, y*32, 32, 32));
			}
		}
		
	}
	
	private int getRndInt() {
		return random.nextInt(120);
	}
 */	
	
private void playMenuSound() {
	sounds.playMenuSound("c:/Users/matta/Downloads/Title Music (Party Time).wav");

        try {
            File arquivoSom = new File("c:/Users/matta/Downloads/Title Music (Party Time).wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivoSom);

            menuSoundClip = AudioSystem.getClip();
            menuSoundClip.open(audioInputStream);
            menuSoundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
public void finalize() {
    stopMenuSound();
}

private void stopMenuSound() {
    if (menuSoundClip != null && menuSoundClip.isRunning()) {
        menuSoundClip.stop();
        menuSoundClip.close();
    }
}

	@Override
	public void mouseClicked(int x, int y) {
		
		if(bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		} else if (bEdit.getBounds().contains(x, y)) {
			SetGameState(EDIT);
		} else if (bSettings.getBounds().contains(x,y)) {
			SetGameState(SETTINGS);
		} else if (bQuit.getBounds().contains(x,y)) {
			System.exit(0);
		}
	}
	
	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bEdit.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);
		
		
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		} else if (bEdit.getBounds().contains(x, y)) {
			bEdit.setMouseOver(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMouseOver(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
			stopMenuSound();
		} else if (bEdit.getBounds().contains(x, y)){
			bEdit.setMousePressed(true);
		} else if (bSettings.getBounds().contains(x, y)) {
			bSettings.setMousePressed(true);
		} else if (bQuit.getBounds().contains(x, y)) {
			bQuit.setMousePressed(true);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bEdit.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
