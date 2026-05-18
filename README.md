# Sistema de Comunicação TCP/IP em Java

## Descrição

Este projeto consiste em uma aplicação de comunicação em tempo real desenvolvida em Java utilizando sockets TCP/IP e interface gráfica com Swing.

O sistema permite múltiplos clientes conectados simultaneamente a um servidor central, possibilitando o envio e recebimento de mensagens em tempo real através de uma arquitetura cliente-servidor.

O objetivo principal do projeto é demonstrar o funcionamento de:

* Comunicação TCP/IP
* Programação Orientada a Objetos
* Programação Concorrente com Threads
* Manipulação de Sockets em Java
* Interfaces gráficas utilizando Swing
* Broadcast de mensagens
* Gerenciamento de múltiplos clientes

---

# Funcionalidades

* Servidor TCP/IP
* Conexão de múltiplos clientes
* Interface gráfica para servidor e cliente
* Envio de mensagens em tempo real
* Broadcast de mensagens para todos os clientes
* Identificação de usuários conectados
* Desconexão de usuários
* Tratamento de conexões simultâneas utilizando Threads

---

# Tecnologias Utilizadas

* Java
* Java Swing
* TCP/IP
* Sockets
* Threads
* Git
* GitHub

---

# Estrutura do Projeto

```text
src/
├── chatform/
│   ├── Client.java
│   ├── Login.java
│   ├── SetupServer.java
│   └── Start.java
│
├── connection/
│   ├── ClientConnection.java
│   └── Server.java
│
└── main/
    └── Run.java
```

---

# Arquitetura do Sistema

## Camada de Interface

Responsável pelas telas gráficas do sistema.

### Start.java

Tela inicial responsável por permitir a escolha entre iniciar como servidor ou cliente.

### Login.java

Tela responsável por coletar:

* IP do servidor
* Porta
* Nome do usuário

### SetupServer.java

Tela responsável por:

* Configuração do servidor
* Inicialização do ServerSocket
* Exibição do IP e porta do servidor

### Client.java

Tela principal do chat.

Responsável por:

* Envio de mensagens
* Exibição das mensagens recebidas
* Conexão com o servidor
* Desconexão

---

## Camada de Conexão

Responsável pela comunicação TCP/IP.

### Server.java

Responsável por:

* Gerenciar clientes conectados
* Receber mensagens
* Fazer broadcast para todos os clientes
* Controlar conexões simultâneas

Cada cliente conectado possui uma Thread dedicada.

### ClientConnection.java

Responsável por:

* Escutar continuamente as mensagens do servidor
* Separar a lógica de recepção da interface gráfica

---

## Classe Principal

### Run.java

Ponto inicial da aplicação.

Responsável por iniciar o sistema.

---

# Funcionamento do Sistema

## Fluxo do Servidor

```text
Run.java
    ↓
Start.java
    ↓
SetupServer.java
    ↓
ServerSocket
    ↓
accept()
    ↓
Server Thread
```

---

## Fluxo do Cliente

```text
Run.java
    ↓
Start.java
    ↓
Login.java
    ↓
Client.java
    ↓
Socket(IP, Porta)
```

---

# Instalação do Java

Para executar o projeto é necessário possuir o Java JDK 17 ou superior instalado na máquina.

## Linux (Ubuntu/Linux Mint)

Instalação do OpenJDK 17:

```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

Verificar instalação:

```bash
java -version
```

---

## Windows

Baixe e instale o OpenJDK através do Eclipse Adoptium:

* https://adoptium.net/

Após a instalação, verifique no Prompt de Comando:

```bash
java -version
```

---

## Verificação Esperada

O terminal deverá exibir algo semelhante a:

```text
openjdk version "17"
```

---

# Como Executar

## Requisitos

* Java 17 ou superior

---

## Executando pelo VSCode

1. Abra o projeto
2. Execute a classe:

```text
main.Run
```

---

## Executando via terminal

### Compilar

```bash
mkdir bin

javac -d bin src/main/*.java src/chatform/*.java src/connection/*.java
```

### Gerar JAR

```bash
jar cfe chat-tcpip.jar main.Run -C bin .
```

### Executar

```bash
java -jar chat-tcpip.jar
```

---

# Como Testar em Rede

## Servidor

1. Execute o sistema
2. Escolha a opção "Servidor"
3. Defina a porta
4. Inicie o servidor

---

## Cliente

1. Execute o sistema
2. Escolha a opção "Cliente"
3. Informe:

* IP do servidor
* Porta
* Nome do usuário

4. Clique em conectar

---

# Exemplo de Rede Local

Servidor:

```text
192.168.0.21
```

Cliente:

```text
IP: 192.168.0.21
Porta: 45454
```

---

# Conceitos Aplicados

* Comunicação Cliente-Servidor
* TCP/IP
* Threads
* Concorrência
* Broadcast
* Programação Orientada a Objetos
* Interfaces Gráficas
* Gerenciamento de Conexões

---

# Melhorias Futuras

* Criptografia de mensagens
* Login com autenticação
* Banco de dados
* Histórico de mensagens
* Lista de usuários online
* Salas privadas
* Interface moderna
* Migração para JavaFX

---

# Integrantes

* Thiago Chaves dos Santos
* Alexandrino Elizeu da Silva Guedes

---

# Referências

Este projeto foi desenvolvido para fins acadêmicos com o objetivo de demonstrar conceitos de comunicação em redes utilizando o protocolo TCP/IP em Java.

Durante o desenvolvimento foram utilizados como base de estudo:

* Documentação oficial da linguagem Java
* Materiais acadêmicos sobre arquitetura cliente-servidor
* Estudos sobre Java Socket Programming
* Conteúdos relacionados a Threads e concorrência em Java
* Projetos públicos disponíveis no GitHub voltados para comunicação TCP/IP

Parte da estrutura inicial e conceitos de implementação foram inspirados no repositório:

* https://github.com/ogabriel/APS-UNIP-5sem

O sistema foi posteriormente adaptado, reorganizado, documentado e refatorado para atender aos objetivos acadêmicos desta aplicação.

As principais modificações realizadas incluem:

* Reorganização completa da arquitetura do projeto
* Separação em camadas (`main`, `chatform` e `connection`)
* Refatoração da lógica de comunicação
* Implementação e reorganização das Threads
* Melhorias na estrutura orientada a objetos
* Criação da documentação técnica
* Melhorias na interface gráfica
* Organização do fluxo de conexão cliente-servidor

Este projeto possui finalidade exclusivamente educacional e acadêmica.

---

# Licença

Este projeto utiliza a licença MIT.
