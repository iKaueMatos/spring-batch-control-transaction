# Controle Transacional com Spring Batch

Este projeto demonstra como o Spring Batch gerencia transações dentro de um chunk. O projeto possui um job que lê um arquivo de usuários e os insere no banco de dados.

## Objetivo do Projeto

O objetivo principal deste projeto é ilustrar como o Spring Batch pode ser configurado para gerenciar transações em múltiplos bancos de dados, garantindo a consistência dos dados mesmo em cenários complexos.

## Roteiro de Desenvolvimento

O processo de desenvolvimento foi dividido nas seguintes etapas:

- [x] **Controle transacional com 1 banco de dados**: Configuração inicial do projeto utilizando um único banco de dados para gerenciar as transações.
  
- [x] **Adição de um segundo banco de dados**: Introdução de um segundo banco de dados para escrita, demonstrando como a transação deixa de funcionar corretamente sem ajustes adicionais.

- [x] **Ajuste para utilizar transação no banco secundário**: Implementação das configurações necessárias para garantir que as transações funcionem corretamente ao utilizar múltiplos bancos de dados.

## Como Funciona o Spring Batch

O Spring Batch é uma framework poderosa para o processamento de grandes volumes de dados em batch. Ele permite que você divida seu processamento em unidades menores e gerencie transações de forma eficiente.

### Principais Componentes do Spring Batch

- **Job**: A unidade de trabalho que contém os passos a serem executados.
- **Step**: Cada etapa do job. Cada step pode ter um reader, um processor e um writer configurados.
- **ItemReader**: Responsável por ler os dados de uma fonte (por exemplo, banco de dados, arquivo CSV).
- **ItemProcessor**: Processa os dados lidos antes de enviá-los ao writer.
- **ItemWriter**: Responsável por escrever os dados em um destino (por exemplo, banco de dados, arquivo de saída).

## Configuração do Ambiente

### Pré-requisitos

1. **Java 17 ou superior**: O projeto foi desenvolvido utilizando Java 17.
2. **Spring Boot**: O projeto utiliza o Spring Boot para facilitar a configuração e execução do Spring Batch.
3. **Docker**: Utilizado para configurar e gerenciar os bancos de dados MySQL.

### Passos para Executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/iKaueMatos/control-transaction
    cd control-transaction
    ```

2. Suba os containers do MySQL:
    ```bash
    docker-compose up -d
    ```

3. Compile o projeto:
    ```bash
    ./mvnw clean install
    ```

4. Execute o job:
    ```bash
    ./mvnw spring-boot:run
    ```

5. Monitore os logs para verificar a execução do job e a gestão das transações.

## Resultados Esperados

Após a configuração correta, você deve observar que as transações são gerenciadas de forma eficiente, garantindo a consistência dos dados mesmo ao utilizar múltiplos bancos de dados.

## Referências

- [Vídeo do Youtube](https://youtu.be/iZXYG7fM8jI)
- [Curso de Spring Batch](https://www.udemy.com/course/curso-para-desenvolvimento-de-jobs-com-spring-batch/?referralCode=8743E206FA9240686B20)
