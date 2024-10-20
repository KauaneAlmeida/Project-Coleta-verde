```markdown
# Project Coleta Verde

## Descrição do Projeto
O **Project Coleta Verde** é uma aplicação Java desenvolvida para gerenciar a coleta seletiva de resíduos. O objetivo é facilitar a organização e o monitoramento das coletas, promovendo a sustentabilidade e a reciclagem.

## Funcionalidades
- Cadastro de pontos de coleta
- Agendamento de coletas
- Monitoramento em tempo real
- Relatórios de coleta

## Tecnologias Utilizadas
- Java 21
- Maven
- Azure Web Apps
- GitHub Actions

## Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)

Além disso, é bom ter um editor para trabalhar com o código, como [VSCode](https://code.visualstudio.com/).

## Instalação
```bash
# Clone este repositório
$ git clone https://github.com/seu-usuario/project-coleta-verde.git

# Acesse a pasta do projeto no terminal/cmd
$ cd project-coleta-verde

# Instale as dependências
$ mvn install
```

## Como Usar
```bash
# Execute a aplicação
$ mvn spring-boot:run
```
A aplicação estará disponível no endereço `http://localhost:8080`.

## Deploy
O deploy da aplicação é feito automaticamente utilizando GitHub Actions e Azure Web Apps. O pipeline de CI/CD está configurado para:
- Compilar o código
- Executar testes
- Implantar a aplicação nos ambientes de staging e produção

### Configuração do Pipeline
O arquivo de configuração do pipeline (`.github/workflows/main.yml`) está configurado da seguinte forma:

```yaml
name: Build and deploy JAR app to Azure Web App - project-coleta-verde
 
on: 
  push:  
    branches: 
      - main
  workflow_dispatch:
 
jobs:
  build:
    runs-on: windows-latest

    steps:
      - uses: actions/checkout@v4

      - name: Set up Java version
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'microsoft'

      - name: Build with Maven
        run: mvn clean install

      - name: Upload artifact for deployment job
        uses: actions/upload-artifact@v4
        with:
          name: java-app
          path: '${{ github.workspace }}/target/*.jar'

  deploy-staging:
    runs-on: windows-latest
    needs: build
    environment:
      name: 'Staging'
      url: ${{ steps.deploy-to-webapp.outputs.webapp-url }}
    permissions:
      id-token: write

    steps:
      - name: Download artifact from build job
        uses: actions/download-artifact@v4
        with:
          name: java-app
      
      - name: Login to Azure
        uses: azure/login@v2
        with:
          client-id: ${{ secrets.AZUREAPPSERVICE_CLIENTID }}
          tenant-id: ${{ secrets.AZUREAPPSERVICE_TENANTID }}
          subscription-id: ${{ secrets.AZUREAPPSERVICE_SUBSCRIPTIONID }}

      - name: Deploy to Azure Web App
        id: deploy-to-webapp
        uses: azure/webapps-deploy@v3
        with:
          app-name: 'project-coleta-verde'
          slot-name: 'staging'
          package: '*.jar'

  deploy-production:
    runs-on: windows-latest
    needs: [build, deploy-staging]
    environment:
      name: 'Production'
      url: ${{ steps.deploy-to-webapp.outputs.webapp-url }}
    permissions:
      id-token: write

    steps:
      - name: Download artifact from build job
        uses: actions/download-artifact@v4
        with:
          name: java-app
      
      - name: Login to Azure
        uses: azure/login@v2
        with:
          client-id: ${{ secrets.AZUREAPPSERVICE_CLIENTID }}
          tenant-id: ${{ secrets.AZUREAPPSERVICE_TENANTID }}
          subscription-id: ${{ secrets.AZUREAPPSERVICE_SUBSCRIPTIONID }}

      - name: Deploy to Azure Web App
        id: deploy-to-webapp
        uses: azure/webapps-deploy@v3
        with:
          app-name: 'project-coleta-verde'
          slot-name: 'production'
          package: '*.jar'
```

## Contribuição
Para contribuir com o projeto, siga estas etapas:
1. Faça um fork deste repositório.
2. Crie uma branch: `git checkout -b minha-branch`.
3. Faça suas alterações e confirme-as: `git commit -m 'minhas alterações'`.
4. Envie para o branch original: `git push origin minha-branch`.
5. Crie a solicitação de pull.

## Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

