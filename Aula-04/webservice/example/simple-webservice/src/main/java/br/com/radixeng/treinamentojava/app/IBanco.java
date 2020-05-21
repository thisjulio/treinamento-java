package br.com.radixeng.treinamentojava.app;

import java.util.Map;

public interface IBanco {
    Boolean criarConta(IUsuario usuario, TipoDeConta tipo);

    void depositar(Double valor, IUsuario usuario, TipoDeConta tipo);

    public Double sacar(Double valor, IUsuario usuario, TipoDeConta tipo);

    String verTodosOsSaldos();

    Map<TipoDeConta, Conta> obterContasUsuario(IUsuario usuario);
}
