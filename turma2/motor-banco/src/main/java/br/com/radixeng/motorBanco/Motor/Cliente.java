package br.com.radixeng.motorBanco.Motor;

abstract public class Cliente {
    protected String identificador;

    public Cliente(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }
}