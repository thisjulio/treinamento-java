# Criando um Webservice

Vamos iniciar criando um projeto pelo archetype:

```sh
mvn archetype:generate -DgroupId=br.com.radixeng.treinamentojava.app -DartifactId=simple-webservice -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Uma pasta chamada `simple-webservice` será criada, você pode abrir a mesma com sua IDE de preferência.

No projeto, edite o arquivo `pom.xml` para adicionar as seguintes dependencias dentro da tag `<dependencies>` do arquivo:

```xml
    <!-- Jetty internal Dependencies -->
    <dependency>
      <groupId>javax.activation</groupId>
      <artifactId>activation</artifactId>
      <version>1.1.1</version>
    </dependency>
    <dependency>
      <groupId>javax.xml.bind</groupId>
      <artifactId>jaxb-api</artifactId>
      <version>2.3.0</version>
    </dependency>

    <!-- Jetty -->
    <dependency>
      <groupId>org.eclipse.jetty</groupId>
      <artifactId>jetty-server</artifactId>
      <version>9.1.3.v20140225</version>
    </dependency>
    <dependency>
      <groupId>org.eclipse.jetty</groupId>
      <artifactId>jetty-servlet</artifactId>
      <version>9.1.3.v20140225</version>
    </dependency>

    <!-- Glass Fish -->
    <dependency>
      <groupId>org.glassfish.jersey.core</groupId>
      <artifactId>jersey-server</artifactId>
      <version>2.7</version>
    </dependency>
    <dependency>
      <groupId>org.glassfish.jersey.containers</groupId>
      <artifactId>jersey-container-servlet-core</artifactId>
      <version>2.7</version>
    </dependency>
    <dependency>
      <groupId>org.glassfish.jersey.containers</groupId>
      <artifactId>jersey-container-jetty-http</artifactId>
      <version>2.7</version>
    </dependency>
    <dependency>
      <groupId>org.glassfish.jersey.media</groupId>
      <artifactId>jersey-media-moxy</artifactId>
      <version>2.7</version>
    </dependency>
```

Após isto, sua IDE poderá automaticamente executar o install das dependências, caso isto não ocorra você pode executar em linha de comando a seguinte instrução:

```sh
mvn install
```

Após finalizar a instalação das dependências deveremos adicionar as dependências, devemos realizar a implementação do servidor http, para isso navegue até o arquivo `src/main/java/br/com/radixeng/treinamentojava/app/App.java` e o deixe no seguinte formato:

```java
package br.com.radixeng.treinamentojava;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * HTTP Server
 *
 */
public class App 
{
    public static void main(String[] args) {

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8181).build();
        ResourceConfig config = new ResourceConfig(Servico.class);
        Server server = JettyHttpContainerFactory.createServer(baseUri, config);
    }
}

```

Após isto crie a classe `Servico.java` que irá conter a chamada do nosso helloWorld e a deixe com o seguinte formato:

```java
package br.com.radixeng.treinamentojava;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Servico {

    @GET
    @Path("helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World";
    }
}
```

Execute o programa na sua IDE, e após ela iniciar o processo, você pode ir para o seu navegador e acessar http://localhost:8181/helloWorld.

Com isso você deve visualizar no como resposta no seu navegador a palavra `Hello World`.

Você pode conferir o projeto completo deste exemplo na pasta [example/simple-webservice](./example/simple-webservice).