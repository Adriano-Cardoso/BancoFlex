# BancoFlex

## Descrição

BancoFlex é um sistema bancário moderno desenvolvido com arquitetura orientada a eventos, utilizando Java 17, Spring Boot, Kafka, Docker e PostgreSQL. Este projeto tem como objetivo demonstrar um sistema bancário completo com serviços de contas, transações e notificações.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Apache Kafka
- Docker
- Docker Compose
- PostgreSQL

## Arquitetura

BancoFlex é composto por três serviços principais:

1. **AccountService** (`account-service`): Gerenciamento de contas bancárias.
2. **TransactionService** (`transaction-service`): Processamento de transações financeiras.
3. **NotificationService** (`notification-service`): Envio de notificações para clientes.

### Diagrama de Arquitetura

<img width="785" alt="image" src="https://github.com/user-attachments/assets/8cfaa325-d99f-4884-8e3f-8143786b61cd">


## Configuração do Kafka

### Tópicos Kafka

- `account-created`
- `account-updated`
- `transaction-initiated`
- `transaction-completed`
- `notification-send`

## Regras de Negócio

### AccountService
- **Criar Conta**: Uma nova conta pode ser criada com um saldo inicial.
- **Atualizar Conta**: Apenas contas existentes podem ser atualizadas.
- **Consultar Conta**: Contas podem ser consultadas por ID ou CPF do titular.

### TransactionService
- **Iniciar Transação**: Uma transação pode ser iniciada se houver saldo suficiente.
- **Concluir Transação**: A transação deve ser concluída e o saldo atualizado.
- **Validar Transação**: Transações devem ser validadas contra fraudes e duplicidades.

### NotificationService
- **Enviar Notificação**: Notificações são enviadas para o cliente sobre transações e atualizações de conta.
- **Templates de Notificação**: Templates são utilizados para formatar as notificações de forma consistente.

## Execução do Projeto

### Requisitos

- Docker
- Docker Compose

### Instruções

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/BancoFlex.git
   ```
   
2. Navegue até o projeto:
   ```bash
      cd BancoFlex
   ```

3. Inicie os serviços usando Docker Compose:
    ```bash
     docker-compose up
    ```

4. Os serviços estarão disponíveis nas seguintes portas:
  - `account-service`: http://localhost:8081
  - ` transaction-service`: http://localhost:8082
  - `notification-service`: http://localhost:8083







