package domain.views;
import domain.business.Sistema;
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
