public class EstruturaDeRepeticao{

    public static void main(String[] args) {
        // while (/*condicao*/) {
        //     // código que sempre será executado enquanto a condicao for verdadeira
        //     break;  // Encerra a repetição
        //     continue; // Passa para o próximo
        // }
        
        // for(/* inicialização do laço*/ ; /*condicao*/ ; /* finalização do laço */) {
        //     // código que sempre será executado enquanto a condicao for verdadeira
        //     break; // Encerra a repetição
        //     continue; // Passa para o próximo
        // }

        int ate=10;
        int contador=0;
        while( contador <= ate ) {
            System.out.println("O número atual com while é: " + contador);
            contador += 2;
        }

        for(int contador2=0; contador2 <= ate; contador2+=2){
            System.out.println("O número atual com for é: " + contador2);
        }

    }
}