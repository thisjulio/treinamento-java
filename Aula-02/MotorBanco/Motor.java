// Objetivo -> Criar um motor de sistema bancário
//              -> Criar Contas de Usuários
//              -> Depositar Valores (Double) [Tomar cuidado com números de ponto flutuante para operação financeira]
//              -> Sacar Valores (Double) [Tomar cuidado com números de ponto flutuante para operação financeira]
//              -> Transferir Valores entre Contas [Tomar cuidado com números de ponto flutuante para operação financeira]

// 1.0 -> 0.999999999999999999991 -> 1.00000000000000000001

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Usuario{ 
    private String nome;

    Usuario(String nome) {
        this.nome = nome;
    }

    public String obterNome() {
        return this.nome;
    }
}

class Conta{
    private Double valor;

    public void depositar(Double valor) {
        this.valor += valor;
    }

    public Double sacar(Double valor) {
        this.valor -= valor;
        return valor;
    }
    
    public void transferir(Double valor, Conta destino) {
        this.sacar(valor);
        destino.depositar(valor);
     }

    public void verSaldo() {
        System.out.println("Valor em conta: " + this.valor);
    }

    Conta() {
        this.valor = 0.0;
    }
}

class Banco {

    // Adicionar multiplos tipos de conta para um mesmo usuário
    // Usuário 1 -> (Conta Corrente, Conta Poupanca, Conta Investimentos, ...)
    //      Transferir Valores entre as contas de um usuário;
    //      Só é possível sacar de uma conta de investimento 30 dias após o depósito do investimento;
    private Map<Usuario,Conta> mapaUsuarioConta;

    Banco(){
        this.mapaUsuarioConta = new HashMap();
    }

    public void criarConta(Usuario usuario){
        Conta novaConta = new Conta();

        this.mapaUsuarioConta.put(usuario, novaConta);
    }

    public void verTodosOsSaldos() {
        for(Usuario u: this.mapaUsuarioConta.keySet()) {
            System.out.println(u.obterNome() + ":");
            this.mapaUsuarioConta.get(u).verSaldo();
            System.out.println("-------------------------");
        }
    }

    public void depositar(Double valor, Usuario usuario) {
        Conta conta = this.mapaUsuarioConta.get(usuario);
        conta.depositar(valor);
    }

    public Double sacar(Double valor, Usuario usuario) {
        Conta conta = this.mapaUsuarioConta.get(usuario);

        try {
            conta.sacar(valor);
        } catch (java.lang.NullPointerException e) {
            System.out.println("Ocorreu um erro inesperado: "+ usuario.obterNome());
            // throw e;
        } 
        
        return valor;
    }
    
    public void transferir(Double valor, Usuario origem, Usuario destino) {
        Conta contaOrigem = this.mapaUsuarioConta.get(origem);
        Conta contaDestino = this.mapaUsuarioConta.get(destino);
        contaOrigem.transferir(valor, contaDestino);
    }
}

public class Motor{
    
    public static void main(String[] args) {
        // João
        Usuario joao = new Usuario("João");
        
        // Maria
        Usuario maria = new Usuario("Maria");

        // Fernanda
        Usuario fernanda = new Usuario("Fernanda");

        Banco banco = new Banco();
        

        // Operações
        banco.criarConta(joao);
        banco.criarConta(maria);

        banco.depositar(50.0, joao);
        
        banco.sacar(10.0, maria);
        
        banco.sacar(10.0, fernanda);

        banco.transferir(10.0, joao, maria);

        banco.verTodosOsSaldos();
    }
}