package br.com.radixeng.motorBanco.Motor;

import java.util.ArrayList;
import java.util.List;

abstract public class Conta {
    protected List<Operacao> operacoes = new ArrayList<>();

    public double getSaldo() {
        double saldo = 0.0;
        
        // for(int i = 0; i < operacoes.size() ; i++) {
        //     saldo += operacoes.get(i).getValor();
        // }

        for (Operacao transacao : operacoes) {
            saldo += transacao.getValor();
        }

        return saldo;
    }

    public List<Operacao> getTransacoes() {
        return operacoes;
    }

    private void operacao(double valorOperacao, Cliente usuarioOrigem, Cliente usuarioDestino) {
        Operacao novaOperacao = new Operacao(valorOperacao, DataBanco.agora(), usuarioOrigem, usuarioDestino);
        this.operacoes.add(novaOperacao);
    }

    public void sacar(double valor, Cliente usuarioOrigem, Cliente usuarioDestino) {
        this.operacao(-valor, usuarioOrigem, usuarioDestino);
    }

    public void depositar(double valor, Cliente usuarioOrigem, Cliente usuarioDestino) {
        this.operacao(valor, usuarioOrigem, usuarioDestino);
    }

    public static Conta criarConta(String tipoConta) throws Exception {
        if (tipoConta.equals(ContaCorrente.class.getName())) {
            return new ContaCorrente();
        } else if (tipoConta.equals(ContaPoupanca.class.getName())) {
            return new ContaPoupanca();
        } else if (tipoConta.equals(ContaInvestimento.class.getName())) {
            return new ContaInvestimento();
        } else {
            throw new Exception("Tipo de conta não permitido pelo sistema.");
        }
    }

    public static List<String> tiposDeConta() {
        List<String> tiposDeConta = new ArrayList<>();
        tiposDeConta.add(ContaCorrente.class.getName());
        tiposDeConta.add(ContaPoupanca.class.getName());
        tiposDeConta.add(ContaInvestimento.class.getName());

        return tiposDeConta;
    }
}