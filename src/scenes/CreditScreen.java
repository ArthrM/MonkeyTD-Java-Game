package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.Timer;
import main.Game;
import ui.MyButton;
import static main.GameStates.SETTINGS;
import static main.GameStates.SetGameState;

public class CreditScreen extends GameScene implements SceneMethods {

    private MyButton bBack;
    private boolean creditosVisiveis;

    public CreditScreen(Game game) {
        super(game);
        initButtons();
    //    iniciarPiscar();
    }

    private void initButtons() {
        bBack = new MyButton("VOLTAR", 10, 10, 150, 50);
    }

   /*  // Inicia o efeito de piscar dos créditos usando um Timer.
    private void iniciarPiscar() {
        Timer timer = new Timer(500, e -> {
            // Inverte o estado de visibilidade dos créditos.
            creditosVisiveis = !creditosVisiveis;
            // Solicita a repintura da tela.
            repaint();
        });
        timer.start();
    }

    private void repaint() {
        // método de repintura da tela
    }

*/
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 780);
        drawButtons(g);
      //  if (creditosVisiveis) {
            drawText(g);
        //}
    }

    public void drawText(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("CRÉDITOS", 260, 70);
        g.drawString("(==== Participantes do Projeto ====)", 145, 200);
        g.drawString("~~ Jogo em Java // Inspirado em Blloons ~~", 115, 130);
        g.drawString("__________", 259, 72);
        g.drawString("Obrigado por Jogar! :)", 220, 610);



        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("-> Mattheus Gonçalves Anitelli: 22011982", 120, 270);
        g.drawString("-> Arthur José Silva Maluf: 22005252", 120, 330);
        g.drawString("-> Vinicius Barbosa: 22000267", 120, 400);
        g.drawString("-> João Vitor Roventini: 22005168", 120, 460);
        g.drawString("-> João Pedro Galo: 00000000", 120, 520);
     
    }

    public void drawButtons(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        bBack.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bBack.getBounds().contains(x, y)) {
            SetGameState(SETTINGS);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bBack.setMouseOver(bBack.getBounds().contains(x, y));
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
