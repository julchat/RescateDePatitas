package business;

import java.util.List;

public class Envase {
    private int codigo;
    private List<Articulo> articulosCompatibles;
    private TipoEnvase tipo;

    // Getters and Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<Articulo> getArticulosCompatibles() {
        return articulosCompatibles;
    }

    public void setArticulosCompatibles(List<Articulo> articulosCompatibles) {
        this.articulosCompatibles = articulosCompatibles;
    }

    public TipoEnvase getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnvase tipo) {
        this.tipo = tipo;
    }

    // Constructor
    public Envase(){ }

    public Envase(int codigo, List<Articulo> articulosCompatibles, TipoEnvase tipo) {
        this.codigo = codigo;
        this.articulosCompatibles = articulosCompatibles;
        this.tipo = tipo;
    }
}
