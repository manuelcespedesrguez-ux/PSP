package Seguntri.TCPOO1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente1Objeto {
    public static void main(String[] arg) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int Puerto = 6000; // puerto remoto
        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket cliente = new Socket(Host, Puerto);

        // Flujo de entrada para objetos
        ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());
        
        // Se recibe el objeto inicial del servidor
        Persona dato = (Persona) perEnt.readObject();
        System.out.println("Recibo del servidor: " + dato.getNombre() + "*" + dato.getEdad());

        // Pedir los nuevos datos por teclado
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce tu nombre: ");
        String nuevoNombre = sc.nextLine().toUpperCase();
        
        System.out.print("Introduce tu edad: ");
        int nuevaEdad = sc.nextInt();
        int edadDefinitiva = nuevaEdad + 10;

        // Modifico el objeto con los datos introducidos
        dato.setNombre(nuevoNombre);
        dato.setEdad(edadDefinitiva);

        // Flujo de salida para objetos
        ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());
        
        // Se envía el objeto modificado al servidor
        perSal.writeObject(dato);
        System.out.println("Envio al servidor: " + dato.getNombre() + "*" + dato.getEdad());

        // CERRAR STREAMS, SCANNER Y SOCKETS
        sc.close();
        perEnt.close();
        perSal.close();
        cliente.close();
    }
}