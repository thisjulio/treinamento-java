package br.com.radixeng.treinamentojava.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/banco")
public class Servico {

    static private IBanco banco = Banco.obterBanco();


    @GET
    @Path("criarConta/{identificadorUsuario}/{tipo}")
    @Produces(MediaType.TEXT_PLAIN)
    public String criarConta(@PathParam("identificadorUsuario") final String identificadorUsuario,
                             @PathParam("tipo") final String tipoDeConta) {

        IUsuario usuario = new IUsuario() {
            @Override
            public String obterIdentificador() {
                return identificadorUsuario;
            }
        };

        Boolean contaFoiCriada = Servico.banco.criarConta(usuario, TipoDeConta.valueOf(tipoDeConta));

        if (contaFoiCriada) {
            return Servico.banco.verTodosOsSaldos();
        } else {
            return "Não foi possível criar esta conta";
        }
    }

    // depositar

    // sacar

    // transferir

    // ver todos os saldos

    // ver transacoes de um usuario
}