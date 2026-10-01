package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente1 {
    
    public static void main(String[] args) {
        
        try {
            String host = "localhost";
            int port = 6000; // puerto remoto

            // ABRIR SOCKET
            Socket cliente = new Socket(host, port); // conecta

            InetAddress inetAdress = cliente.getInetAddress();
            System.out.println("Puerto local: " + cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + cliente.getPort());
            System.out.println("Host Remoto: " + inetAdress.getHostName());
            System.out.println("IP Host Remoto: " + inetAdress.getHostAddress());

            // CREO FLUJO DE SALIDA AL SERVIDOR
            OutputStream salida = cliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(salida);

            // ENVÍO UN SALUDO AL SERVIDOR
            flujoSalida.writeUTF("Saludos al servidor del cliente");

            // CREO FLUJO DE ENTRADA DESDE EL SERVIDOR
            InputStream entrada = cliente.getInputStream();
            DataInputStream flujoEntrada = new DataInputStream(entrada);

            // EL SERVIDOR ME ENVÍA UN MENSAJE
            System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());

            // CERRAR STREAMS Y SOCKETS
            entrada.close();
            flujoEntrada.close();
            salida.close();
            flujoSalida.close();
            cliente.close(); // Cierra el socket
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}