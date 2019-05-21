import java.awt.image.BufferedImage;

public class Recursos {

    private static Recursos singleton = null;

    private Recursos(){
        
    }

    public static Recursos getInstance(){
        if (singleton == null) {
            singleton = new Recursos();
        }

        return singleton;
    }

    public BufferedImage cortarImagem(int x1, int y1, int x2, int y2, BufferedImage img){
        int largura = x2 - x1;
        int altura = y2 - y1;
        
        return img.getSubimage(x1, y1, largura, altura);
    } 

    public int GetFatorByIntervalo(long intervalo){
        int fator = 0;

        if(intervalo<60){
            fator = 15;
        }
        else if(intervalo<90){
            fator = 10;
        }
        else if(intervalo<150){
            fator = 5;
        }
        else{
            fator=0;
        }

        return fator;
    }
        
}