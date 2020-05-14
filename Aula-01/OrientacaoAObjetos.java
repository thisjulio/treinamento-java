public class FormaGeometrica{
    public static Integer t = 0;
    public Integer numeroDeLados = 4;
    public String nome = "quadrado";

}

public interface NomeInterface {
    public void teste();
}

public interface NomeInterface2 {

}

public class Quadrado extends FormaGeometrica implements NomeInterface, NomeInterface2 {
    private int teste;

    Quadrado(int testeArgumento) {
        // construtor
        this.teste = testeArgumento;
    }

    public int getT() {
        return FormaGeometrica.t;
    }
}

public class OrientacaoAObjetos{

    public static void main(String[] args) {
        String texto1 = "texto literal";
        Integer int1 = 10;
        Float float1 = 10.0;
        Double double1 = 11.1;
        Boolean bool = false;

        Quadrado forma1 = new Quadrado(10); // this.teste => 10
        Quadrado forma2 = new Quadrado(15); // this.teste => 15
    
        forma1.nome; // quadrado
        forma1.nome = "teste"; //teste
        forma2.nome; // quadrado

        forma1.getT(); // 0
        forma2.getT(); // 0
        FormaGeometrica.t = 15;
        forma1.getT(); // 15
        forma2.getT(); // 15

        NomeInterface obj1 = forma1;
        obj1.teste();
    }
}