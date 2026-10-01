package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {
    
    public static void main(String[] args) {
        
        try {
            int port = 6000; // puerto
            ServerSocket servidor = new ServerSocket(port);
            System.out.println("Escuchando en " + servidor.getLocalPort());

            // --- CLIENTE 1 ---
            Socket cliente1 = servidor.accept(); // esperando a un cliente
            System.out.println("\n--- Cliente 1 conectado ---");

            InputStream entrada1 = cliente1.getInputStream();
            DataInputStream flujoEntrada1 = new DataInputStream(entrada1);
            // EL CLIENTE ME ENVÍA UN MENSAJE
            System.out.println("Recibiendo del CLIENTE 1: \n\t" + flujoEntrada1.readUTF());

            OutputStream salida1 = cliente1.getOutputStream();
            DataOutputStream flujoSalida1 = new DataOutputStream(salida1);
            // ENVÍO UN SALUDO AL CLIENTE
            flujoSalida1.writeUTF("Buenos días cliente 1 del servidor");

            // CERRAR STREAMS Y SOCKETS CLIENTE 1
            entrada1.close();
            flujoEntrada1.close();
            salida1.close();
            flujoSalida1.close();
            cliente1.close();

            // --- CLIENTE 2 ---
            Socket cliente2 = servidor.accept(); // esperando a otro cliente
            System.out.println("\n--- Cliente 2 conectado ---");

            InputStream entrada2 = cliente2.getInputStream();
            DataInputStream flujoEntrada2 = new DataInputStream(entrada2);
            // EL CLIENTE ME ENVÍA UN MENSAJE
            System.out.println("Recibiendo del CLIENTE 2: \n\t" + flujoEntrada2.readUTF());

            OutputStream salida2 = cliente2.getOutputStream();
            DataOutputStream flujoSalida2 = new DataOutputStream(salida2);
            // ENVÍO UN SALUDO AL CLIENTE
            flujoSalida2.writeUTF("Saludos al cliente 2 del servidor");

            // CERRAR STREAMS Y SOCKETS CLIENTE 2
            entrada2.close();
            flujoEntrada2.close();
            salida2.close();
            flujoSalida2.close();
            cliente2.close();

            servidor.close(); // cierro socket servidor
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}