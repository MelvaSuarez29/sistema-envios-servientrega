import Envios.Envio;
import Envios.EnvioNacional;
import Envios.EnvioExpress;
import Envios.EnvioInternacional;

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
                    contador = registrarEnvio(sc, enTransito, contador);
                    break;
                case 2:
                    consultarEnvio(sc, enTransito);
                    break;
                case 3:
                    entregarEnvio(sc, enTransito, entregados);
                    break;
                case 4:
                    listarEntregados(entregados);
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

    public static int registrarEnvio(Scanner sc, HashMap<String, Envio> enTransito, int contador) {
        System.out.print("Tipo de servicio \n1 Nacional \n2 Express \n3 Internacional");
        System.out.print("\nSeleccione un tipo de servicio: ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Nombre del destinatario: ");
        String destinatario = sc.nextLine();

        System.out.print("Ciudad de destino: ");
        String ciudad = sc.nextLine();

        System.out.print("Peso en kilos: ");
        double peso = Double.parseDouble(sc.nextLine());

        if (peso <= 0) {
            System.out.println("Error: El peso debe ser mayor a cero. No se registró el envío.");
            return contador;
        }

        if (tipo == 1 || tipo == 2) {
            System.out.print("¿Entrega a domicilio? (S/N): ");
            boolean domicilio = sc.nextLine().equalsIgnoreCase("S");

            if (tipo == 1) {
                String guia = "NAC" + String.valueOf(contador);
                Envio envio = new EnvioNacional(guia, destinatario, ciudad, peso, domicilio);
                enTransito.put(guia, envio);
                contador++;
                System.out.println("Envío registrado. Guía: " + guia + " | Costo: $" + envio.calcularCosto());
            } else {
                System.out.print("¿El cliente eligió entrega en 24 horas? (S/N): ");
                boolean horas24 = sc.nextLine().equalsIgnoreCase("S");

                String guia = "EXP" + String.valueOf(contador);
                Envio envio;
                if (horas24) {
                    envio = new EnvioExpress(guia, destinatario, ciudad, peso, domicilio, 24);
                } else {
                    envio = new EnvioExpress(guia, destinatario, ciudad, peso, domicilio); // Usa el constructor por defecto de 48h
                }
                enTransito.put(guia, envio);
                contador++;
                System.out.println("Envío registrado. Guía: " + guia + " | Costo: $" + envio.calcularCosto());
            }
        } else if (tipo == 3) {
            System.out.print("Valor de la compra en dólares (FOB): ");
            double valorFob = Double.parseDouble(sc.nextLine());

            String guia = "INT" + String.valueOf(contador);
            EnvioInternacional envio = new EnvioInternacional(guia, destinatario, ciudad, peso, valorFob);

            if (!envio.cumple4x4()) {
                System.out.println("Envío RECHAZADO: No cumple la regla 4x4...");
                return contador;
            }

            enTransito.put(guia, envio);
            contador++;
            System.out.println("Envío registrado. Guía: " + guia + " | Costo: $" + envio.calcularCosto());
        } else {
            System.out.println("Tipo de servicio inválido. No se registró nada.");
        }

        return contador;
    }

    public static void consultarEnvio(Scanner sc, HashMap<String, Envio> enTransito) {
        System.out.print("Ingrese la guía a consultar: ");
        String guia = sc.nextLine();

        if (!enTransito.containsKey(guia)) {
            System.out.println("No existe un envío en tránsito con la guía " + guia);
            return;
        }

        Envio envio = enTransito.get(guia);
        System.out.println(envio.describir());
        System.out.println("Costo: $" + envio.calcularCosto());
    }

    public static void entregarEnvio(Scanner sc, HashMap<String, Envio> enTransito, ArrayList<Envio> entregados) {
        System.out.print("Ingrese la guía a entregar: ");
        String guia = sc.nextLine();

        if (!enTransito.containsKey(guia)) {
            System.out.println("No existe un envío en tránsito con la guía " + guia);
            return;
        }
        Envio envio = enTransito.remove(guia);
        entregados.add(envio);
        System.out.println("Envío " + guia + " marcado como ENTREGADO exitosamente.");
    }

    public static void listarEntregados(ArrayList<Envio> entregados) {
        if (entregados.isEmpty()) {
            System.out.println("Todavía no hay envíos entregados en el sistema.");
            return;
        }

        double totalRecaudado = 0.0;
        System.out.println("\n------------ ENVÍOS ENTREGADOS --------------");

        for (Envio envio : entregados) {
            System.out.println(envio.describir());
            System.out.println("Costo: $" + envio.calcularCosto());
            System.out.println("------------------------------------------");
            totalRecaudado += envio.calcularCosto();
        }
        System.out.println("TOTAL RECAUDADO: $" + totalRecaudado);
    }
}