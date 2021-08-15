package domain.views;
import domain.business.Sistema;
import domain.security.Usuario;

import java.util.Scanner;

public class MenuPrueba {

    public void iniciarMenu() {
        Scanner opciones = new Scanner(System.in);
        Scanner datosUsuario = new Scanner(System.in);
        boolean fin = false;
        int opcionElegida;
        Sistema miSistema = Sistema.getInstance();


        while(!fin) {
            System.out.println("¡Bienvenido/a a RescateDePatitas!");
            System.out.println("    - Para Iniciar Sesion: 1");
            System.out.println("    - Para Crear un Usuario: 2");
            System.out.println("    - Para Cerrar: 3");

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

                    miSistema.inicioSesion(usuarioLogged);
                    fin = true;

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
                    miSistema.crearUsuario(nombreUsuario, contrasenia);
                    System.out.println("El usuario ha sido creado correctamente.");
                    System.out.println("Continuando...");


                    break;
                case 3:
                    System.out.println("¡Gracias por visitar RescateDePatitas!");
                    fin = true;
                    break;
                default:
                    System.out.println("Se ha elegido una opción incorrecta. Intente nuevamente.");
                    break;
            }
        }



    }
}
