package br.com.radixeng.treinamentojava.app;

import java.util.*;

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

    public static Conta instanciaConta(TipoDeConta tipoConta) throws TipoDeContaInvalida {

        switch (tipoConta) {
            case CONTA_CORRENTE:
                return new ContaCorrente();
            case CONTA_POUPANCA:
                return new ContaPoupanca();
            case CONTA_INVESTIMENTO:
                return new ContaInvestimento();
        }

        throw new TipoDeContaInvalida();
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



public class Banco implements IBanco {

    // Adicionar multiplos tipos de conta para um mesmo usuário
    // Usuário 1 -> (Conta Corrente, Conta Poupanca, Conta Investimentos, ...)
    //      Transferir Valores entre as contas de um usuário;
    //      Só é possível sacar de uma conta de investimento 30 dias após o depósito do investimento;
    private Map<String, Map<TipoDeConta,Conta>> mapaUsuarioContas;

    private static Banco instance = new Banco();

    // Singleton
    public static Banco obterBanco() {
        if (Banco.instance != null) {
            return Banco.instance;
        } else {
            Banco.instance = new Banco();
            return Banco.instance;
        }
    }

    private Banco(){
        this.mapaUsuarioContas = new HashMap();
    }

    public Boolean criarConta(IUsuario usuario, TipoDeConta tipo){
        try {

            Conta novaConta = Conta.instanciaConta(tipo);

            if (this.mapaUsuarioContas.containsKey(usuario.obterIdentificador())) {
                this.mapaUsuarioContas.get(usuario.obterIdentificador()).put(tipo, novaConta);
            } else {
                Map<TipoDeConta, Conta> contas = new HashMap<>();
                contas.put(tipo, novaConta);
                this.mapaUsuarioContas.put(usuario.obterIdentificador(), contas);
            }

            return true;
        } catch (TipoDeContaInvalida e) {
            System.out.println("Tipo de conta inválido para este usuário.");
            return false;
        }
    }

    public String verTodosOsSaldos() {
        String retorno = "";
        for(String identificadorUsuario: this.mapaUsuarioContas.keySet()) {
            retorno += identificadorUsuario + "\n"; // Trazer o nome da conta
            Map<TipoDeConta, Conta> tiposDeContaUsuario = this.mapaUsuarioContas.get(identificadorUsuario);
            for (TipoDeConta tipoConta : tiposDeContaUsuario.keySet()) {
                retorno += tipoConta + "\n";
                retorno += "Valor em conta: " + tiposDeContaUsuario.get(tipoConta).obterSaldo() + "\n";
            }
            retorno += "-------------------------\n";
        }

        return retorno;
    }


    public void depositar(Double valor, IUsuario usuario, TipoDeConta tipo) {
        Conta conta = this.obterContaApartirUsuario(tipo, usuario);

        conta.depositar(valor);
    }

    public Double sacar(Double valor, IUsuario usuario, TipoDeConta tipo) {
        Conta conta = this.obterContaApartirUsuario(tipo, usuario);

        try {
            conta.sacar(valor);
        } catch (java.lang.NullPointerException e) {
            System.out.println("Ocorreu um erro inesperado: "+ usuario.obterIdentificador());
            // throw e;
        }

        return valor;
    }

    public void transferir(Double valor, IUsuario origem, TipoDeConta tipoOrigem, IUsuario destino, TipoDeConta tipoDestino) {
        Conta contaOrigem = this.obterContaApartirUsuario(tipoOrigem, origem);//this.mapaUsuarioConta.get(origem);
        Conta contaDestino = this.obterContaApartirUsuario(tipoDestino, destino);//this.mapaUsuarioConta.get(destino);
        contaOrigem.transferir(valor, contaDestino);
    }

    public Map<TipoDeConta, Conta> obterContasUsuario(IUsuario usuario) {
        return this.mapaUsuarioContas.get(usuario.obterIdentificador());
    }

    private Conta obterContaApartirUsuario(TipoDeConta tipo, IUsuario usuario) {
        Map<TipoDeConta, Conta> tiposDeContaUsuario = this.mapaUsuarioContas.get(usuario.obterIdentificador());
        Conta conta = tiposDeContaUsuario.get(tipo);

        return conta;
    }
}
