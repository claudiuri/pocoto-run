import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Background {	
	public BufferedImage img; 
	public double posX;
	public double posY;
	public double velX;
	public double velY;
	
	public Background(String nome, double posX) {
		try {
			img = ImageIO.read(getClass().getResource(nome));
		}catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!" + e);
		}
		this.posX = posX;
		posY = 0;
		velX = -1;
		velY = 0;
	}
	
	public void move(long deltaTime) {
		posX = posX + velX;
		posY = posY + velY;
	}
	
	public void checkPosition() {
		if(posX+640<=0) {
			this.posX = 1280;
		}
	}
}
