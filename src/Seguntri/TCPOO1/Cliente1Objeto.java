package Seguntri.TCPOO1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente1Objeto {
    @SuppressWarnings("unchecked")
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int Puerto = 6000;
        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket cliente = new Socket(Host, Puerto);

        // Flujo de entrada para recibir la lista
        ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
        ArrayList<Persona> listaPersonas = (ArrayList<Persona>) perEnt.readObject();

        Scanner sc = new Scanner(System.in);
        String continuar;
        boolean esElPrimero = true; // Controla si sobrescribimos a Juan

        do {
            System.out.print("Introduce tu nombre: ");
            String nuevoNombre = sc.nextLine().toUpperCase();

            System.out.print("Introduce tu edad: ");
            int nuevaEdad = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            int edadDefinitiva = nuevaEdad + 10;

            if (esElPrimero) {
                // AQUÍ VA: Cogemos a Juan (posición 0) y le cambiamos los datos
                Persona dato = listaPersonas.get(0);
                dato.setNombre(nuevoNombre);
                dato.setEdad(edadDefinitiva);
                esElPrimero = false; // Los siguientes ya se añadirán como nuevos
            } else {
                // Para la segunda persona en adelante, creamos una nueva y la añadimos
                Persona p = new Persona();
                p.setNombre(nuevoNombre);
                p.setEdad(edadDefinitiva);
                listaPersonas.add(p);
            }

            System.out.print("¿Quieres añadir otra persona? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));

        // Mostrar toda la lista en el cliente
        for (Persona p : listaPersonas) {
            System.out.println("Envio al servidor: " + p.getNombre() + " * " + p.getEdad());
        }

        // Flujo de salida para enviar el ArrayList actualizado
        ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
        perSal.writeObject(listaPersonas);

        // Cerrar recursos
        sc.close();
        perEnt.close();
        perSal.close();
        cliente.close();
    }
}