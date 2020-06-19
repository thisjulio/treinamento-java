package br.com.radixeng.motorBanco.Motor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco {
    Map<String, Map<String, Conta>> repositorioContas = new HashMap<>();

    private static Banco instancia;

    private Banco() { }
    
    public static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
        }

        return instancia;
    }
    
    public void criarConta(Cliente usuario, String tipoConta) throws Exception {
        if (repositorioContas.get(usuario.getIdentificador()) != null) {
            Conta novaConta = Conta.criarConta(tipoConta);
            repositorioContas.get(usuario.getIdentificador()).put(tipoConta, novaConta);
        } else {
            HashMap<String, Conta> conta = new HashMap<>();
            Conta novaConta = Conta.criarConta(tipoConta);
            conta.put(tipoConta, novaConta);
            repositorioContas.put(usuario.getIdentificador(), conta);
        }
        
    }


    public void sacar(double valor, Cliente usuario, String tipoConta, Cliente usuarioOrigem) {
        Conta conta = obterConta(usuario, tipoConta);
        conta.sacar(valor, usuarioOrigem, usuario);
    }

    public void depositar(double valor, Cliente usuario, String tipoConta, Cliente usuarioOrigem) {
        Conta conta = obterConta(usuario, tipoConta);
        conta.depositar(valor, usuarioOrigem, usuario);
    }

    private Conta obterConta(Cliente usuario, String tipoConta) {
        Conta conta = repositorioContas.get(usuario.getIdentificador()).get(tipoConta);
        return conta;
    }

    public void transferir(double valor, Cliente usuarioOrigem, String tipoContaOrigem, Cliente usuarioDestino, String tipoContaDestino) {
        this.sacar(valor, usuarioOrigem, tipoContaOrigem, usuarioDestino);
        this.depositar(valor, usuarioDestino, tipoContaDestino, usuarioOrigem);
    }

    public double saldo(Cliente usuario, String tipoConta){
        return repositorioContas.get(usuario.getIdentificador()).get(tipoConta).getSaldo();
    }

    public String consultarExtrato(Cliente usuario, String tipoConta, int intervalo){
        String extrato = "--------EXTRATO--------\n";

        List<Operacao> operacoes = this.consultaExtrato(usuario, tipoConta, intervalo);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (Operacao operacao : operacoes) {
            String data = dateFormat.format(operacao.getData());
            extrato += data + " --> (" + operacao.getValor() + ")\n-----------------------\n";
        }

        return extrato;
    }

    public List<Operacao> consultaExtrato(Cliente usuario, String tipoConta, int intervalo) {
        List<Operacao> extrato = new ArrayList<>();

        Conta conta = obterConta(usuario, tipoConta);
        
        Date dateAgora = DataBanco.agora();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateAgora);
        calendar.add(Calendar.DAY_OF_MONTH, -intervalo);
        Date dateFiltro = calendar.getTime();
        
        for (Operacao operacao : conta.getTransacoes()) {
            if(operacao.getData().compareTo(dateFiltro) >= 0) {
                extrato.add(operacao);
            }
        }

        return extrato;
    }
}