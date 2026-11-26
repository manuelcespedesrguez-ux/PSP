/* Ejercicio 1
URL url = new URL("http://www.example.com");
URLConnection conexion = url.openConnection();
BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
String linea;
while ((linea = lector.readLine()) != null) {
    System.out.println(linea);
}
lector.close();

Este codigo establece una conexion con esa URL y la abre, crea un lector con el bufferedReader que lee la conexion se haya hecho
y se crea un string que recoge el buffered reader, el cual con el while se detecta si esta vacio y se devuelve la linea de la conexion

*/

/* Ejercicio 2
ServerSocket servidor = new ServerSocket(5000);
System.out.println("Servidor iniciado a la escucha en el puerto 5000...");
Socket clienteSocket = servidor.accept();
BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
String mensaje = entrada.readLine();
System.out.println("Mensaje recibido del cliente: " + mensaje);
clienteSocket.close();
servidor.close();

El serverSocket instancia por defecto el puerto en el 5000, se le muestra al usuario que el servidor esta encendido escuchando en el puerto 5000;
se crea un socket que recibe acepta la conexcion con el cliente y recibe su mensaje a traves de un bufferedreader el cual lee si hay un mensaje y lo recibe 
ahora muestra el mensaje del cliente y se cierra la conexion con el cliente y la del servidor
*/

/* Ejercicio 3
Socket socket = new Socket("localhost", 5000);
PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
salida.println("Hola servidor, peticion correcta.");
salida.close();
socket.close();

Se crea un socket en el o¡puerto 5000, se crea un printwriter que manda el mensaje al servidor, el true lo que hace es que vacia el bufer si ha comprobado 
que esta lleno, y se le manda el mensaje al servidor y posteriormente se cierra la conexiion
*/


/* EJercicio 4
byte[] buffer = new byte[1024];
DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
DatagramSocket socketUDP = new DatagramSocket(6000);
socketUDP.receive(paqueteEntrada);
String mensajeRecibido = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
System.out.println("Paquete UDP recibido de: " + paqueteEntrada.getAddress() + " -> " + mensajeRecibido);
socketUDP.close();

Se crea un array de bytes, con el datagrampacket se crea un paquete en el que se guarda en bytes ese paquete que se ha recibido, y se comprueba su longitud 
para que no se pase de 1024, se crea un socket en el puerto 6000 y se recibe el paquete, se crea un string en el que se recibe los datos del paquete que ha
entrado y su longitud; se muestra mensaje diciendo que se ha recibido el paquete desde los datos del cliente y el mensaje que ha mandado 
*/

// Segunda tanda de ejercicios 

/* Ejercicio 5
ServerSocket servidor = new ServerSocket(5000);
System.out.println("Servidor multihilo en marcha...");
while (true) {
    Socket clienteSocket = servidor.accept(); // Bloqueante esperando cliente
    // Se lanza un hilo independiente para atender a este cliente
    new HiloCliente(clienteSocket).start(); 
}

Se crea un socket del servidor en el puerto 5000, se muestra por pantalla al usuario que se inicia el servidor multhilo,
se crea un bucle while que mientras sea true el servidor va a lanzar un socket para el cliente para que acepte al cliente y se crea otro hilo para atender 
al siguiente cliente mientras el servidor espera por el cliente 1
*/

/* Ejercicio 6
DatagramSocket socketUDP = new DatagramSocket();
String mensaje = "¡Hola desde el cliente UDP!";
byte[] buffer = mensaje.getBytes();
InetAddress direccionDestino = InetAddress.getByName("localhost");
DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccionDestino, 6000);
socketUDP.send(paquete);
socketUDP.close();

Este UDP se diferencia del TCP porque usa arrays de bytes para mandar los mensajes de un lado a otro y no es de forma directa como el TCP que seria similar
a una llamada de telefono. Se crea un nuevo socket, y se añade un mensaje; este mensaje se transforma a bytes mediante el metodo getbytes() y se mete dentro 
de un array, se pone la direccion de destino la que se llame localhost y se crea el paquete formado por el array, el tamaño de bytes y la direccion y se marca 
al puerto 6000. Se manda el paquete y se cierra el socket
*/

/* Ejercicio 7
DataInputStream entradaDatos = new DataInputStream(clienteSocket.getInputStream());
int idCliente = entradaDatos.readInt();
double saldo = entradaDatos.readDouble();
System.out.println("Recibido ID: " + idCliente + " con saldo: " + saldo);

Recibe datos mediante el datainputstream y estos datos se leen mediante el metodo readint y el readdouble y se muestran por consola el id recibido con el 
saldo. El buffered se usa para la leer strings ya que no puede leer int o doubles pero el data si
*/

/* Ejercicio 8
Socket socket = new Socket("127.0.0.1", 5000);
BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));

System.out.print("Introduce texto para el servidor: ");
String tecladoMsg = teclado.readLine();
salida.println(tecladoMsg); // Envía al servidor
String respuesta = entradaServidor.readLine(); // Lee la respuesta
System.out.println("Respuesta del servidor: " + respuesta);

socket.close();

Se usan varios bufferedReader ya que el primero llamado teclado lo que hace es reciibir el mensaje que quiere mandarle el cliente al servidor y el otro 
entradaServidor recoge la respuesta mandada del servidor; ambas son mostradas por pantalla pero antes se pasas a un String para poder leerlas y luego 
se muestran por pantalla y se cierrra el socket
*/

/* Ejercicio 9
Socket socket = new Socket("localhost", 5000);
DataOutputStream salidaDatos = new DataOutputStream(socket.getOutputStream());
salidaDatos.writeInt(108);       // Envía un entero (4 bytes)
salidaDatos.writeDouble(1550.75); // Envía un double (8 bytes)
salidaDatos.flush();              // Fuerza el vaciado del buffer binario
socket.close();

El flush lo que hace es como tirar de la cisterna, provoca que el dataoutput qwue ha recibido el binario se vacie al enviar los datos y posteirormente se 
cierra el socket
*/

/* Ejercicio 10
InetAddress direccion = InetAddress.getByName("www.google.es");
System.out.println("Nombre del host: " + direccion.getHostName());
System.out.println("Dirección IP: " + direccion.getHostAddress());

InetAddress miaLocal = InetAddress.getLocalHost();
System.out.println("Mi propia IP local: " + miaLocal.getHostAddress());

El inetaddress se usa para usar un enlace que ya existe como por ejemplo el de google y mostrar su direccion IP y su nombre. Por otro lado el getlocalhost
se usa para recibir la IP del servidor local abierto 
*/