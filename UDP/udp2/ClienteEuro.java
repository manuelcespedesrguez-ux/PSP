package UDP.udp2;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClienteEuro {
    
    public static void main(String[] args) {
        try {
            String host = "localhost";
            int port = 6000;

            DatagramSocket socketUDP = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName(host);
            Scanner scanner = new Scanner(System.in);

            boolean continuarJugando = true;

            // Bucle principal para permitir jugar varias veces sin reiniciar el cliente
            while (continuarJugando) {
                Set<Integer> setNums = new HashSet<>();

                // Pedimos los 5 números del 1 al 50
                while (setNums.size() < 5) {
                    System.out.println("\nIntroduce el número principal " + (setNums.size() + 1) + " (entre 1 y 50):");
                    if (scanner.hasNextInt()) {
                        int numero = scanner.nextInt();
                        if (numero < 1 || numero > 50) {
                            System.out.println("ERROR: El número debe estar entre 1 y 50");
                        } else if (!setNums.add(numero)) { 
                            System.out.println("ERROR: El número ya ha sido introducido.");
                        }
                    } else {
                        System.out.println("ERROR: Debes introducir un número entero válido");
                        scanner.next(); 
                    }
                }

                // Pedimos dos números más que son los soles del 1 al 12
                Set<Integer> setSoles = new HashSet<>();
                while (setSoles.size() < 2) {
                    System.out.println("Introduce el sol / número adicional " + (setSoles.size() + 1) + " (entre 1 y 12):");
                    if (scanner.hasNextInt()) {
                        int sol = scanner.nextInt();
                        if (sol < 1 || sol > 12) {
                            System.out.println("ERROR: El sol debe estar entre 1 y 12");
                        } else if (!setSoles.add(sol)) {
                            System.out.println("ERROR: Ese sol ya ha sido introducido.");
                        }
                    } else {
                        System.out.println("ERROR: Debes introducir un número entero válido");
                        scanner.next();
                    }
                }

                // Pasamos los datos mediante un array de bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(baos);

                // Escribe los 5 números
                for (Integer num : setNums) {
                    out.writeInt(num);
                }
                // Escribe los 2 soles
                for (Integer sol : setSoles) {
                    out.writeInt(sol);
                }
                out.flush();

                byte[] datosAEnviar = baos.toByteArray();

                // Se envia la info al servidor en bytes
                DatagramPacket paqueteEnvio = new DatagramPacket(datosAEnviar, datosAEnviar.length, direccionServidor, port);
                socketUDP.send(paqueteEnvio);

                // Recibimos respuesta del servidor
                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                socketUDP.receive(paqueteRecepcion);

                String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                System.out.println("\nRespuesta del servidor: " + respuesta);

                // Preguntamos si quiere echar otra jugada
                System.out.println("\n¿Quieres seguir perdiendo? Si no quieres seguir humillandote escribe no: ");
                String respuestaUsuario = scanner.next();
                if (respuestaUsuario.equalsIgnoreCase("no")) {
                    continuarJugando = false;
                }
            }

            scanner.close();
            socketUDP.close();
            System.out.println("Conexión cerrada correctamente.");
            System.out.println("Chao almendrugo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}