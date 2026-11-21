import Envios.Envio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SistemaEnvios {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<String, Envio> enTransito = new HashMap<>();
        ArrayList<Envio> entregados = new ArrayList<>();
        int contador = 1001;
        int opcion;

        do {
            mostrarMenu();
            opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 5) {
                System.out.println("Acaba de salir del sistema");
                break;
            }

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (true);
        sc.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n---------------SISTEMA DE ENVÍOS SERVIENTREGA-------------");
        System.out.println("1. Registrar envío");
        System.out.println("2. Consultar Envio");
        System.out.println("3. Entregar Envio");
        System.out.println("4. Listar Entregados");
        System.out.println("5. Salir");
        System.out.print("Seleccionar una opcion: ");
    }
}