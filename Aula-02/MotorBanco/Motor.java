// Objetivo -> Criar um motor de sistema bancário
//              -> Criar Contas de Usuários
//              -> Depositar Valores (Double) [Tomar cuidado com números de ponto flutuante para operação financeira]
//              -> Sacar Valores (Double) [Tomar cuidado com números de ponto flutuante para operação financeira]
//              -> Transferir Valores entre Contas [Tomar cuidado com números de ponto flutuante para operação financeira]

// 1.0 -> 0.999999999999999999991 -> 1.00000000000000000001

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;

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

class TipoDeContaInvalida extends Exception {

}

class Transacao {
    private Double valor;
    private Date data;
    
    public Double obterValor() {
        return this.valor;
    }

    public Date obterData() {
        return this.data;
    }

    Transacao(Double valor, Date data) {
        this.valor = valor;
        this.data = data;
    }

    static Double obterSaldo(List<Transacao> transacoes) {
        Double saldo = 0.0;
        
        for (Transacao transacao : transacoes) {
            saldo += transacao.obterValor();
        }

        return saldo;
    }
}

class Conta{
    protected List<Transacao> transacoes;


    protected Double adicionarTransacao(Double valor) {
        Transacao transacao = new Transacao(valor, new Date());
        transacoes.add(transacao);
        return valor;
    }

    public void depositar(Double valor) {
        this.adicionarTransacao(valor);
    }

    public Double sacar(Double valor) {
        return this.adicionarTransacao(-valor);
    }
    
    public void transferir(Double valor, Conta destino) {
        this.sacar(valor);
        destino.depositar(valor);
    }

    public Double obterSaldo() {
        return Transacao.obterSaldo(this.transacoes);
    }

    public void verSaldo() {
        System.out.println("Valor em conta: " + this.obterSaldo());
    }

    Conta() {
        this.transacoes = new ArrayList<>();
    }

    public static Conta instanciaConta(String tipoConta) throws TipoDeContaInvalida {
        if (tipoConta.equals(ContaCorrente.class.getName())) {
            return new ContaCorrente();
        } else if (tipoConta.equals(ContaPoupanca.class.getName())) {
            return new ContaPoupanca();
        } else if (tipoConta.equals(ContaInvestimento.class.getName())) {
            return new ContaInvestimento();
        } else {
            throw new TipoDeContaInvalida();
        }
    }
}

class ContaCorrente extends Conta { }
class ContaPoupanca extends Conta { }

class ContaInvestimento extends Conta { 

    public Double sacar(Double valor) {
        // Date dataAtual = new Date();
        List<Transacao> transacoesComMaisDeTrintaDias = new ArrayList<>();
        
        for (Transacao transacao : this.transacoes) {
            Calendar calendarTransacaoAposTrintaDias = Calendar.getInstance();
            calendarTransacaoAposTrintaDias.setTime(transacao.obterData());
            calendarTransacaoAposTrintaDias.add(Calendar.DATE, 30);
            
            if (transacao.obterValor() < 0 || calendarTransacaoAposTrintaDias.before(new Date())) {
                transacoesComMaisDeTrintaDias.add(transacao);
            }
        }

        Double saldo = Transacao.obterSaldo(transacoesComMaisDeTrintaDias);
        if (saldo > 0 && saldo > valor ) {
            this.sacar(valor);
            return valor;
        }

        return 0.0;
    }

}



class Banco {

    // Adicionar multiplos tipos de conta para um mesmo usuário
    // Usuário 1 -> (Conta Corrente, Conta Poupanca, Conta Investimentos, ...)
    //      Transferir Valores entre as contas de um usuário;
    //      Só é possível sacar de uma conta de investimento 30 dias após o depósito do investimento;
    private Map<Usuario,Map<String,Conta>> mapaUsuarioContas;

    Banco(){
        this.mapaUsuarioContas = new HashMap();
    }

    public void criarConta(Usuario usuario, String tipo){
        try {

            Conta novaConta = Conta.instanciaConta(tipo);
            
            if (this.mapaUsuarioContas.containsKey(usuario)) {
                this.mapaUsuarioContas.get(usuario).put(tipo, novaConta);
            } else {
                Map<String, Conta> contas = new HashMap<>();
                contas.put(tipo, novaConta);
                this.mapaUsuarioContas.put(usuario, contas);
            }
            
        } catch (TipoDeContaInvalida e) {
            System.out.println("Tipo de conta inválido para este usuário.");
        }
    }

    public void verTodosOsSaldos() {
        for(Usuario u: this.mapaUsuarioContas.keySet()) {
            System.out.println(u.obterNome()); // Trazer o nome da conta
            Map<String, Conta> tiposDeContaUsuario = this.mapaUsuarioContas.get(u);
            for (String tipoConta : tiposDeContaUsuario.keySet()) {
                System.out.println(tipoConta);
                tiposDeContaUsuario.get(tipoConta).verSaldo();
            }
            System.out.println("-------------------------");
        }
    }

    public void depositar(Double valor, Usuario usuario, String tipo) {
        Conta conta = this.obterContaApartirUsuario(tipo, usuario);
        
        conta.depositar(valor);
    }

    public Double sacar(Double valor, Usuario usuario, String tipo) {
        Conta conta = this.obterContaApartirUsuario(tipo, usuario);

        try {
            conta.sacar(valor);
        } catch (java.lang.NullPointerException e) {
            System.out.println("Ocorreu um erro inesperado: "+ usuario.obterNome());
            // throw e;
        } 
        
        return valor;
    }
    
    public void transferir(Double valor, Usuario origem, String tipoOrigem, Usuario destino, String tipoDestino) {
        Conta contaOrigem = this.obterContaApartirUsuario(tipoOrigem, origem);//this.mapaUsuarioConta.get(origem);
        Conta contaDestino = this.obterContaApartirUsuario(tipoDestino, destino);//this.mapaUsuarioConta.get(destino);
        contaOrigem.transferir(valor, contaDestino);
    }

    private Conta obterContaApartirUsuario(String tipo, Usuario usuario) {
        Map<String, Conta> tiposDeContaUsuario = this.mapaUsuarioContas.get(usuario);
        Conta conta = tiposDeContaUsuario.get(tipo);

        return conta;
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
        banco.criarConta(joao, ContaCorrente.class.getName());
        banco.criarConta(joao, ContaPoupanca.class.getName());
        banco.criarConta(maria, ContaCorrente.class.getName());
        banco.criarConta(maria, ContaInvestimento.class.getName());
        banco.criarConta(fernanda, "ContaEspecial");

        banco.depositar(50.0, joao, ContaCorrente.class.getName());
        banco.transferir(20.0, joao, ContaCorrente.class.getName(), joao, ContaPoupanca.class.getName());
        
        banco.sacar(10.0, maria, ContaCorrente.class.getName());
        
        // banco.sacar(10.0, fernanda, ContaCorrente.class.getName());

        banco.transferir(10.0, joao, ContaCorrente.class.getName(), maria, ContaCorrente.class.getName());

        banco.depositar(30.0, maria, ContaInvestimento.class.getName());
        banco.sacar(10.0, maria, ContaInvestimento.class.getName());

        banco.verTodosOsSaldos();
    }
}