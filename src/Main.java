import java.awt.Dimension;
import javax.swing.JFrame;

public class Main {

    public static final int LARGURA_TELA = 1280;
    public static final int ALTURA_TELA = 480;

    public Main() {
        JFrame janela = new JFrame("Pocoto Run"); // cria a janela
        Game game = new Game(); // cria a tela do jogo
        game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        janela.getContentPane().add(game); // adiciona a tela do jogo na janela
        //janela.setSize(LARGURA_TELA, ALTURA_TELA); // dimensões da janela
        janela.setResizable(false); // impede redimensionamento
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // modo de encerramento
        // janela.setLocation(100, 100); // posição da janela na tela
        janela.setVisible(true); // torna a janela visível
        janela.pack();
        janela.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Main(); // dispara a aplicação
    }
}
