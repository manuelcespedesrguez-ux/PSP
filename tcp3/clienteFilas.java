package tcp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class clienteFilas {
    
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

            //pido 6 numeros y los almaceno
            Set<Integer> setNums = new HashSet<>();

            // ASUMO: el macaco es inteligente y no repite los numeros
            while (setNums.size() < 6) {
                System.out.println("Introduce el número " + (setNums.size() + 1) + " comprendido entre 1 y 49:");

                if (scanner.hasNextInt()) {
                    int numero = scanner.nextInt();
                    if (numero < 1 || numero > 49) {
                        System.out.println("ERROR: El número debe estar comprendido entre 1 y 49");
                    } else if (!setNums.add(numero)) { 
                        // set.add() devuelve false si el número ya existía en el Set
                        System.out.println("ERROR: El número ya ha sido introducido. Elige uno distinto.");
                    }
                } else {
                    System.out.println("ERROR: Debes introducir un número entero válido");
                    scanner.next(); // Limpia el buffer para evitar bucle infinito si escriben letras
                }
            }

            // Enviamos cada número del Set uno por uno
            for (Integer num : setNums) {
                flujoSalida.writeInt(num);
            }
            flujoSalida.flush();

            //genero el reintegro y lo envio
            Random rn = new Random();
            int reintegro = (int) (rn.nextInt(10)+1);

            System.out.println("El reintegro de hoy es: "+reintegro);

            flujoSalida.writeInt(reintegro);

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