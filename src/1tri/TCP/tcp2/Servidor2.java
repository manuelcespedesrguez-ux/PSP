package TCP.tcp2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    
    public static void main(String[] args) {
        
        try {
            int port = 6000;
            ServerSocket servidor = new ServerSocket(port);
            System.out.println("Escuchando en " + servidor.getLocalPort());

            Socket cliente = servidor.accept();
            System.out.println("\n--- Cliente conectado ---");

            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            boolean continuar = true;

            while (continuar) {
                int numeroCliente = flujoEntrada.readInt();
                System.out.println("Recibido del cliente el número: " + numeroCliente);

                if (numeroCliente == 0) {
                    continuar = false; 
                    flujoSalida.writeUTF("Conexión finalizada por el cliente.");
                } else {
                    boolean esPerfecto = LogicaNPerfecto.esNumeroPerfecto(numeroCliente);
                    
                    if (esPerfecto) {
                        flujoSalida.writeUTF("El número " + numeroCliente + " ES un número perfecto.");
                    } else {
                        flujoSalida.writeUTF("El número " + numeroCliente + " NO es un número perfecto.");
                    }
                }
            }

            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            servidor.close();
            System.out.println("Adiós papanatas");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}