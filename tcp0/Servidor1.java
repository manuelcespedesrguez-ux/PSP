package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor1 {
    
    public static void main(String[] args) {
        
        try {
            int port = 6000;
            ServerSocket servidor = new ServerSocket(port);
            System.out.println("Escuchando en " + servidor.getLocalPort());

            Socket cliente = servidor.accept();
            System.out.println("\n--- Cliente conectado ---");
            System.out.println("Buenos días, padre cleadol. ¿Qué lo que tu quiere hacel?");

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            boolean continuar = true;

            while (continuar) {

                String mensajeCliente = flujoEntrada.readUTF();
                System.out.println("Recibido del cliente: " + mensajeCliente);

                if (mensajeCliente.equalsIgnoreCase("FIN")) {
                    continuar = false; 
                    flujoSalida.writeUTF("Conexión finalizada por el cliente.");
                } else {
                    flujoSalida.writeUTF("Mensaje recibido correctamente: " + ecoUpperCase(mensajeCliente));
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            servidor.close();
            System.out.println("Servidor cerrado correctamente.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String ecoUpperCase(String mensaje) {
        return mensaje.toUpperCase();
    }
}