package domain.business.foto;

import java.util.HashMap;

public class Foto {
    private int alto;
    private int ancho;
    private HashMap<Posicion, Color> pixeles = new HashMap<>();
    void normalizarA(int alto, int ancho) {}
    void resetearPixeles(){pixeles = new HashMap<>()};
    int armarRGB(int red, int green, int blue){
        return 0;
    }
    int[3] desarmarRGB(int colorRGB){
        return [0,0,0];
    }
}
