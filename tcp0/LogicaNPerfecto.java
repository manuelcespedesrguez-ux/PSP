package tcp0;

public class LogicaNPerfecto {
    
    public static boolean esNumeroPerfecto(int numero) {
        if (numero <= 1) {
            return false;
        }

        int sumaDivisores = 0;
        for (int i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                sumaDivisores += i;
            }
        }

        return sumaDivisores == numero;
    }
}