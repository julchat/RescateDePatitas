package domain.views;
import domain.business.*;
import domain.business.caracteristicas.Caracteristica;
import domain.business.caracteristicas.CaracteristicaMascota;
import domain.business.foto.Foto;
import domain.business.notificaciones.Notificacion;
import domain.business.notificaciones.NotificadorEmail;
import domain.business.notificaciones.NotificadorSms;
import domain.business.notificaciones.NotificadorWhatsapp;
import domain.business.organizaciones.apiHogares.APIhogares;
import domain.business.organizaciones.apiHogares.entidades.Hogar;
import domain.security.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class MenuPrueba {

    private APIhogares apIhogares = APIhogares.getInstance();
    private Sistema miSistema = Sistema.getInstance();

    public void iniciarMenu() throws IOException {
        Scanner opciones = new Scanner(System.in);
        Scanner datosUsuario = new Scanner(System.in);
        boolean salir = false;
        int opcionElegida;
        Sistema miSistema = Sistema.getInstance();


        while(!salir) {
            System.out.println("¡Bienvenido/a a RescateDePatitas!");
            System.out.println("    - Para Iniciar Sesion, ingrese 1.");
            System.out.println("    - Para Crear un Usuario, ingrese 2.");

            // Esto es por ahora
            System.out.println("    - Para consultar los Hogares disponibles, ingrese 3.");

            System.out.println("    - Para ver las Mascotas Perdidas, ingrese 4.");
            System.out.println("    - Para Reportar una Mascota Perdida, ingrese 5.");
            System.out.println("    - Para querer Adoptar a una Mascota, ingrese 6.");
            System.out.println("    - Para Cerrar, ingrese 7.");

            System.out.print("Ingrese el comando: ");
            opcionElegida = opciones.nextInt();

            switch(opcionElegida) {
                case 1: // Inicio de Sesion
                    System.out.print("Ingrese su nombre de usuario: ");
                    String usuarioLogin = datosUsuario.nextLine();
                    while(!miSistema.existeUsuario(usuarioLogin)) {
                        System.out.print("Usuario incorrecto. Por favor ingrese nuevamente: ");
                        usuarioLogin = datosUsuario.nextLine();
                    }

                    System.out.print("Ingrese la contraseña: ");
                    String contraseniaLogin = datosUsuario.nextLine();
                    int intentos = 0;
                    while(!miSistema.coincideContrasenia(usuarioLogin, contraseniaLogin)) {
                        System.out.print("Contraseña incorrecta. Por favor ingrese nuevamente: ");
                        contraseniaLogin = datosUsuario.nextLine();
                        intentos++;
                        if(intentos == 3) {
                            System.out.println("Ha realizado 3 intentos consecutivos y erróneos. Por favor espere unos segundos e intente nuevamente.");
                            // TODO: sleep(30) o algo asi
                        }
                    }
                    System.out.println("¡Bienvenido de vuelta " + usuarioLogin + "!");
                    Usuario usuarioLogged = miSistema.buscarUsuario(usuarioLogin);

                    if(usuarioLogged.getRol().puedoAprobarPublicaciones()) {
                        this.inicioSesionModerador(usuarioLogged);
                    }
                    else if(usuarioLogged.getRol().puedoCrearAdministradores()) {
                        this.inicioSesionAdmin(usuarioLogged);
                    }
                    else {
                        this.inicioSesionUser(usuarioLogged);
                    }

                    break;
                case 2: // Creación de Nuevo Usuario
                    System.out.print("Para crear un usuario, primero ingrese un nombre de usuario: ");
                    String nombreUsuario = datosUsuario.next();
                    while(miSistema.existeUsuario(nombreUsuario)) {
                        System.out.print("El nombre de usuario ya existe, ingrese otro: ");
                        nombreUsuario = datosUsuario.next();
                    }

                    System.out.print("Ingrese una contraseña: ");
                    String contrasenia = datosUsuario.next();
                    while(!miSistema.validarContrasenia(contrasenia)) {
                        System.out.print("La contraseña no es válida, ingrese otra: ");
                        contrasenia = datosUsuario.next();
                    }
                    System.out.print("Confirme la contraseña: ");
                    String contraseniaConfirmada = datosUsuario.next();
                    while(!contrasenia.equals(contraseniaConfirmada)) {
                        System.out.print("La confirmación de la contraseña es incorrecta, pruebe otra vez: ");
                        contraseniaConfirmada = datosUsuario.next();
                    }
                    Usuario usuarioRegistrado = miSistema.crearUsuario(nombreUsuario, contrasenia);

                    // TODO: por ahora todos serian Dueños
                    usuarioRegistrado.setPersona(this.crearPersona());
                    System.out.println("El usuario ha sido creado correctamente.");
                    System.out.println("Continuando...");

                    break;
                case 3:
                    System.out.println("En total, hay " + apIhogares.cantidadHogares() + " hogares en " + apIhogares.cantidadPaginas() + " paginas.");

                    for(int i=1; i<= apIhogares.cantidadPaginas(); i++) {
                        List<Hogar> hogares = apIhogares.conjuntoHogares(i);
                        for(Hogar hogar : hogares) {
                            System.out.println("ID: " + hogar.getId());
                            System.out.println("NOMBRE: " + hogar.getNombre());
                            System.out.println("TELEFONO: " + hogar.getTelefono());
                            System.out.println("CAPACIDAD: " + hogar.getCapacidad());
                            System.out.println("CARACTERISTICAS: " + hogar.getCaracteristicas());
                            System.out.println("DIRECCION: " + hogar.getUbicacion().getDireccion());
                            System.out.println("LATITUD: " + hogar.getUbicacion().getLat());
                            System.out.println("LONGITUD: " + hogar.getUbicacion().getLongitud());
                            System.out.println("-------------------------------------------------------------------");
                        }
                    }

                    break;
                case 4:
                    this.mascotasPerdidas();
                    break;
                case 5:
                    this.reportarMascotaPerdida();
                    break;
                case 6:
                    this.adoptarMascota();
                    break;
                case 7:
                    System.out.println("¡Gracias por visitar RescateDePatitas!");
                    salir = true;
                    break;
                default:
                    System.out.println("Se ha elegido una opción incorrecta. Intente nuevamente.");
                    break;
            }
        }
        exit(0);
    }

    public Persona crearPersona() {
        Duenio nuevaPersona = new Duenio();
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        nuevaPersona.setNombre(entrada.next());
        System.out.print("Ingrese su apellido: ");
        nuevaPersona.setApellido(entrada.next());
        System.out.print("Ingrese su email: ");
        nuevaPersona.setEmail(entrada.next());
        System.out.print("Ingrese su fecha de nacimiento: ");
        nuevaPersona.setFechaDeNacimiento(this.crearFecha(entrada.next()));
        System.out.println("Elija su Tipo de Documento: ");
        nuevaPersona.setTipoDocumento(this.elegirDocumento(entrada));
        System.out.print("Ingrese su número de Documento: ");
        nuevaPersona.setNumeroDocumento(entrada.nextInt());
        System.out.print("Ingrese su número de teléfono o celular: ");
        nuevaPersona.setTelefono(entrada.next());
        System.out.println("Seleccione su forma de notificación preferida: ");
        nuevaPersona.setFormasDeNotificacion(this.elegirNotificacionesPreferidas(entrada));
        System.out.println("Ingrese sus contactos: ");
        this.agregarContacto(nuevaPersona, entrada);

        return nuevaPersona;
    }

    private LocalDate crearFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(fecha, formatter);
        return localDate;
    }

    private TipoDoc elegirDocumento(Scanner entrada) {
        System.out.println("        - 1) DNI");
        System.out.println("        - 2) CEDULA");
        System.out.println("        - 3) PASAPORTE");
        System.out.print("> ");
        int opcionElegida = entrada.nextInt();
        boolean salir = false;
        TipoDoc eleccion = null;

        while(!salir) {
            switch(opcionElegida) {
                case 1:
                    eleccion = TipoDoc.DNI;
                    salir = true;
                    break;
                case 2:
                    eleccion = TipoDoc.CEDULA;
                    salir = true;
                    break;
                case 3:
                    eleccion = TipoDoc.PASAPORTE;
                    salir = true;
                    break;
                default:
                    System.out.println("La opción que ha elegido es incorrecta. Por favor ingrese nuevamente.");
                    break;
            }
        }
        return eleccion;
    }

    private List<Notificacion> elegirNotificacionesPreferidas(Scanner entrada) {
        List<Notificacion> notificacionesElegidas = new ArrayList<>();
        int cantidadNotificaciones = 0;
        boolean salir = false;
        int opcionElegida;

        while(cantidadNotificaciones < 3 && !salir) {

            System.out.println("    - 1) Email");
            System.out.println("    - 2) Sms");
            System.out.println("    - 3) WhatsApp");
            System.out.print(">");
            opcionElegida = entrada.nextInt();

            switch(opcionElegida) {
                case 1:
                    if(notificacionesElegidas.stream().anyMatch(notificacion -> notificacion.obtenerCodigoNotificacion() == 1)) {
                        System.out.println("No puede agregar un medio que ya está agregado.");
                    }
                    else {
                        notificacionesElegidas.add(new NotificadorEmail());
                        System.out.println("Se agregó el medio Email como preferencia.");
                        cantidadNotificaciones++;
                    }
                    break;
                case 2:
                    if(notificacionesElegidas.stream().anyMatch(notificacion -> notificacion.obtenerCodigoNotificacion() == 2)) {
                        System.out.println("No puede agregar un medio que ya está agregado.");
                    }
                    else {
                        notificacionesElegidas.add(new NotificadorSms());
                        System.out.println("Se agregó el medio Sms como preferencia.");
                        cantidadNotificaciones++;
                    }
                    break;
                case 3:
                    if(notificacionesElegidas.stream().anyMatch(notificacion -> notificacion.obtenerCodigoNotificacion() == 3)) {
                        System.out.println("No puede agregar un medio que ya está agregado.");
                    }
                    else {
                        notificacionesElegidas.add(new NotificadorWhatsapp());
                        System.out.println("Se agregó el medio Whatsapp como preferencia.");
                        cantidadNotificaciones++;
                    }
                    break;
                default:
                    System.out.println("La opción que ha elegido es incorrecta. Por favor ingrese nuevamente.");
                    break;
            }

            System.out.println("Si desea continuar con el medio elegido, ingrese 1.");
            System.out.println("Si desea cargar otro medio de notificación preferido, ingrese cualquier otra opción.");
            System.out.print(">");
            opcionElegida = entrada.nextInt();

            if(opcionElegida == 1) { salir = true; }

        }
        return notificacionesElegidas;
    }

    private void agregarContacto(Persona nuevaPersona, Scanner entrada){
        boolean salir = false;

        System.out.println("  - Si quiere cargar un Contacto, ingrese 1.");
        System.out.println("  - Si desea no cargar contactos, ingrese 2");
        int opcionElegida = entrada.nextInt();

        while(!salir) {
            switch(opcionElegida) {
                case 1:
                    Contacto nuevoContacto = new Contacto();
                    System.out.print("Ingrese el nombre del contacto: ");
                    nuevoContacto.setNombreContacto(entrada.next());
                    System.out.print("Ingrese el apellido del contacto: ");
                    nuevoContacto.setApellidoContacto(entrada.next());
                    System.out.print("Ingrese el teléfono o celular del contacto: ");
                    nuevoContacto.setTelefonoContacto(entrada.nextInt());
                    System.out.print("Ingrese el email del contacto: ");
                    nuevoContacto.setEmailContacto(entrada.next());
                    System.out.println("Ingrese una forma de notificación preferida del contacto: ");
                    nuevoContacto.setFormasDeNotificacionContacto(this.elegirNotificacionesPreferidas(entrada));

                    nuevaPersona.agregarContacto(nuevoContacto);

                    System.out.println("  - Si quiere cargar un nuevo Contacto, ingrese 1.");
                    System.out.println("  - Si quiere terminar con la carga de Contactos, ingrese 2");
                    opcionElegida = entrada.nextInt();
                    break;
                case 2:
                    salir = true;
                    break;
                default:
                    System.out.println("Ha ingresado una opción incorrecta.");
                    System.out.println("  - Si quiere cargar un nuevo Contacto, ingrese 1.");
                    System.out.println("  - Si quiere terminar con la carga de Contactos, ingrese 2");
                    opcionElegida = entrada.nextInt();
                    break;
            }
        }
    }

    public void inicioSesionUser(Usuario usuarioLogin) {
        Scanner entrada = new Scanner(System.in);
        Sistema miSistema = Sistema.getInstance();
        boolean salir = false;
        System.out.println("    - Para Registrar a una Mascota, ingrese 1.");
        System.out.println("    - Para Ver las Mascotas Perdidas, ingrese 2.");
        System.out.println("    - Para Adoptar a una Mascota, ingrese 3.");
        System.out.println("    - Para mostrar las Mascotas Registradas, ingrese 4.");
        System.out.println("    - Para Cerrar sesión, ingrese 5.");
        int opcion = entrada.nextInt();

        while(!salir) {
            switch(opcion) {
                case 1:
                    // Solo puedo registrar una mascota si tengo los permisos adecuados, es decir, si soy un User
                    if(usuarioLogin.getRol().puedoRegistrarMascota()) {
                        Mascota mascota = this.registrarMascota();
                        mascota.setEncargado(usuarioLogin.getPersona());
                        ((Duenio) usuarioLogin.getPersona()).registrarMascota(mascota);


                        if(((Duenio) usuarioLogin.getPersona()).getDomicilio() == null) {
                            System.out.println("Ingrese los datos del Domicilio del Dueño: ");
                            ((Duenio) usuarioLogin.getPersona()).cambiarDomicilio(this.datosDomicilio(entrada));
                        }
                        else {
                            System.out.println("El usuario ya tiene un Domicilio cargado.");
                            // Todo: se podria preguntar si quiere cambiarlo, aunque eso tambien podria estar en la parte de Editar Perfil del Usuario, para actualizar datos
                        }
                    }
                    break;
                case 2:
                    this.mascotasPerdidas();
                    break;
                case 3:
                    this.adoptarMascota();
                    break;
                case 4:
                    List<Mascota> mascotasRegistradas = ((Duenio)usuarioLogin.getPersona()).getMascotas();
                    for(Mascota mascota : mascotasRegistradas) {
                        System.out.println("Tipo del Animal: " + mascota.getTipoAnimal());
                        System.out.println("Nombre de la Mascota: " + mascota.getNombreMascota());
                        System.out.println("Apodo de la Mascota: " + mascota.getApodoMascota());
                        System.out.println("Edad de la Mascota: " + mascota.getEdadMascota());
                        System.out.println("Sexo de la Mascota: " + mascota.getSexoMascota());
                        System.out.println("Descripción de la Mascota: " + mascota.getDescripcionMascota());
                        System.out.println("Características de la Mascota: " + mascota.getCaracteristicasMascota());
                        System.out.println("---------------------------------------------------------------------");
                    }
                    break;
                case 5:
                    System.out.println("¡Gracias por visitar RescateDePatitas!");
                    this.cerrarSesion();
                    salir = true;
                    break;
                default:
                    System.out.print("Ha ingresado una opción incorrecta. Por favor intente nuevamente: ");
                    opcion = entrada.nextInt();
                    break;
            }
        }
    }

    private Mascota registrarMascota() {
        Scanner entrada = new Scanner(System.in);
        Mascota nuevaMascota = new Mascota();

        System.out.println("Seleccione que tipo de animal quiere registrar: ");
        nuevaMascota.setTipoAnimal(this.eleccionTipoAnimal(entrada));
        System.out.print("Ingrese el nombre completo de la mascota: ");
        nuevaMascota.setNombreMascota(entrada.next());
        System.out.print("Ingrese el apodo de la mascota: ");
        nuevaMascota.setApodoMascota(entrada.next());
        System.out.print("Ingrese la edad aproximada de la mascota: ");
        nuevaMascota.setEdadMascota(entrada.nextInt());
        System.out.println("Seleccione el sexo de la mascota: ");
        nuevaMascota.setSexoMascota(this.eleccionSexoMascota(entrada));
        System.out.println("Ingrese una breve descripción de la mascota: ");
        nuevaMascota.setDescripcionMascota(entrada.next());
        System.out.println("Seleccione alguna/s fotos de su mascota: ");
        nuevaMascota.setFotos(this.eleccionFotos(entrada));
        System.out.println("Seleccione las características que se adecuan a su mascota: ");
        nuevaMascota.setCaracteristicasMascota(this.eleccionCaracteristicas(entrada));

        return nuevaMascota;
    }

    private TipoAnimal eleccionTipoAnimal(Scanner entrada) {
        boolean salir = false;
        int opcionElegida;
        TipoAnimal animalElegido = null;
        System.out.println("    - Si es un Perro, ingrese 1.");
        System.out.println("    - Si es un Gato, ingrese 2.");
        System.out.print("> ");
        opcionElegida = entrada.nextInt();

        while(!salir) {
            switch(opcionElegida) {
                case 1:
                    animalElegido = TipoAnimal.PERRO;
                    salir = true;
                    break;
                case 2:
                    animalElegido = TipoAnimal.GATO;
                    salir = true;
                    break;
                default:
                    System.out.print("Ha ingresado una opción incorrecta. Por favor intente nuevamente: ");
                    break;
            }
        }
        return animalElegido;
    }

    private SexoMascota eleccionSexoMascota(Scanner entrada) {
        int opcionElegida;
        SexoMascota sexoElegido = null;
        System.out.println("    - Si su mascota es Macho, ingrese 1.");
        System.out.println("    - Si su mascota es Hembra, ingrese 2.");
        System.out.print("> ");
        opcionElegida = entrada.nextInt();

        switch(opcionElegida) {
            case 1:
                sexoElegido = SexoMascota.MACHO;
                break;
            case 2:
                sexoElegido = SexoMascota.HEMBRA;
                break;
            default:
                System.out.print("Ha ingresado una opción incorrecta. Por favor intente nuevamente: ");
                break;
        }

        return sexoElegido;
    }

    private List<Foto> eleccionFotos(Scanner entrada) {
        List<Foto> fotos = new ArrayList<>();


        // TODO: Metodo para elegir una foto desde la computadora y agregarla en un carrousel para subirla a la base de datos

        return fotos;
    }

    private List<CaracteristicaMascota> eleccionCaracteristicas(Scanner entrada) {
        List<CaracteristicaMascota> caracteristicasMascota = new ArrayList<>();

        /*
        for(Caracteristica unaCaracteristica : listaCaracteristicas) {
            ELEGIR LAS CARACTERISTICAS ADECUADAS
        }*/

        return caracteristicasMascota;
    }

    private Domicilio datosDomicilio(Scanner entrada) {
        System.out.println("DOMICILIO DEL DUEÑO/A");
        Domicilio nuevoDomicilio = new Domicilio();

        System.out.println("Ingrese la Provincia: ");
        nuevoDomicilio.setProvincia(entrada.next());
        System.out.println("Ingrese la Localidad: ");
        nuevoDomicilio.setLocalidad(entrada.next());
        System.out.println("Ingrese su Código Postal: ");
        nuevoDomicilio.setCodigoPostal(entrada.nextInt());
        System.out.println("Ingrese el nombre de la Calle: ");
        nuevoDomicilio.setCalle(entrada.next());
        System.out.println("Ingrese la Numeración: ");
        nuevoDomicilio.setNumero(entrada.nextInt());
        System.out.println("Ingrese el número de Departamento: (OPCIONAL)");
        nuevoDomicilio.setDepartamento(entrada.nextInt());
        System.out.println("Ingrese el piso: (OPCIONAL)");
        nuevoDomicilio.setPiso(entrada.nextInt());

        return nuevoDomicilio;
    }

    public void mascotasPerdidas() {
        miSistema.mostrarMascotasPerdidas();
    }

    public void reportarMascotaPerdida() {
        Scanner entrada = new Scanner(System.in);
        Persona nuevaPersona = this.generarFormularioRescatista(entrada);

        // nuevaPersona iria a la publicacion

        MascotaPerdida mascotaPerdida = this.generarFormularioMascotaPerdida(entrada);

        // mascotaPerdida se agrega en la publicacion y se agrega a la lista de mascotas perdidas
        miSistema.agregarMascotaPerdida(mascotaPerdida);
    }

    private Rescatista generarFormularioRescatista(Scanner entrada) {
        Rescatista nuevoRescatista = new Rescatista();

        System.out.print("Ingrese su nombre: ");
        nuevoRescatista.setNombre(entrada.next());
        System.out.print("Ingrese su apellido: ");
        nuevoRescatista.setApellido(entrada.next());
        System.out.print("Ingrese su fecha de nacimiento: ");
        nuevoRescatista.setFechaDeNacimiento(this.crearFecha(entrada.next()));
        System.out.println("Elija el tipo de documento: ");
        nuevoRescatista.setTipoDocumento(this.elegirDocumento(entrada));
        System.out.print("Ingrese su número de documento: ");
        nuevoRescatista.setNumeroDocumento(entrada.nextInt());
        System.out.print("Ingrese su número de teléfono: ");
        nuevoRescatista.setTelefono(entrada.next());
        System.out.print("Ingrese su email: ");
        nuevoRescatista.setEmail(entrada.next());
        System.out.println("Seleccione su forma de notificación preferida: ");
        nuevoRescatista.setFormasDeNotificacion(this.elegirNotificacionesPreferidas(entrada));
        System.out.println("Ingrese sus contactos: ");
        this.agregarContacto(nuevoRescatista, entrada);

        return nuevoRescatista;
    }

    private MascotaPerdida generarFormularioMascotaPerdida(Scanner entrada) {
        MascotaPerdida nuevaMascotaPerdida = new MascotaPerdida();

        System.out.println("Seleccione el tipo de animal de la mascota encontrada: ");
        nuevaMascotaPerdida.setTipoAnimal(this.eleccionTipoAnimal(entrada));
        System.out.println("Seleccione el tamaño de la mascota encontrada: ");
        nuevaMascotaPerdida.setTamanio(this.eleccionTamanio(entrada));
        System.out.print("Ingrese la descripción de la mascota encontrada: ");
        nuevaMascotaPerdida.setDescripcion(entrada.next());
        System.out.println("Indique la ubicación donde fue encontrada la mascota: ");
        nuevaMascotaPerdida.setUbicacionEncontrada(this.ingresarUbicacionEncontrada(entrada));
        System.out.println("Ingrese el carrousel de fotos de la mascota encontrada: ");
        nuevaMascotaPerdida.setCarrouselFotos(this.eleccionFotos(entrada));

        return nuevaMascotaPerdida;
    }

    private Tamanio eleccionTamanio(Scanner entrada) {
        Tamanio nuevoTamanio = null;
        int opcionElegida;
        boolean salir = false;

        System.out.println("    - Si es Pequeña, ingrese 1.");
        System.out.println("    - Si es Mediana, ingrese 2.");
        System.out.println("    - Si es Grande, ingrese 3.");
        System.out.print("> ");
        opcionElegida = entrada.nextInt();

        while(!salir) {
            switch(opcionElegida) {
                case 1:
                    nuevoTamanio = Tamanio.PEQUENIA;
                    salir = true;
                    break;
                case 2:
                    nuevoTamanio = Tamanio.MEDIANA;
                    salir = true;
                    break;
                case 3:
                    nuevoTamanio = Tamanio.GRANDE;
                    salir = true;
                    break;
                default:
                    System.out.print("Ha ingresado una opción incorrecta. Por favor intente nuevamente: ");
                    break;
            }
        }
        return nuevoTamanio;
    }

    private Ubicacion ingresarUbicacionEncontrada(Scanner entrada) {
        Ubicacion nuevaUbicacion = new Ubicacion();

        System.out.println("    - Ingrese la latitud en el mapa: ");
        nuevaUbicacion.setLatitud(entrada.nextDouble());
        System.out.println("    - Ingrese la longitud en el mapa: ");
        nuevaUbicacion.setLongitud(entrada.nextDouble());

        // TODO: en este caso estaria una extensión del google maps, y se lo elige a mano, de ahi te devuelve la longitud y latitud de la ubicacion

        return nuevaUbicacion;
    }

    public void adoptarMascota() {
        /* Todo: obtener las mascotas que estan en adopcion
                - elegir una de esas mascotas o pasar de largo
                - si se elige una, notificar al dueño de dicha mascota
                - otra opcion es crear una publicacion para buscar una mascota que se adecue a las preferencias del adoptante
        */
    }

    public void inicioSesionAdmin(Usuario usuario) {
        Scanner entrada = new Scanner(System.in);
        int opcionElegida;
        boolean salir = false;

        while(!salir) {
            System.out.println("    - Para hacer nuevos Administradores, ingrese 1.");
            System.out.println("    - Para cambiar los estandares de una Organización, ingrese 2.");
            System.out.println("    - Para volver al Menu Principal, ingrese 3.");
            System.out.print("> ");
            opcionElegida = entrada.nextInt();

            switch (opcionElegida) {
                case 1: // Hacer nuevos Admins
                    // Elegir alguno de los usuarios para hacerlo Admin
                    miSistema.getUsuarios();
                   //usuario.hacerAdministrador(usuarioElegido);
                    break;
                case 2: // Administrar una Organizacion, ya sea agregando caracteristicas o modificandolas
                    break;
                case 3: // De vuelta al Menu Principal
                    salir = true;
                    break;
                default:
                    System.out.println("La opción elegida es incorrecta. Por favor intente nuevamente.");
                    break;
            }
        }
    }

    public void inicioSesionModerador(Usuario usuario) {

        // TODO: obtener las publicaciones que estan PENDIENTES
        // - Verificar 1 x 1?
        miSistema.getPublicaciones();
        // - Luego de ver 1 publicacion, tener 2 opciones: 1) APROBAR 2) DESAPROBAR, y eso cambiar el estado de dicha publicacion

    }

    public void cerrarSesion() {
        // Todo: aca habria que cerrar sesion, creo que implementando algo de seguridad
        return;
    }
}
