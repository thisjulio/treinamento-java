import java.util.List; // Interface
import java.util.ArrayList; // Implementação da interface List

class Ponto {
    protected int posicaoX;
    protected int posicaoY;
    protected int posicaoZ;

    Ponto(int posicaoX, int posicaoY, int posicaoZ) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
    }
}

abstract class FormaGeometrica {
    protected Ponto pontoCentral;

    FormaGeometrica(int posicaoX, int posicaoY, int posicaoZ) {
        this.pontoCentral = new Ponto(posicaoX, posicaoY, posicaoZ);
    }

    public String toString() {
        return "ponto central: (" + this.pontoCentral.posicaoX + "," + this.pontoCentral.posicaoY + "," + this.pontoCentral.posicaoZ + ")";
    }
}

class Quadrado extends FormaGeometrica {
    protected int largura;
    protected int comprimento;

    Quadrado(int largura, int comprimento, int posicaoX, int posicaoY, int posicaoZ) {
        super(posicaoX, posicaoY, posicaoZ);
        this.largura = largura;
        this.comprimento = comprimento;
    }

    @Override
    public String toString() {
        return "largura: " + this.largura +  " comprimento: " + this.comprimento + " " + super.toString();
    }
}

class Circulo extends FormaGeometrica {
    protected int raio;

    Circulo(int raio, int posicaoX, int posicaoY, int posicaoZ) {
        super(posicaoX, posicaoY, posicaoZ);
        this.raio = raio;
    }

    @Override
    public String toString() {
        return "raio: " + this.raio + " " + super.toString();
    }
}

// class Poligono extends FormaGeometrica {
//     protected Ponto[] pontos;
// }



public class OrientacaoAObjeto {

    public static void main(String[] args) {
        Circulo c1 = new Circulo(10, 0, 0, 0);
        Circulo c2 = c1;
        
        Circulo c3 = new Circulo(5, 2, 3, 4);

        Quadrado q1 = new Quadrado(10, 10, 15,15,15);

        System.out.println("Exibindo forma geométrica c1: " + c1.toString()); // .toString()
        System.out.println("Exibindo forma geométrica c2: " + c2);

        System.out.println("Exibindo forma geométrica q1: " + q1);

        // List<Quadrado> formasQuadrado = new ArrayList();
        // formasQuadrado.add(q1);

        // List<Circulo> formasCirculo = new ArrayList();
        // formasCirculo.add(c1);  
        // formasCirculo.add(c2);  
        // formasCirculo.add(c3); 
        
        // for(Quadrado q: formasQuadrado) {
        //     System.out.println(q);
        // }

        // for(Circulo c: formasCirculo) {
        //     System.out.println(c);
        // }

        List<FormaGeometrica> formas = new ArrayList();
        formas.add(c1);
        formas.add(c2);
        formas.add(c3);
        formas.add(q1);

        for (FormaGeometrica f: formas){
            System.out.println(f);
        }

    }
}