import java.util.List; // Interface
import java.util.ArrayList; // Implementação da interface List

// interface Forma3D {}

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

interface Forma2D {
    FormaGeometrica extrusaoLinear(int altura);
}

// Criar interface Forma3D e tornar possível para as formas Geométricas de Cubo e Cilindro, realizar a projeção no eixo XY

class Quadrado extends FormaGeometrica implements Forma2D {
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

    public Cubo extrusaoLinear(int altura) {
        return new Cubo(this.largura, this.comprimento, altura, this.pontoCentral.posicaoX, this.pontoCentral.posicaoY, this.pontoCentral.posicaoZ);
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

class Cubo extends FormaGeometrica {
    protected int largura;
    protected int comprimento;
    protected int altura;

    Cubo(int largura, int comprimento, int altura, int posicaoX, int posicaoY, int posicaoZ) {
        super(posicaoX, posicaoY, posicaoZ);
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    @Override
    public String toString() {
        return "largura: " + this.largura +  " comprimento: " + this.comprimento + " altura: " + this.altura + " " + super.toString();
    }
}

class Cilindro extends FormaGeometrica {
    protected int raio;
    protected int altura;

    Cilindro(int raio, int altura, int posicaoX, int posicaoY, int posicaoZ) {
        super(posicaoX, posicaoY, posicaoZ);
        this.altura = altura;
        this.raio = raio;
    }

    @Override
    public String toString() {
        return "raio: " + this.raio + "altura: " + this.altura + " " + super.toString();
    }
}



public class OrientacaoAObjeto {

    public static void main(String[] args) {
        Circulo c1 = new Circulo(10, 0, 0, 0);
        Circulo c2 = c1;
        
        Circulo c3 = new Circulo(5, 2, 3, 4);

        Quadrado q1 = new Quadrado(10, 10, 15,15,15);

        Cubo cb1 = q1.extrusaoLinear(7);

        System.out.println("Exibindo forma geométrica c1: " + c1.toString()); // .toString()
        System.out.println("Exibindo forma geométrica c2: " + c2);

        System.out.println("Exibindo forma geométrica q1: " + q1);
        System.out.println("Exibindo forma geométrica cb1: " + cb1);

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