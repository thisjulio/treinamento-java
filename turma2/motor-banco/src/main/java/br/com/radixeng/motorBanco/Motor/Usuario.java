package br.com.radixeng.motorBanco.Motor;

public class Usuario extends Cliente {
    
    private String nome;
    
    public Usuario(String nome){
        super(nome);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    
}