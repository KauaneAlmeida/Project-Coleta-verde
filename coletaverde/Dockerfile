FROM ubuntu:latest AS build

# Definindo argumento de build para a versão do JDK
ARG JDK_VERSION=21

# Usando o argumento para instalar a versão específica do JDK
RUN apt-get update && apt-get install openjdk-${JDK_VERSION}-jdk -y

# Copiando o código da aplicação
COPY . .

# Instalando o Maven
RUN apt-get install maven -y

# Build do projeto
RUN mvn clean install -DskipTests

# Usando a imagem slim do JDK 21 para rodar a aplicação
FROM openjdk:21-jdk-slim

# Definindo uma variável de ambiente para a descrição
ENV APP_DESCRIPTION="Coletaverde é um app que ajuda as pessoas na gestão de resíduos"
LABEL description=${APP_DESCRIPTION}

# Expondo a porta 80
EXPOSE 80

# Copiando o arquivo .jar gerado no build para a nova imagem
COPY --from=build /target/*.jar coletaverde.jar

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "coletaverde.jar"]
