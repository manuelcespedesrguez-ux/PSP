package MCS2;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServidorMC2 {
    
    public static String obtenerHoraActual(LocalDate fecha) {
        // Obtenemos la hora actual
        LocalTime horaActual = LocalTime.now();
        
        // Formateamos la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(formatter);
        
        // Devolvemos una cadena con la fecha y la hora
        return "Fecha: " + fecha + ", Hora actual: " + horaFormateada;
    }

    public static void main(String args[]) throws Exception {
        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Se crea el socket multicast.
        MulticastSocket ms = new MulticastSocket();
        int Puerto = 12345; // Puerto multicast
        InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Grupo
        String cadena = "";

        //
        while (!cadena.trim().equals("quit")) {
            System.out.println("Escribe el mensaje o escribe 'quit' si quieres salir");
            cadena = in.readLine();

            String mensaje = cadena + " " + obtenerHoraActual(LocalDate.now());
            byte[] datos = mensaje.getBytes();

            // ENVIANDO AL GRUPO
            DatagramPacket paquete = new DatagramPacket(datos, datos.length, grupo, Puerto);
            ms.send(paquete);
        }
        ms.close(); // cierro socket
        System.out.println("Socket cerrado...");
    }
}