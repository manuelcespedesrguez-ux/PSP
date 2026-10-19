package tcp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente3 {
    
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

            for (int i = 1; i < 7; i++) {
                int numero = -1;

                while (numero < 1 || numero > 49) {
                    System.out.println("Introduce el número " + i + " comprendido entre 1 y 49");
                    if (scanner.hasNextInt()) {
                        numero = scanner.nextInt();
                        if (numero < 1 || numero > 49) {
                            System.out.println("ERROR: El número debe estar comprendido entre 1 y 49");
                        }
                    } else {
                        System.out.println("ERROR: El número debes introducir un número entero válido");
                    }
                }
                flujoSalida.writeInt(numero);

            }

            String respuesta = flujoEntrada.readUTF();
            System.out.println("\nRespuesta del servidor: "+ respuesta);

            scanner.close();
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();
            System.out.println("La conexión se ha cerrado correctamente");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
