package domain.business.tests;

import domain.business.*;
import domain.business.foto.Foto;
import domain.business.notificaciones.Notificacion;
import domain.business.notificaciones.NotificadorEmail;
import domain.business.notificaciones.NotificadorSms;
import domain.business.notificaciones.NotificadorWhatsapp;
import domain.business.organizaciones.apiHogares.APIhogares;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class test {
    Sistema miSistema = Sistema.getInstance();

    @Test
    public void contraseniaInsegura() throws IOException {
        System.out.println("Contraseña que no cumpla con los criterios de Seguridad, no cumple.");
        assertFalse(miSistema.validarContrasenia("admin123", "password"));
    }
    @Test
    public void contraseniaMuyCorta() throws IOException {
        System.out.println("Contraseña que no cumple con el mínimo de 8 caracteres.");
        assertFalse(miSistema.validarContrasenia("admin123", "A12DH"));
    }

    @Test
    public void contraseniaDiferenteUsuario() throws IOException {
        System.out.println("Contraseña que cumple con los criterios de Seguridad, es una contraseña válida.");
        assertFalse(miSistema.validarContrasenia("admin", "admin"));
    }

    @Test
    public void validarContrasenia() throws IOException {
        System.out.println("Contraseña que cumple con los criterios de Seguridad, es una contraseña válida.");
        assertTrue(miSistema.validarContrasenia("admin123", "password1234ABC"));
    }

    @Test
    public void listadoDeHogares() throws IOException {
        System.out.println("Obtiene la primer página de la API de Hogares.");
        APIhogares apiService = APIhogares.getInstance();
        assertTrue(apiService.conjuntoHogares(1).size() > 0 );
    }
/*
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
        duenioMascota = new Duenio("Jorge", "Santos" , LocalDateTime.now().toLocalDate(), TipoDoc.DNI, 3541212, "+541128646857" , "cristianmanuelcali@hotmail.com", medios, null, null, mascotas);
        mascotaQueSePerdio = new Mascota("Popi", TipoAnimal.GATO, 5, SexoMascota.HEMBRA, "gordi", fotos, null, true, false, duenioMascota);
        duenioMascota.getMascotas().add(mascotaQueSePerdio);
        halladorMascota.setNombre("Claudio");
        halladorMascota.setApellido("Peterson");
    }

    @Test
    public void pruebaSMS(){
        System.out.println("Utilizando SMS como medio de Notificación.");
        medios.add(notiSMS);
        duenioMascota.getFormasDeNotificacion().forEach((unMedio -> unMedio.notificarMascotaEncontrada(duenioMascota, halladorMascota, mascotaQueSePerdio, link)));
        assertTrue(true);
    } // No usar a menos que sea necesario ya que descuenta saldo.

    @Test
    public void pruebaWhatsapp(){
        System.out.println("Utilizando Whatsapp como medio de Notificación.");
        duenioMascota.getFormasDeNotificacion().add(notiWPP);
        duenioMascota.getFormasDeNotificacion().forEach((unMedio -> unMedio.notificarMascotaEncontrada(duenioMascota, halladorMascota, mascotaQueSePerdio, link)));
        assertTrue(true);
    } // No usar a menos que sea necesario ya que descuenta saldo.

    @Test
    public void pruebaMail(){
        System.out.println("Utilizando el Mail como medio de Notificación.");
        duenioMascota.getFormasDeNotificacion().add(notiMail);
        duenioMascota.getFormasDeNotificacion().forEach((unMedio -> unMedio.notificarMascotaEncontrada(duenioMascota, halladorMascota, mascotaQueSePerdio, link)));
        assertTrue(true);
    } // No usar a menos que sea necesario ya que descuenta saldo.

    */
}






