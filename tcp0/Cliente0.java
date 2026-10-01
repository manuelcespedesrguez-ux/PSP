package tcp0;

import java.net.InetAddress;
import java.net.Socket;

public class Cliente0 {

    public static void main(String[] args) {

        try {

            String Host = "localhost";
            int Puerto = 6000; // puerto remoto

            // ABRIR SOCKET
            Socket Cliente = new Socket(Host, Puerto); // conecta

            InetAddress i = Cliente.getInetAddress();
            System.out.println("Puerto local: " + Cliente.getLocalPort());
            System.out.println("Puerto Remoto: " + Cliente.getPort());
            System.out.println("Host Remoto: " + i.getHostName().toString());
            System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
            Cliente.close(); // Cierra el socket
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
