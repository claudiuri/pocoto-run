import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Game extends JPanel {

	private Bola bolaAzul;
	private int fatorMaisAzul = 0;
	private long novoTempoAzul = 0;
	private long  tempoAnteriorAzul = 0;
	private long  intervaloAzul = 1000;
	private boolean k_direita_azul = false;

	private Bola bolaVermelha;
	private int fatorMaisVermelho = 0;
	private long novoTempoVermelho= 0;
	private long tempoAnteriorVermelho = 0;
	private long intervaloVermelho = 1000;
	private boolean k_direita_vermelho = false;
	
	// private int fatorMenos = 1;

	public Game() {
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

					// Movimenta a bola vermelha
					case KeyEvent.VK_RIGHT: {

						k_direita_vermelho = true;
						novoTempoVermelho = System.currentTimeMillis();
						intervaloVermelho = novoTempoVermelho - tempoAnteriorVermelho;
						
						fatorMaisVermelho=0;

						if(intervaloVermelho<60){
							fatorMaisVermelho = 15;
						}
						else if(intervaloVermelho<90){
							fatorMaisVermelho = 10;
						}
						else if(intervaloVermelho<150){
							fatorMaisVermelho = 5;
						}
						else{
							fatorMaisVermelho=0;
						}

						bolaVermelha.posX = bolaVermelha.posX + fatorMaisVermelho;

						break;
					}
					
					// Movimenta a bola azul 
					case KeyEvent.VK_SPACE:{

						k_direita_azul = true;
						novoTempoAzul = System.currentTimeMillis(); // click atual
						intervaloAzul = novoTempoAzul - tempoAnteriorAzul; // intevalo entre o click anteiror e atual
						System.out.println(intervaloAzul);

						fatorMaisAzul = 0;

						if(intervaloAzul<60){
							fatorMaisAzul = 15;
						}
						else if(intervaloAzul<90){
							fatorMaisAzul = 10;
						}
						else if(intervaloAzul<150){
							fatorMaisAzul = 5;
						}
						else{
							fatorMaisAzul=0;
						}

						bolaAzul.posX = bolaAzul.posX + fatorMaisAzul;

						break;
					}

					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {

					case KeyEvent.VK_RIGHT:{
						tempoAnteriorVermelho = System.currentTimeMillis();
						k_direita_vermelho = false;
						break;
					}

					case KeyEvent.VK_SPACE:{
						tempoAnteriorAzul = System.currentTimeMillis();
						k_direita_azul = false;
						break;
					}
					
					
				}
				
			}
		});

		bolaVermelha= new Bola();
		bolaAzul = new Bola();
		
		setFocusable(true);
		setLayout(null);

		new Thread(new Runnable() { // instancia da Thread + classe interna anï¿½nima
			@Override
			public void run() {
				gameloop(); // inicia o gameloop
			}
		}).start(); // dispara a Thread
	}

	public void gameloop() {
		while (true) {
			handlerEvents();
			update();
			render();
			try {
				Thread.sleep(17);
			} catch (Exception e) {
			}
		}
	}

	public void handlerEvents() {
		bolaAzul.velX = 0;
		bolaAzul.velY = 0;

		bolaVermelha.velX = 0;
		bolaVermelha.velY = 0;
	}

	public void update() {
		testeColisoes();
	}

	public void render() {
		repaint();
	}

	public void testeColisoes() {
		// if (bola.posX + (bola.raio * 2) >= Main.LARGURA_TELA || bola.posX < 0) {
		// 	bola.posX -= bola.velX;
		// }
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.LIGHT_GRAY);
		g.setColor(Color.BLUE);
		g.fillOval(bolaAzul.posX, 10, bolaAzul.raio * 2, bolaAzul.raio * 2);

		g.setColor(Color.RED);
		g.fillOval(bolaVermelha.posX, 80, bolaVermelha.raio * 2, bolaVermelha.raio * 2);
	}

}