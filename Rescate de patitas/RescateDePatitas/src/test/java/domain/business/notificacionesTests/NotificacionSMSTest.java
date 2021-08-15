/*package domain.business.notificacionesTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import domain.business.*;
import org.apache.avro.generic.GenericData;
import org.junit.Before;
import org.junit.Test;
import domain.notificaciones.*;
import domain.business.foto.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificacionSMSTest {
    private NotificadorSms notiSMS = new NotificadorSms();
    private List<Notificacion> medios = new ArrayList<>();
    private Mascota mascotaQueSePerdio;
    private Duenio duenioMascota;
    private List<Foto> fotos = new ArrayList<>();
    private List<Mascota> mascotas = new ArrayList<>();
    private String link = "www.RescateDePatitas.com.ar/Formularios/324";
    private Rescatista halladorMascota = new Rescatista();
    private NotificadorWhatsapp notiWPP = new NotificadorWhatsapp();
    private NotificadorEmail notiMail = new NotificadorEmail();
    @Before
    public void setUp(){
        fotos.add(new Foto());
        duenioMascota = new Duenio("Jorge", "Santos" , LocalDateTime.now(), TipoDoc.DNI, 3541212, "+541128646857" , "cristianmanuelcali@hotmail.com", medios, null, null, mascotas);
        mascotaQueSePerdio = new Mascota("Popi", TipoAnimal.GATO, 5, SexoMascota.HEMBRA, "gordi", fotos, null, true, false, duenioMascota);
        duenioMascota.getMascotas().add(mascotaQueSePerdio);
        halladorMascota.setNombre("Claudio");
        halladorMascota.setApellido("Peterson");
    }

    @Test*/
    /*public void pruebaSMS(){
        medios.add(notiSMS);
        duenioMascota.getFormasDeNotificacion().forEach((unMedio -> unMedio.notificarMascotaEncontrada(duenioMascota, halladorMascota, mascotaQueSePerdio, link)));
        assertTrue(true);
    } Comento porque baja el saldo*/

    /*public void pruebaWhatsapp(){
        duenioMascota.getFormasDeNotificacion().add(notiWPP);
        duenioMascota.getFormasDeNotificacion().forEach((unMedio -> unMedio.notificarMascotaEncontrada(duenioMascota, halladorMascota, mascotaQueSePerdio, link)));
        assertTrue(true);
    }

    public void pruebaMail(){
        duenioMascota.getFormasDeNotificacion().add(notiMail);
        duenioMascota.getFormasDeNotificacion().forEach((unMedio -> unMedio.notificarMascotaEncontrada(duenioMascota, halladorMascota, mascotaQueSePerdio, link)));
        assertTrue(true);
    }
}
*/