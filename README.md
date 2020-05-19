# treinamento-java

## Criando um projeto com o Maven

Tutorial: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

Basicamente basta baixar o maven e instalar na sua máquina seguindo o tutorial: https://maven.apache.org/install.html

Uma outra forma mais rápida é de baixar e extrair o pacote binário `(apache-maven-X.X.X-bin.zip)` (substituir o x.x.x pela versão atual) do maven para uma pasta do seu computador e apartir desta pasta executar em linha de comando conforme os comandos abaixo:
```sh
cd /caminho/pasta/download/maven

# Macos/linux
./bin/mvn --help

# Windows
bin\mvn --help
```
A saida esperada deve ser algo como abaixo:

```sh
# Macos/linux
./bin/mvn --help

Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /Users/julio/Downloads/apache-maven-3.6.3
Java version: 1.8.0_212-release, vendor: JetBrains s.r.o, runtime: /Applications/Android Studio.app/Contents/jre/jdk/Contents/Home/jre
Default locale: pt_BR, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.6", arch: "x86_64", family: "mac"
```

Uma vez tendo sucesso com o comando anterior, podemos criar um projeto básico utilizando o archetype de quickstart do próprio maven:

```sh
./bin/mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Após isto uma pasta será criada a partir de onde sua linha de comando está sendo executada com o nome `my-app`, esta pasta pode ser importada por qualquer IDE (vscode, Eclipse ou IntelliJ).


## Criando webservice

Acesse [aqui](./Aula-04/webservice/README.md).

## Curiosidades

- String imutável passada por valor

```java
String a = "12312312";
String b = a;
a += "teste"; // a: 12312312teste, b: 12312312
```

## Soluções para problemas
- Para executar a compilação e execução dos arquivos você deve estar no mesmo diretório dos arquivos;