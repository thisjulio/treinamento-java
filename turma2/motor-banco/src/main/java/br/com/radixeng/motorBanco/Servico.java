package br.com.radixeng.motorBanco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.radixeng.motorBanco.Motor.Banco;
import br.com.radixeng.motorBanco.Motor.Cliente;
import br.com.radixeng.motorBanco.Motor.Conta;
import br.com.radixeng.motorBanco.Motor.ContaCorrente;

class NovaContaRequest {
    public String nome;
}

class OperacaoRequest {
    public double valor;
    public String identificadorDestino;
    public String identificadorOrigem;
    public String tipoContaOrigem;
    public String tipoContaDestino;
}

class TipoContaResponse {
    public List<String> tipos = new ArrayList<>();
}

@Path("/")
public class Servico {
    private Banco motorBanco = Banco.getInstancia();

    private static Map<String, Cliente> repositorioUsuarios = new HashMap<>();

    @Path("conta")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response novaConta(NovaContaRequest request) throws Exception {
        Cliente cliente = new Cliente(request.nome){ };

        repositorioUsuarios.put(request.nome, cliente);

        motorBanco.criarConta(cliente, ContaCorrente.class.getName());

        return Response.accepted().build();
    }

    @POST
    @Path("operacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response operacao(OperacaoRequest request) {
        
        if(request.identificadorDestino == null && request.tipoContaDestino == null) {
            
            if(request.valor < 0) {
                motorBanco.sacar(request.valor, repositorioUsuarios.get(request.identificadorOrigem), request.tipoContaOrigem, repositorioUsuarios.get(request.identificadorOrigem));
            } else {
                motorBanco.depositar(request.valor, repositorioUsuarios.get(request.identificadorOrigem), request.tipoContaOrigem, repositorioUsuarios.get(request.identificadorOrigem));
            }

        } else if(request.valor >= 0 && request.identificadorDestino != null && request.tipoContaOrigem != null && request.tipoContaDestino != null) {
            motorBanco.transferir(request.valor, repositorioUsuarios.get(request.identificadorOrigem), request.tipoContaOrigem, repositorioUsuarios.get(request.identificadorDestino), request.tipoContaDestino);
        } else {
            return Response.status(400).build();
        }

        return Response.accepted().build();
    }

    @GET
    @Path("/tipo-conta")
    @Produces(MediaType.APPLICATION_JSON)
    public TipoContaResponse tiposDeConta() {
        TipoContaResponse response = new TipoContaResponse();
        response.tipos = Conta.tiposDeConta();

        return response;
    }

    @GET
    @Path("helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }
}