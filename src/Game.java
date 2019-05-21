import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {

	private Cavalo CavaloAzul;
	private long novoTempoAzul = 0;
	private long  tempoAnteriorAzul = 0;
	private long  intervaloAzul = 1000;
	private boolean k_direita_azul = false;
	
	private Cavalo bolaVermelha;
	private long novoTempoVermelho= 0;
	private long tempoAnteriorVermelho = 0;
	private long intervaloVermelho = 1000;
	private boolean k_direita_vermelho = false;
	
	private BufferedImage imgAtual;
	// private int fatorMenos = 1;

	public Game() {
		bolaVermelha= new Cavalo();
		CavaloAzul = new Cavalo();
		imgAtual = CavaloAzul.movimento1;
		
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
						
						bolaVermelha.posX = bolaVermelha.posX + Recursos.getInstance().GetFatorByIntervalo(intervaloVermelho);

						break;
					}
					
					// Movimenta a bola azul 
					case KeyEvent.VK_SPACE:{

						k_direita_azul = true;
						novoTempoAzul = System.currentTimeMillis(); 
						intervaloAzul = novoTempoAzul - tempoAnteriorAzul;
						
						if (imgAtual == CavaloAzul.movimento1) {
							imgAtual = CavaloAzul.movimento2;
						}else{
							imgAtual = CavaloAzul.movimento1;
						}

						CavaloAzul.posX = CavaloAzul.posX + Recursos.getInstance().GetFatorByIntervalo(intervaloAzul);

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

		CavaloAzul.velX = 0;
		CavaloAzul.velY = 0;

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

		if (CavaloAzul.posX + (CavaloAzul.raio * 2) >= Main.LARGURA_TELA || CavaloAzul.posX < 0) {
			JOptionPane.showMessageDialog(null, "Bola Azul ganhou!");
			System.exit(0);
		}

		if (bolaVermelha.posX + (bolaVermelha.raio * 2) >= Main.LARGURA_TELA || bolaVermelha.posX < 0) {
			JOptionPane.showMessageDialog(null, "Bola Vemelha ganhou!");
			System.exit(0);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.LIGHT_GRAY);
		g.setColor(Color.BLUE);
		g.drawImage(imgAtual, CavaloAzul.posX, CavaloAzul.posY, null);

		// g.setColor(Color.RED);
		// g.fillOval(bolaVermelha.posX, 80, bolaVermelha.raio * 2, bolaVermelha.raio * 2);
	}

}