package TCP.tcp3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class servidorFilas {

    public static void main(String[] args) {
        try{
            int Puerto = 6000;
            ServerSocket Servidor = new ServerSocket(Puerto);
            System.out.println("Escuchando en "+Servidor.getLocalPort());

            //aceptar cliente 
            Socket cli = Servidor.accept();
            System.out.println("Cliente conectado desde... "+cli.getInetAddress());

            //flujos cliente 
            DataInputStream in1 = new DataInputStream(cli.getInputStream());
            DataOutputStream out1 = new DataOutputStream(cli.getOutputStream());

            //crea el set con los numeros Y el reintegro
            Set<Integer> numeros = new HashSet<>();
            Random rn = new Random();

            while(numeros.size() < 6){
                numeros.add(rn.nextInt(49)+1);
            }

            System.out.println("Números de hoy:");
            for(int n : numeros){
                System.out.println(n);
            }

            int reinServ = rn.nextInt(10)+1;
            System.out.println("Reintegro de hoy: "+reinServ);

            //recibe los numeros del cliente + comprobacion
            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < 6; i++) {
                set.add(in1.readInt());
            }

            System.out.println("\nLo que recibo es:");
            for(Integer n : set){
                System.out.println(n);
            }

            int reintegro = in1.readInt();
            System.out.println("Reintegro: "+reintegro);

            int aciertos = 0;
            for(Integer n : numeros){
                if(set.contains(n)){
                    aciertos++;
                }
            }

            //logica de aciertos
            if(aciertos == 6){
                out1.writeUTF("PERO MADRE MIA !! HAS ACERTADO TODO !! QUE CRACK");
            }
            else if(aciertos == 5 && reintegro == reinServ){
                out1.writeUTF("Has acertado 5 y el reintegro !! Tienes otra oportunidad.");
            }
            else if(aciertos == 5){
                out1.writeUTF("Has acertado 5 !! que guay..");
            }
            else if(aciertos == 4){
                out1.writeUTF("Chachi pistachi, has acertado CUATRO");
            }
            else if(aciertos == 3){
                out1.writeUTF("POR LOS PELOS !! Has acertado 3");
            }
            else{
                out1.writeUTF("No tienes premio, PRINGAO!");
            }

            in1.close();
            out1.close();
            cli.close();
            Servidor.close();
            System.out.println("Venga chao, PESCAO");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
    }
}   