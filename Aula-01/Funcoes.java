public class Funcoes {

    public static void oiMundo() {
        System.out.println("Oi mundo!");
    }

    public static int soma(int a, int b) {
        return a + b; 
    }

    // Recebe um número de entrada
    // Retorna o produto de todos os numeros anteriores até chegar 
    // no próprio número.
    // fatorial(3) => 3 * 2 * 1
    // fatorial(3) => 3 * fatorial(3-1)
    // fatorial(3) => 3 * 2 * fatorial(3-2)
    // fatorial(n) => se n > 2 => n * fatorial(n-1) se não => 2
    public static int fatorial(int n) {
        if (n > 1) {
            return n * fatorial(n-1);
        } else {
            return 1;
        }
    }


    public static void main(String[] args) {
        oiMundo();

        System.out.println("Somar 1 com 3: " + soma(1,3));
        System.out.println("Fatorial 5: " + fatorial(5));
        System.out.println("Fatorial 2: " + fatorial(2));
    }
}