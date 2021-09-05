package domain.business.publicaciones;

public abstract class TipoPublicacion {

    public void aceptar(PuestoEnAdopcion publicacion) {};
    public void aceptar(BusquedaMascotaIdeal publicacion) {};
    public void aceptar(BusquedaMascotaPerdida publicacion) {};

    public abstract void mostrarDatos();
}
