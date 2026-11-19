/* Ejercicio 1
ServerSocket servidor = new ServerSocket(6000);
Socket cliente = servidor.accept();
DataInputStream dis = new DataInputStream(cliente.getInputStream());
String mensaje = dis.readUTF(); 

ServerSocket inicia el servidor en el puerto 6000, bloquea al cliente para que pueda entrar otro cliente, se lee la informaxion llegada medianre el datainput
y se transforma a UTF mediante un string y el metodo readUTF
*/

/* Ejercicio 2
byte[] buffer = new byte[1024];
DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
DatagramSocket socketUDP = new DatagramSocket(9876);
socketUDP.receive(paquete);

Porque primero hay que pasar el paquete que se recibe a bytes porque en UDP se trabaja siempre con los paquetes en bytes y por eso se castea  abytes 
y luego se recibe el paquete en bytes
*/

/* Ejercicio 3
¿Qué es lo que está fallando en la gestión del flujo de salida y para qué sirve exactamente el método flush() (o el parámetro true en el PrintWriter)?
Puede ser que el flush actue antes de que se mande el mensaje y por eso no se mande nada, ya que el flush vacia el buffer; el true del PrintWriter,
hace que se active el autoflush osea que se vacie el buffer
*/


/* Ejercicio 4
URL url = new URL("http://www.example.com");
URLConnection conexion = url.openConnection();
BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
String linea;
while ((linea = lector.readLine()) != null) {
    System.out.println(linea);
}
lector.close();

Se crea un objeto URL con la url de ejemplo, se abre una conexion con la URL, se le lo que hay dentro de la conexion y reciben los datos
se crea un Objeto String llamado linea y con el buvle while se garantiza que siempre que liena y lector.readline sea distinto de null se 
devuelva el string linea y se cierra el reader
*/


/* Ejercicio 5
ServerSocket servidor = new ServerSocket(5000);
while (true) {
    Socket clienteSocket = servidor.accept();
    new HiloCliente(clienteSocket).start();
}

Asi se garantiza que siempre entre un cliente a la conexion porque con el accept se crea una llamada bloqueante a la espera de que entre un cliente y es 
while true debido a que asi se garantiza siempre la conexion por eso se pone true para que siempre haya una llamada de cliente 
*/

/* Ejercicio 6
MulticastSocket ms = new MulticastSocket(12345);
InetAddress grupo = InetAddress.getByName("225.0.0.1");
ms.joinGroup(grupo);
byte[] buf = new byte[1000];
DatagramPacket paquete = new DatagramPacket(buf, buf.length);
ms.receive(paquete);

Esto de multicast no entra 
*/

/* Ejercicio 7
import java.io.*;
import java.net.*;

public class ClienteExamen {
    public static void main(String[] args) {
        try {
            // 1. Creamos el socket conectándonos a localhost en el puerto 6000
            Socket socket = new Socket("localhost", 6000);

            // 2. Creamos el flujo de salida con autoflush activado
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // 3. Enviamos el mensaje al servidor
            salida.println("Hola, esta es la petición del examen.");

            // 4. Cerramos los recursos en el orden adecuado
            salida.close();
            socket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/