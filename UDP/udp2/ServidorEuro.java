package UDP.udp2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class ServidorEuro {

    private static final Set<Integer> GANADORES_NUMS = Set.of(5, 12, 23, 34, 45);
    private static final Set<Integer> GANADORES_SOLES = Set.of(3, 9);

    public static void main(String[] args) {

        try {
            int puerto = 6000;
            DatagramSocket socketUDP = new DatagramSocket(puerto);
            System.out.println("Servidor Eurojackpot escuchando en el puerto " + puerto);

            while (true) {
                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                
                System.out.println("Esperando apuesta de Eurojackpot...");
                socketUDP.receive(paqueteRecepcion);
                System.out.println("Cliente conectado desde: " + paqueteRecepcion.getAddress());

                // Convierte los bytes recibidos a numeros enteros
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                DataInputStream in = new DataInputStream(bais);

                Set<Integer> setClienteNums = new HashSet<>();
                for (int i = 0; i < 5; i++) {
                    setClienteNums.add(in.readInt());
                }

                Set<Integer> setClienteSoles = new HashSet<>();
                for (int i = 0; i < 2; i++) {
                    setClienteSoles.add(in.readInt());
                }

                System.out.println("Números recibidos: " + setClienteNums);
                System.out.println("Soles recibidos: " + setClienteSoles);
                System.out.println("Comprobando jugada...");

                // Llamada al método para comprobar la jugada con la combinación fija
                String respuesta = comprobarJugada(setClienteNums, setClienteSoles);

                // Enviamos la respuesta al cliente de nuevo 
                byte[] datosRespuesta = respuesta.getBytes();
                InetAddress direccionCliente = paqueteRecepcion.getAddress();
                int puertoCliente = paqueteRecepcion.getPort();
                
                DatagramPacket paqueteEnvio = new DatagramPacket(datosRespuesta, datosRespuesta.length, direccionCliente, puertoCliente);
                socketUDP.send(paqueteEnvio);
                
                System.out.println("Respuesta enviada.");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método propio para la lógica de comprobación
    public static String comprobarJugada(Set<Integer> setClienteNums, Set<Integer> setClienteSoles) {
        // Verifica los aciertos
        int aciertosNums = 0;
        for (Integer n : GANADORES_NUMS) {
            if (setClienteNums.contains(n)) {
                aciertosNums++;
            }
        }

        int aciertosSoles = 0;
        for (Integer s : GANADORES_SOLES) {
            if (setClienteSoles.contains(s)) {
                aciertosSoles++;
            }
        }

        // Lógica de premios básica orientada al Eurojackpot
        String respuesta;
        if (aciertosNums == 5 && aciertosSoles == 2) {
            respuesta = "¡BOTE! ¡Has acertado los 5 números y los 2 soles, JETOVEN! ¡Puedes pagar la cuota de CEBEM!";
        } else if (aciertosNums == 5 && aciertosSoles == 1) {
            respuesta = "¡Increíble! 5 números y 1 sol.";
        } else if (aciertosNums == 5 && aciertosSoles == 0) {
            respuesta = "¡Has acertado los 5 números principales!";
        } else if (aciertosNums >= 3) {
            respuesta = "¡Buen trabajo! Has acertado " + aciertosNums + " números y " + aciertosSoles + " soles.";
        } else {
            respuesta = "Sin premio pringado, la casa siempre gana ¡a seguir intentándolo!";
        }
        
        return respuesta;
    }
}