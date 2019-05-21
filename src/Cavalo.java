import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Cavalo {

    public int posX;
    public int posY;
    public int raio;
    public int velX;
    public int velY;

    public BufferedImage sprite; 
    public BufferedImage movimento1; 
    public BufferedImage movimento2; 
    public BufferedImage movimento3; 
    public BufferedImage movimento4; 
    
    public Cavalo() {

        try {
            sprite = ImageIO.read(getClass().getResource("img/sprite.png"));
            
            movimento1 = Recursos.getInstance().cortarImagem(0, 0, 105, 85, sprite);
            movimento2 = Recursos.getInstance().cortarImagem(105, 0, 205, 85, sprite);
            movimento3 = Recursos.getInstance().cortarImagem(0, 0, 105, 85, sprite);
            movimento4 = Recursos.getInstance().cortarImagem(0, 0, 105, 85, sprite);
        
        }catch (Exception e) {
                System.out.println("Erro ao carregar a imagem!" + e);
        }

    	raio = 30;
        posX = 0;
        posY = 100;
        velX = 0;
        velY = 0;
    }
}