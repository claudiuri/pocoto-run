import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Game extends JPanel {

	private final double decay = 10000000;

	private Horse horseBlack;
	private long newTimeBlack = 0;
	private long  previousTimeBlack = 0;
	private long  intervalBlack = 1000;
	private boolean k_run_black = false;
	
	private Horse horseYelow;
	private long newTimeYelow = 0;
	private long previousTimeYelow = 0;
	private long intervalYelow = 1000;
	private boolean k_run_yelow = false;
	
	private BufferedImage currentSpriteBlack;
	private BufferedImage currentSpriteYelow;
	private BufferedImage header;
	// private int fatorMenos = 1;

	Background bg01, bg02, bg03, bg04;

	public Game() {

		try {
			bg01 = new Background("img/bg_01.png",0);
			bg02 = new Background("img/bg_02.png",640);
			bg03 = new Background("img/bg_03.png",1280);
			bg04 = new Background("img/bg_01.png",2560);
			header = ImageIO.read(getClass().getResource("img/header.png"));
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!" + e);
		}

		horseBlack = new Horse();
		horseYelow = new Horse();
		horseYelow.posY = horseBlack.posY + 10;

		currentSpriteBlack = horseBlack.blackMoviment1;
		currentSpriteYelow = horseYelow.yelowMoviment1;
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

					case KeyEvent.VK_RIGHT: {

						k_run_yelow = true;
						newTimeYelow = System.currentTimeMillis(); 
						intervalYelow = newTimeYelow - previousTimeYelow;
						
						if (currentSpriteYelow == horseYelow.yelowMoviment1) {
							currentSpriteYelow = horseYelow.yelowMoviment2;
						}else if(currentSpriteYelow == horseYelow.yelowMoviment2){
							currentSpriteYelow = horseYelow.yelowMoviment3;
						}else{
							currentSpriteYelow = horseYelow.yelowMoviment1;
						}

						horseYelow.posX = horseYelow.posX + Recursos.getInstance().GetFatorByIntervalo(intervalYelow);

					

						break;
					}
					case KeyEvent.VK_SPACE:{

						k_run_black = true;
						newTimeBlack = System.currentTimeMillis(); 
						intervalBlack = newTimeBlack - previousTimeBlack;
						
						if (currentSpriteBlack == horseBlack.blackMoviment1) {
							currentSpriteBlack = horseBlack.blackMoviment2;
						}else if(currentSpriteBlack == horseBlack.blackMoviment2){
							currentSpriteBlack = horseBlack.blackMoviment3;
						}else{
							currentSpriteBlack = horseBlack.blackMoviment1;
						}

						horseBlack.posX = horseBlack.posX + Recursos.getInstance().GetFatorByIntervalo(intervalBlack);

						testeColisoes();

						break;
					}
				}
			}  

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {

					case KeyEvent.VK_RIGHT:{
						previousTimeYelow = System.currentTimeMillis();
						k_run_yelow = false;
						break;
					}

					case KeyEvent.VK_SPACE:{
						previousTimeBlack = System.currentTimeMillis();
						k_run_black = false;
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
		long tempoInicio=System.nanoTime();
		long tempoAnterior = tempoInicio;
		long deltaTime=0;

		while (true) {
			tempoInicio = System.nanoTime();
			deltaTime = tempoInicio - tempoAnterior;
			tempoAnterior = tempoInicio;
			handlerEvents();
			update(deltaTime);
			render();
			try {
				Thread.sleep(17);
			} catch (Exception e) {
			}
		}
	}

	public void handlerEvents() {

		horseBlack.velX = 0;
		horseBlack.velY = 0;

		horseYelow.velX = 0;
		horseYelow.velY = 0;
	}

	public void update(long deltaTime) {

		bg01.checkPosition();
		bg02.checkPosition();
		bg03.checkPosition();
		bg01.move(deltaTime);
		bg02.move(deltaTime);
		bg03.move(deltaTime);

					
		testeColisoes();
		
	}

	public void render() {
		repaint();
	}

	public void testeColisoes() {

		if (horseBlack.posX + (horseBlack.raio * 2) >= Main.LARGURA_TELA || horseBlack.posX < 0) {
			horseBlack.posX = 0;
			horseYelow.posX = 0;
			JOptionPane.showMessageDialog(null, "Cavalo preto ganhou!");
		}

		if (horseYelow.posX + (horseYelow.raio * 2) >= Main.LARGURA_TELA || horseYelow.posX < 0) {

			horseBlack.posX = 0;
			horseYelow.posX = 0;
			JOptionPane.showMessageDialog(null, "Cavalo marelo ganhou!");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// setBackground(Color.LIGHT_GRAY);
		Graphics2D g2d = (Graphics2D) g;

		AffineTransform af01 = new AffineTransform();
		AffineTransform af02 = new AffineTransform();
		AffineTransform af03 = new AffineTransform();
		
		af01.translate(bg01.posX, bg01.posY);
		g2d.drawImage(bg01.img, af01, null);
		
		af02.translate(bg02.posX, bg02.posY);
		g2d.drawImage(bg02.img, af02, null);
		
		af03.translate(bg03.posX, bg03.posY);
		g2d.drawImage(bg03.img, af03, null);
		
		g.drawImage(header, Main.LARGURA_TELA/2 -  header.getWidth()/2, 5, null);
		g.drawImage(currentSpriteBlack,  (int) Math.floor(horseBlack.posX), (int) Math.floor(horseBlack.posY), null);
		g.drawImage(currentSpriteYelow, (int) Math.floor(horseYelow.posX), (int) Math.floor(horseYelow.posY), null);
	}

}