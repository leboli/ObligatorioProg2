package other;

public class FuncionesNumericas {

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int encontrarPrimoCercanoMayor(int numero) {
        int mayorPrimo = numero + 1;

        while (true) {
            if (esPrimo(mayorPrimo)) {
                return mayorPrimo;
            }
            mayorPrimo++;
        }
    }

}
