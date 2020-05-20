package br.com.radixeng.treinamentojava.app;

public interface IBanco {
    Boolean criarConta(IUsuario usuario, TipoDeConta tipo);
    String verTodosOsSaldos();
}
