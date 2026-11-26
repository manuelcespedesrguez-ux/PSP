package Seguntri.TCPOO1;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Servidor1Objeto {
    @SuppressWarnings("unchecked")
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        int numeroPuerto = 6000;
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        System.out.println("Esperando al cliente.....");
        Socket cliente = servidor.accept();

        // Se prepara la lista inicial con Juan y se envía
        ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());
        ArrayList<Persona> listaInicial = new ArrayList<>();
        listaInicial.add(new Persona("Juan", 20));

        outObjeto.writeObject(listaInicial);
        System.out.println("Lista inicial enviada al cliente.");

        // Se recibe el ArrayList actualizado del cliente
        ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
        ArrayList<Persona> listaRecibida = (ArrayList<Persona>) inObjeto.readObject();

        // Mostrar los datos recibidos omitiendo a "Juan"
        System.out.println("\n--- Personas recibidas en el Servidor (sin Juan) ---");
        for (Persona p : listaRecibida) {
            if (!p.getNombre().equalsIgnoreCase("Juan")) {
                System.out.println("Recibo: " + p.getNombre() + " * " + p.getEdad());
            }
        }

        // Cerrar recursos
        outObjeto.close();
        inObjeto.close();
        cliente.close();
        servidor.close();
    }
}