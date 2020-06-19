package br.com.radixeng.motorBanco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.radixeng.motorBanco.Motor.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private Banco b = Banco.getInstancia();
    private Cliente u = new Cliente("Julio Cesar") { };
    private Cliente u2 = new Usuario("Teste");
    
    /**
     * Instancia do banco da SDK deve sempre ser a mesma
     */
    @Test
    public void instanciaDeBancoDeveSerAMesma() {
        Banco b2 = Banco.getInstancia();
        assertEquals(b, b2);
    }

    /**
     * Ao criar uma conta o saldo deve iniciar com o valor 0
     */
    @Test
    public void saldoDeContasDeveIniciarComZero() throws Exception {
        b.criarConta(u, ContaInvestimento.class.getName());
        b.criarConta(u, ContaCorrente.class.getName());
        b.criarConta(u, ContaPoupanca.class.getName());
        
        b.criarConta(u2, ContaPoupanca.class.getName());

        assertEquals(0.0, b.saldo(u, ContaCorrente.class.getName()), 0.0); 
        assertEquals(0.0, b.saldo(u, ContaCorrente.class.getName()), 0.0); 
        assertEquals(0.0, b.saldo(u, ContaPoupanca.class.getName()), 0.0); 
        assertEquals(0.0, b.saldo(u2, ContaPoupanca.class.getName()), 0.0); 
    }

    // Exercício 18/06/2020
    // Dar Continuidade aos testes do motor
}
