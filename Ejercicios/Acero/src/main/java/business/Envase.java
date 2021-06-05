package business;

import java.util.List;

public class Envase {
    private int codigo;
    private TipoEnvase tipo;

    // Getters and Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public TipoEnvase getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnvase tipo) {
        this.tipo = tipo;
    }

    // Constructor
    public Envase(){ }

    public Envase(int codigo, TipoEnvase tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }
}
