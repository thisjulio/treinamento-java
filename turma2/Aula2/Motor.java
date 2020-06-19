import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
    Motor bancário

    // Criar contas (Corrente, Poupança, Investimento ....)
    // Operação sobre contas
    //  --> Saque
    //  --> Deposito
    //  --> Transferencia (entre contas)

 */

 /**
 * EXERCÍCIO 16/06/2020
 *   Criar método Depositar (DONE)
 *   Abstrair a conta para uma classe (DONE)
 *   Adicionar regra para que saques na conta de investimento só possam ser realizados 30 dias após o depósito (saque aniversário) (DONE)
 * 
 *
 */

/**
 * EXERCÍCIO 17/06/2020
 *   Criar método que retorna o extrato do cliente dado uma conta em um intervalo solicitado 
 */

 class Motor {

  private static void test(String text, boolean condition) {
    if (!condition) {
      System.out.println("Teste Falhou!");
      System.out.println("---------------------------");
      System.out.println(text);
      System.out.println("---------------------------");
      throw new AssertionError();  
    }
  }


  public static void main(String[] args) throws Exception {
      Date agora = new Date();
      Date _15Dias = Date.from(Instant.now().minus(15, ChronoUnit.DAYS));
      Date _30Dias = Date.from(Instant.now().minus(30, ChronoUnit.DAYS));
      
      Cliente u = new Cliente("Julio Cesar") { };
      Cliente u2 = new Usuario("Teste");

      Banco b = Banco.getInstancia();
      Banco b2 = Banco.getInstancia();

      test("Instancia do banco da SDK deve sempre ser a mesma", b == b2);

      b.criarConta(u, ContaInvestimento.class.getName());
      b.criarConta(u, ContaCorrente.class.getName());
      b.criarConta(u, ContaPoupanca.class.getName());
      
      b.criarConta(u2, ContaPoupanca.class.getName());

      test("Ao criar uma conta o saldo deve iniciar com o valor 0", b.saldo(u, ContaCorrente.class.getName()) == 0.0 && b.saldo(u, ContaCorrente.class.getName()) == 0.0 && b.saldo(u, ContaPoupanca.class.getName()) == 0.0 && b.saldo(u2, ContaPoupanca.class.getName()) == 0.0);

      DataBanco.mock(agora);
      b.sacar(30.0, u, ContaCorrente.class.getName());
      
      test("Ao realizar um saque de 30 reais o saldo de uma nova conta o saldo deve ser de -30", b.saldo(u, ContaCorrente.class.getName()) == -30.0);

      DataBanco.mock(_15Dias);
      b.transferir(10.0, u, ContaCorrente.class.getName(), u2, ContaPoupanca.class.getName());

      test("Ao realizar uma transferencia entre duas contas, uma deve ter um saldo subtraido do valor da transferência e outra deve ter o saldo incrementado do valor da transferência", b.saldo(u, ContaCorrente.class.getName()) == -40.0 && b.saldo(u2, ContaPoupanca.class.getName()) == 10.0);

      DataBanco.mock(_30Dias);
      b.depositar(150.0, u, ContaCorrente.class.getName());
      test("Ao realizar um depósito de 150 reais o saldo de uma que possui saldo de -40 deve ser de 110.0", b.saldo(u, ContaCorrente.class.getName()) == 110.0);

      DataBanco.mock(agora);

      b.depositar(100.0, u, ContaInvestimento.class.getName());
      b.sacar(50.0, u, ContaInvestimento.class.getName());
      test("Saques na conta de investimento só podem ser realizados 30 dias após o depósito (saque aniversário)", b.saldo(u, ContaInvestimento.class.getName()) == 100.0);


      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String dataHoje = dateFormat.format(agora);

      String data15Dias = dateFormat.format(_15Dias);
      String mockExtrato15Dias = "--------EXTRATO--------\n" + dataHoje + " --> (-30.0)\n-----------------------\n" + data15Dias+ " --> (-10.0)\n-----------------------\n";
      String extrato15Dias = b.consultarExtrato(u, ContaCorrente.class.getName(), 15);

      test("Retorna o extrato de 15 dias do cliente dado uma conta\nResultado:\n"+extrato15Dias+"\nEsperado:\n"+mockExtrato15Dias, extrato15Dias.equals(mockExtrato15Dias));

      String data30Dias = dateFormat.format(_30Dias);
      String mockExtrato30Dias = "--------EXTRATO--------\n" + dataHoje + " --> (-30.0)\n-----------------------\n" + data15Dias+ " --> (-10.0)\n-----------------------\n" + data30Dias + " --> (150.0)\n-----------------------\n";
      String extrato30Dias = b.consultarExtrato(u, ContaCorrente.class.getName(), 30);

      test("Retorna o extrato de 30 dias do cliente dado uma conta\nResultado:\n"+extrato30Dias+"\nEsperado:\n"+mockExtrato30Dias, extrato30Dias.equals(mockExtrato30Dias));
  }
 }