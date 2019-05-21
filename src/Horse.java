import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Horse {

    public int posX;
    public int posY;
    public int raio;
    public int velX;
    public int velY;

    public BufferedImage spriteYelow; 
    public BufferedImage spriteBlack; 

    public BufferedImage blackMoviment1; 
    public BufferedImage blackMoviment2; 
    public BufferedImage blackMoviment3;

    public BufferedImage yelowMoviment1; 
    public BufferedImage yelowMoviment2; 
    public BufferedImage yelowMoviment3;
    
    
    
    public Horse() {

        try {
            spriteYelow = ImageIO.read(getClass().getResource("img/spriteAmarelo.png"));
            spriteBlack = ImageIO.read(getClass().getResource("img/spritePreto.png"));
            
            blackMoviment1 = Recursos.getInstance().cortarImagem(0, 0, 135, 98, spriteBlack);
            blackMoviment2 = Recursos.getInstance().cortarImagem(135, 0, 270, 98, spriteBlack);
            blackMoviment3 = Recursos.getInstance().cortarImagem(270, 0, 405, 98, spriteBlack);

            yelowMoviment1 = Recursos.getInstance().cortarImagem(0, 0, 135, 98, spriteYelow);
            yelowMoviment2 = Recursos.getInstance().cortarImagem(135, 0, 270, 98, spriteYelow);
            yelowMoviment3 = Recursos.getInstance().cortarImagem(270, 0, 405, 98, spriteYelow);
        
        }catch (Exception e) {
                System.out.println("Erro ao carregar a imagem!" + e);
        }

    	raio = 30;
        posX = 0;
        posY = 320;
        velX = 0;
        velY = 0;
    }
}