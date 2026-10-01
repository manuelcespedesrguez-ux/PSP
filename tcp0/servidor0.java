package tcp0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor0 {

    public static void main(String[] args) {

        try {

            int Puerto = 6000; // Puerto
            ServerSocket ServerS = new ServerSocket(Puerto);
            System.out.println("Escuchando en " + ServerS.getLocalPort());

            Socket cliente1 = ServerS.accept(); // esperando a un cliente

            // realizar acciones con cliente1
            Socket cliente2 = ServerS.accept(); // esperando a otro cliente
            // realizar acciones con cliente2

            ServerS.close(); // cierro socket servidor
            cliente1.close();
            cliente2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
