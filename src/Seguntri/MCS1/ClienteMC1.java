package Seguntri.MCS1;

import java.net.*;

public class ClienteMC1 {

    public static void main(String args[]) throws Exception {
        // Se crea el socket multicast
        int Puerto = 12345; // Puerto multicast
        MulticastSocket ms = new MulticastSocket(Puerto);
        InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Grupo
        // Nos unimos al grupo
        ms.joinGroup(grupo);
        String msg = "";
        byte[] buf = new byte[1000];
        //
        while (!msg.trim().equals("Quit")) {
            // Recibe el paquete del servidor multicast
            System.out.println("Escribe el mensaje o escribe 'quit' si quieres salir");
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            ms.receive(paquete);
            msg = new String(paquete.getData());
            System.out.println("Recibo: " + msg.trim());
        }
        ms.leaveGroup(grupo); // abandonamos grupo
        ms.close(); // cierra socket
        System.out.println("Socket cerrado...");
    }
}