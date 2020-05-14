import java.util.List; // Interface
import java.util.ArrayList; // Implementação da interface List

import java.util.Map; // Interface
import java.util.HashMap; // Implementação da interface List

public class Colecoes {

    public static void main(String[] args) {

        List<Integer> lista = new ArrayList();

        lista.add(10);
        lista.add(15);
        lista.add(20);

        // for(int i=0; i < lista.size(); i++) {
        //     System.out.println(lista.get(i));
        // }

        for(Integer inteiro: lista) {
            System.out.println(inteiro);
        }

        // (alguma coisa) => (alguma coisa)
        Map<String, Integer> dicionario = new HashMap();

        dicionario.put("teste",10);

        System.out.println("Valor teste no dicionário: " + dicionario.get("teste"));
    }
}