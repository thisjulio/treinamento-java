package br.com.radixeng.motorBanco;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.radixeng.motorBanco.Motor.Banco;
import br.com.radixeng.motorBanco.Motor.Cliente;
import br.com.radixeng.motorBanco.Motor.ContaCorrente;

class NovaContaResponse {
    public boolean status;
}

class NovaContaRequest {
    public String nome;
}

@Path("/")
public class Servico {
    private Banco motorBanco = Banco.getInstancia();

    @Path("conta")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public NovaContaResponse novaConta(NovaContaRequest request) throws Exception {
        Cliente cliente = new Cliente(request.nome){ };
        motorBanco.criarConta(cliente, ContaCorrente.class.getName());

        NovaContaResponse r = new NovaContaResponse();
        r.status = true;

        return r;
    }

    @GET
    @Path("helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }
}