package Seguntri.MCS2;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class ClienteMC2 {

    @SuppressWarnings("deprecation")
    public static void main(String args[]) throws Exception {

        // Se crea el socket multicast
        int Puerto = 12345; // Puerto multicast
        MulticastSocket ms = new MulticastSocket(Puerto);
        InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Grupo
        // Nos unimos al grupo
        ms.joinGroup(grupo);

        String msg = "";
        byte[] buf = new byte[1000];
        
        while (!msg.trim().equals("Quit")) {
            // Recibe el paquete del servidor multicast
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            ms.receive(paquete);
            msg = new String(paquete.getData());
            System.out.println("Recibo: " + msg.trim());
        }

        ms.leaveGroup(grupo); // abandonamos grupo
        ms.close(); // cierra socket
        System.out.println("Socket cerrado...");
    }

    @SuppressWarnings("unused")
    private static NetworkInterface obtenerInterfazActiva() throws SocketException {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();

            if (ni.isUp() && !ni.isVirtual() && ni.supportsMulticast()) {
                return ni;
            }
        }
        return null;    
    }
}
