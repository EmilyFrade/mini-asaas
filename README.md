# Mini Asaas

![Apache Groovy](https://img.shields.io/badge/Apache%20Groovy-4298B8.svg?style=plastic&logo=Apache+Groovy&logoColor=white)
![Grails](https://img.shields.io/badge/Grails-0175C2.svg?style=plastic&logo=Grails&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1.svg?style=plastic&logo=MySQL&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=plastic&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=plastic&logo=github&logoColor=white)

## Descrição

Projeto desenvolvido para a 2ª etapa do Programa de Estágio do Asaas,
utilizando o framework Grails.  
Consta de um pequeno sistema de cobranças, onde o usuário cadastrado
pode gerar cobranças a serem pagas por seus clientes.

## Instruções de Instalação

### Pré-requisitos

- Java 11.0.17-amzn
- Grails 5.0.0

Para instalação dos pré-requisitos, recomendamos o uso do 
[SDKMAN!](https://sdkman.io/install), que facilita a instalação e 
o gerenciamento de versões de diversas ferramentas de desenvolvimento.

### Instalação

1. Clone o repositório
   ```sh
   git clone https://github.com/EmilyFrade/mini-asaas.git
   ```
   
2. Acesse a pasta do projeto
   ```sh
    cd mini-asaas
    ```
   
3. Adicione um arquivo .env na raiz do projeto conforme o arquivo .env.example e preencha com as informações necessárias
   ```sh
    cp .env.example .env
    ```
   
4. Execute o projeto
    ```sh
     grails run-app
     ```
   
5. Acesse o projeto no navegador: [http://localhost:8080](http://localhost:8080)

## Funcionalidades

- Cadastro de usuários
- Cadastro de pagadores
- Geração de cobranças
- Confirmação de pagamento
- Vencimento automático de cobranças
- Esteira de notificações por e-mail
- Gestão multi-usuário

## Tecnologias Utilizadas

- Grails
- Groovy e Groovy Server Pages (GSP)
- Git e GitHub
- SQL com MySQL
- Estilização com Bootstrap e CSS

## Requisitos de UI

- [ ] Tela de login de usuário
- [ ] Tela de cadastro de usuário
- [ ] Tela de listagem de pagadores, com ações de edição, exclusão e restauração
  - [ ] Ação de restauração de pagadores excluídos
  - [ ] Ação de exclusão de pagadores
  - [ ] Ação de edição de pagadores
- [ ] Tela de cadastro de pagadores
- [ ] Tela de detalhes de um pagador
- [ ] Tela de listagem de cobranças
  - [ ] Ação de exclusão de cobranças
  - [ ] Ação de edição de cobranças
  - [ ] Ação de confirmação de recebimento de pagamento
  - [ ] Ação de exibir comprovante de pagamento
- [ ] Tela de cadastro de cobranças
- [ ] Tela de detalhes de uma cobrança
- [ ] Seção de Notificações

## Desenvolvedores

- [Emily Frade](https://github.com/EmilyFrade)
- [Ícaro Teles](https://github.com/icarobteles)

