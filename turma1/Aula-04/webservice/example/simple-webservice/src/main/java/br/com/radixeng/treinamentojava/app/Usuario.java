package br.com.radixeng.treinamentojava.app;

class Usuario implements IUsuario{
    private String nome;

    Usuario(String nome) {
        this.nome = nome;
    }

    public String obterNome() {
        return this.nome;
    }

    @Override
    public String obterIdentificador() {
        return this.obterNome();
    }
}
