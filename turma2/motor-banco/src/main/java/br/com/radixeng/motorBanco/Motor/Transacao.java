package br.com.radixeng.motorBanco.Motor;

import java.util.Date;

public class Transacao {
    private double valor;
    private Date data;

    public Date getData() {
        return data;
    }
    
    public double getValor() {
        return valor;
    }

    Transacao(double valor, Date data) {
        this.valor = valor;
        this.data = data;
    }
}