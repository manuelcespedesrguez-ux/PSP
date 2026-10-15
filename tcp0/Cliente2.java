package tcp0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    
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
                int numero = -1;

                while (numero < 0) {
                    System.out.print("\nIntroduce un número entero positivo, escribe 0 para salir: ");
                    if (scanner.hasNextInt()) {
                        numero = scanner.nextInt();
                        if (numero < 0) {
                            System.out.println("Error: El número debe ser positivo.");
                        }
                    } else {
                        System.out.println("Error: Debes introducir un número entero válido.");
                        scanner.next(); 
                    }
                }

                flujoSalida.writeInt(numero);

                String respuesta = flujoEntrada.readUTF();
                System.out.println("Respuesta del servidor: " + respuesta);

                if (numero == 0) {
                    continuar = false;
                }
            }

            scanner.close();
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            System.out.println("El cliente es un papanatas y se fue.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}