package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class CreditScreen extends GameScene implements SceneMethods {

    private MyButton bBack;
    
    public CreditScreen(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        bBack = new MyButton("Return", 10, 10, 150, 50);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK); // Change the color as needed
        g.fillRect(0, 0, 640, 640);
        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        // Draw the credits text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("| ==== Participantes do Projeto ==== |", 135, 120);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("-> Mattheus Gonçalves Anitelli", 180, 200);
        g.drawString("RA: 22011982", 250, 230);
        g.drawString("-> Arthur José Silva Maluf", 190, 300);
        g.drawString("RA: 22005252", 250, 330);
        // Add more lines as needed
    
        // Draw the buttons
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        bBack.draw(g);
    }
    

    @Override
    public void mouseClicked(int x, int y) {
        if (bBack.getBounds().contains(x, y)) {
        SetGameState(SETTINGS); // Change to the appropriate state
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bBack.setMouseOver(false);

        if(bBack.getBounds().contains(x,y)){
            bBack.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        if (bBack.getBounds().contains(x, y)) {
            bBack.setMousePressed(true);
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub
    }

    private void resetButtons() {
        bBack.resetBooleans();
    }
}
