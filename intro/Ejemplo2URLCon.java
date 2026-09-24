package intro;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ejemplo2URLCon {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost/vernombre.php");
            URLConnection conexion = url.openConnection();
            conexion.setDoOutput(true);

            Scanner sc = new  Scanner(System.in); 
            System.out.println("Introduce tu nombre: "); // Pedimos el nombre
            String nombre = sc.nextLine(); // Lee el nombre
            System.out.println("Introduce los apellidos: "); // Pedimos los apellidos
            String apellidos = sc.nextLine(); // Lee los apellidos introducidos
            String cadena = "nombre=" + nombre + "&apellidos=" + apellidos; // Pone el nombre y los apellidos en una unida cadena 

            // ESCRIBIR EN LA URL
            PrintWriter output = new PrintWriter(conexion.getOutputStream());
            output.write(cadena);
            output.close(); // cerrar flujo

            // LEER DE LA URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close();// cerrar flujo

        } catch (MalformedURLException me) {
            System.err.println("MalformedURLException: " + me);
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe);
        }
    }
}
