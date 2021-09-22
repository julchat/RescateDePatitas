package domain.business.notificaciones;
import domain.business.Mascota;
import domain.business.Persona;
import domain.business.publicaciones.PublicacionParaAdoptar;

public abstract class Notificacion {
    private TipoNotificacion codigoNotificacion;

// Getters and Setters
    public TipoNotificacion obtenerCodigoNotificacion() { return codigoNotificacion; }

    public void setTipoNotificacion(TipoNotificacion codigoNotificacion) { this.codigoNotificacion = codigoNotificacion; }

// Metodos
    AdapterTwillio twillio = new AdapterTwillio();
    AdapterJavaMailApi mailSender = new AdapterJavaMailApi();
    public String armarMensajeMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaEncontrada, String ruta) {
        return "Estimado/a " + destinatario.getNombre() + "\n" + "Nos comunicamos para informarle que su mascota " + mascotaEncontrada.getNombreMascota() +
                " ha sido encontrada por " + hallador.getApellido() + " " + hallador.getNombre() + ".\n Para mas informacion ingrese a " + ruta;
    }
    public String armarMensajeHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta){
        return "Estimado/a " + destinatario.getNombre() + "\n" + "Nos comunicamos para informarle que el usuario " + interesado.getApellido() + " " + interesado.getNombre() +
                "esta interesado/a en adoptar a su mascota " + mascotaPorSerAdoptada.getNombreMascota() +  ".\n Para mas informacion ingrese a " + ruta;
    }
    public String armarMensajeRecomendacionesSemanales(Persona destinatario, PublicacionParaAdoptar publicacion, String ruta){
        return "Estimado/a " + destinatario.getNombre() + "\n" + " Nos comunicamos para informarle que hay recomendaciones de mascotas para adoptar relacionadas a su publicacion disponibles!" +
                " para visualizarlas, ingrese a " + ruta;
    }
    public abstract void notificarMascotaEncontrada(Persona destinatario, Persona hallador, Mascota mascotaPerdida, String ruta);
    public abstract void notificarHayInteresadoEnAdoptar(Persona destinatario, Persona interesado, Mascota mascotaPorSerAdoptada, String ruta);
    public abstract void notificarRecomendaciones(Persona destinatario, PublicacionParaAdoptar publicacion, String ruta);
}
