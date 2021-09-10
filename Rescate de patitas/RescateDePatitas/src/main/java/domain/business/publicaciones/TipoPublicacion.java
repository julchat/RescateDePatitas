package domain.business.publicaciones;

public abstract class TipoPublicacion {

    public void aceptar(PublicacionMascotaEnAdopcion publicacion) {};
    public void aceptar(PublicacionParaAdoptar publicacion) {};
    public void aceptar(PublicacionMascotaPerdida publicacion) {};

    public abstract void mostrarDatos();
}
