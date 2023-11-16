/*

public class PauseScreen extends GameScene implements SceneMethods {

    private MyButton resumeButton;
    private MyButton optionsButton;
    private MyButton menuButton;

    public PauseScreen(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        resumeButton = new MyButton("Retomar Jogo", 245, 150, 150, 50);
        optionsButton = new MyButton("Opções", 245, 250, 150, 50);
        menuButton = new MyButton("Retornar para o Menu", 245, 350, 150, 50);
    }

    @Override
    public void render(Graphics g) {
        // Desenhe um fundo transparente
        g.setColor(new Color(0, 0, 0, 100)); // Cor preta com transparência
        g.fillRect(0, 0, getWidth(), getHeight());

        drawButtons(g);
    }

    public void drawButtons(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        resumeButton.draw(g);
        optionsButton.draw(g);
        menuButton.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (resumeButton.getBounds().contains(x, y)) {
            game.togglePause(); // Retomar o jogo ao clicar em "Retomar Jogo"
        } else if (optionsButton.getBounds().contains(x, y)) {
            // Implementar lógica para acessar a tela de opções
        } else if (menuButton.getBounds().contains(x, y)) {
            game.togglePause(); // Retomar o jogo ao retornar para o menu
            SetGameState(MENU);
        }
    }

    // Outros métodos de input e renderização conforme necessário
}
 */