package domain.business.foto;

import java.util.HashMap;

public class Foto {
    private int alto;
    private int ancho;
    private HashMap<Posicion, Color> pixeles = new HashMap<>();
    public void normalizarA(DimensionEstandar dimension) {}
    public void resetearPixeles(){pixeles = new HashMap<>();}
    public int armarRGB(int red, int green, int blue){
        return 0;
    }
   /* public int[] desarmarRGB(int colorRGB){
        return [0,0,0]
    }*/
}
