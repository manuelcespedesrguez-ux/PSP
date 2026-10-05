package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente1 {
    
    public static void main(String[] args) {
        
        try {
            String host = "localhost";
            int port = 6000;

            Socket cliente = new Socket(host, port);

            InetAddress inetAddress = cliente.getInetAddress();
            System.out.println("Conectado a: " + inetAddress.getHostName() + " (" + inetAddress.getHostAddress() + ")");

            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            Scanner scanner = new Scanner(System.in);

            boolean continuar = true;

            while (continuar) {
                System.out.print("\nEscribe un mensaje para el servidor o escribe 'FIN' para terminar la comunicacion: ");
                String mensaje = scanner.nextLine();

                flujoSalida.writeUTF(mensaje);

                String respuesta = flujoEntrada.readUTF();
                System.out.println("REspuesta del servidor: " + respuesta);

                if (mensaje.equalsIgnoreCase("FIN")) {
                    continuar = false;
                }
            }

            // Cerramos flujos y conexiones
            scanner.close();
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            System.out.println("Cliente desconectado.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}