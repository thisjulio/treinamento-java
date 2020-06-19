package br.com.radixeng.motorBanco.Motor;

import java.util.ArrayList;
import java.util.List;

abstract public class Conta {
    protected List<Transacao> transacoes = new ArrayList<>();

    public double getSaldo() {
        double saldo = 0.0;
        
        // for(int i = 0; i < transacoes.size() ; i++) {
        //     saldo += transacoes.get(i).getValor();
        // }

        for (Transacao transacao : transacoes) {
            saldo += transacao.getValor();
        }

        return saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    private void operacao(double valorOperacao) {
        Transacao novaTransacao = new Transacao(valorOperacao, DataBanco.agora());
        this.transacoes.add(novaTransacao);
    }

    public void sacar(double valor) {
        this.operacao(-valor);
    }

    public void depositar(double valor) {
        this.operacao(valor);
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
}