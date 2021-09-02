package domain.views;
import domain.business.Contacto;
import domain.business.Persona;
import domain.business.Sistema;
import domain.business.TipoDoc;
import domain.business.organizaciones.apiHogares.APIhogares;
import domain.business.organizaciones.apiHogares.entidades.Hogar;
import domain.security.UserDuenio;
import domain.security.Usuario;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class MenuPrueba {

    private APIhogares apIhogares = APIhogares.getInstance();

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
            System.out.println("    - Para consultar los Hogares disponibles, ingrese 3.");
            System.out.println("    - Para Cerrar, ingrese 4.");

            System.out.print("Ingrese el comando: ");
            opcionElegida = opciones.nextInt();

            switch(opcionElegida) {
                case 1:
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

                    this.inicioSesion(usuarioLogged);

                    break;
                case 2:
                    System.out.print("Para crear un usuario, primero ingrese un nombre de usuario: ");
                    String nombreUsuario = datosUsuario.nextLine();
                    while(miSistema.existeUsuario(nombreUsuario)) {
                        System.out.print("El nombre de usuario ya existe, ingrese otro: ");
                        nombreUsuario = datosUsuario.nextLine();
                    }

                    System.out.print("Ingrese una contraseña: ");
                    String contrasenia = datosUsuario.nextLine();
                    while(!miSistema.validarContrasenia(contrasenia)) {
                        System.out.print("La contraseña no es válida, ingrese otra: ");
                        contrasenia = datosUsuario.nextLine();
                    }
                    System.out.print("Confirme la contraseña: ");
                    String contraseniaConfirmada = datosUsuario.nextLine();
                    while(!contrasenia.equals(contraseniaConfirmada)) {
                        System.out.print("La confirmación de la contraseña es incorrecta, pruebe otra vez: ");
                        contraseniaConfirmada = datosUsuario.nextLine();
                    }
                    Usuario usuarioRegistrado = miSistema.crearUsuario(nombreUsuario, contrasenia);
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
        Persona nuevaPersona = new Persona();
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        nuevaPersona.setNombre(entrada.nextLine());
        System.out.print("Ingrese su apellido: ");
        nuevaPersona.setApellido(entrada.nextLine());
        System.out.print("Ingrese su fecha de nacimiento: ");
        //String fechaNacimiento = entrada.nextLine();
        //nuevaPersona.setFechaDeNacimiento(this.crearFecha(fechaNacimiento));
        System.out.print("Elija su Tipo de Documento: ");
        //TipoDoc documentoElegido = this.elegirDocumento(entrada);
        //nuevaPersona.setTipoDocumento(documentoElegido);
        System.out.print("Ingrese su número de Documento: ");
        nuevaPersona.setNumeroDocumento(entrada.nextInt());
        System.out.print("Ingrese su número de teléfono o celular: ");
        nuevaPersona.setTelefono(entrada.nextLine());
        System.out.print("Ingrese su email: ");
        nuevaPersona.setEmail(entrada.nextLine());
        //System.out.print("Seleccione su forma de notificación preferida: ");
        //nuevaPersona.setFormasDeNotificacion();
        System.out.println("Ingrese sus contactos: ");
        this.agregarContacto(nuevaPersona, entrada);

        return nuevaPersona;
    }


    public void agregarContacto(Persona nuevaPersona, Scanner entrada){
        boolean salir = false;

        System.out.println("  - Si quiere cargar un Contacto, ingrese 1.");
        System.out.println("  - Si desea no cargar contactos, ingrese 2");
        int opcionElegida = entrada.nextInt();

        while(!salir) {
            switch(opcionElegida) {
                case 1:
                    Contacto nuevoContacto = new Contacto();
                    System.out.print("Ingrese el nombre del contacto: ");
                    nuevoContacto.setNombreContacto(entrada.nextLine());
                    System.out.print("Ingrese el apellido del contacto: ");
                    nuevoContacto.setApellidoContacto(entrada.nextLine());
                    System.out.print("Ingrese el teléfono o celular del contacto: ");
                    nuevoContacto.setTelefonoContacto(entrada.nextInt());
                    System.out.print("Ingrese el email del contacto: ");
                    nuevoContacto.setEmailContacto(entrada.nextLine());
                   // System.out.print("Ingrese una forma de notificación preferida del contacto: ");
                   // nuevoContacto.setFormasDeNotificacionContacto();

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

    public void inicioSesion(Usuario usuarioLogin) {
        Scanner entrada = new Scanner(System.in);
        Sistema miSistema = Sistema.getInstance();
        boolean salir = false;
        System.out.println("    - Para Registrar a una mascota, ingrese 1.");
        System.out.println("    - Para Ver las mascotas perdidas, ingrese 2");
        System.out.println("    - Para Adoptar a una mascota, ingrese 3");
        System.out.println("    - Para Cerrar sesión, ingrese 4");
        int opcion = entrada.nextInt();

        while(!salir) {
            switch(opcion) {
                case 1:
                    // Todo: todo lo relacionado al registro de una mascota, se haria el formulario
                    if(usuarioLogin.getRol().puedoRegistrarMascota()) {

                      //aunque el registrarMascota es mas de Duenio, tal vez hay que poner que Duenio se crea cuando creas un usuario y meter todos los datos extras
                    }
                    break;
                case 2:
                    miSistema.mostrarMascotasPerdidas();
                    break;
                case 3:
                    // Todo: todo lo relacionado a querer adoptar a una mascota
                    break;
                case 4:
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

    public void cerrarSesion() {
        // Todo: aca habria que cerrar sesion, creo que implementando algo de seguridad
        return;
    }
}
