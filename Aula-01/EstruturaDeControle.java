public class EstruturaDeControle {

    public static void main(String[] args){
        // if (/*condição lógica*/) {
        //     // Instrução quando a condição lógica é verdadeira
        // } else {
        //     // Instrução quando a condição lógica é falsa
        // }

        // if (/*condição lógica*/) {
        //     // Instrução quando a condição lógica é verdadeira
        // } else if (/*segunda condição lógica*/) {
        //     // Instrução quando a segunda condição lógica é verdadeira
        // } else {
        //     // Instrução quando a condição lógica é falsa
        // }

        // switch(/*valor*/) {
        //     case /*exemplo de valor 1*/:
        //         break;
        //     case /*exemplo de valor 1*/:
        //         break;
        //     default:
        //         break;
        // }

        int numero1 = 0;

        if (numero1 % 2 == 0) {
            System.out.println("O número informado é par!");
        } else {
            System.out.println("O número informado não é par!");
        }

        int numero2 = 0;

        switch(numero2) {
            case 0:
                System.out.println("O número é zero!");
                break;
            case 1:
                System.out.println("O número é um!");
                break;
            case 2:
                System.out.println("O número é dois!");
                break;
            default:
                System.out.println("O número não foi mapeado");
                break;
        }

    }
}