package domain;


import domain.strategyEnvios.Estandar;
import domain.strategyEnvios.FedEx;
import domain.strategyEnvios.StrategyEnvio;
import domain.strategyEnvios.Ups;

public class main {

    public static void main(String[] args) {
        Direccion direccion1 = new Direccion("Calle Falsa 123", "CABA", "Almagro", 123);
        Direccion direccion2 = new Direccion("Av. Corrientes", "CABA", "Almagro", 123);
        Direccion direccion3 = new Direccion("Cachito", "Cordoba", "Carlos Paz", 442);

        StrategyEnvio estandar = new Estandar(20,40);
        StrategyEnvio fedEx = new FedEx(30);
        StrategyEnvio ups = new Ups(25,10);

        Paquete paquete = new Paquete(5);

        Envio envio1 = new Envio(direccion1, direccion2, paquete, estandar);
        Envio envio2 = new Envio(direccion1, direccion3, paquete, estandar);
        Envio envio3 = new Envio(direccion1, direccion2, paquete, fedEx);
        Envio envio4 = new Envio(direccion2, direccion3, paquete, ups);

        System.out.println("Precio de un paquete de 5kg según las distintas estrategias:");
        System.out.println();
        System.out.println("Segun la estrategia Estandar: ");
        int precio1 = estandar.calcularPrecio(envio1);
        System.out.println("     - Precio Envio1: " + precio1);
        int precio2 = estandar.calcularPrecio(envio2);
        System.out.println("     - Precio Envio2: " + precio2);

        System.out.println("Segun la estrategia FedEx: ");
        int precio3 = fedEx.calcularPrecio(envio3);
        System.out.println("     - Precio Envio1: " + precio3);

        System.out.println("Segun la estrategia Ups: ");
        int precio4 = ups.calcularPrecio(envio4);
        System.out.println("     - Precio Envio4: " + precio4);

    }
}
