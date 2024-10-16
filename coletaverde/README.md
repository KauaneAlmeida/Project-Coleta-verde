# Coletaverde

Este é o projeto Coletaverde, desenvolvido com Spring Boot e configurado para ser executado em um ambiente Docker.

## Pré-requisitos
- Docker e Docker Compose instalados.
- Porta 80 disponível no seu sistema.

## Como Executar

1. Construir e Iniciar os Containers
Para construir e iniciar os containers, execute:
   docker-compose up -d --build

Este comando fará o build da imagem e iniciará o container em segundo plano (-d).

2. Acessar a Aplicação
Com os containers em execução, a aplicação estará disponível na porta 80. Acesse pelo navegador em:
   http://localhost

3. Parar os Containers:
Para parar os containers, use:
   docker-compose down

