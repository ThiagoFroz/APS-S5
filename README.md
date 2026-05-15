# Sistema de Comunicação TCP/IP

Projeto desenvolvido em Java utilizando sockets TCP/IP para realizar comunicação entre cliente e servidor em tempo real.

## 📌 Descrição

Este projeto implementa um sistema de chat baseado em arquitetura cliente-servidor utilizando o protocolo TCP/IP.

O servidor é responsável por aceitar conexões de múltiplos clientes, enquanto os clientes podem enviar e receber mensagens em tempo real através da rede.

O objetivo do projeto é demonstrar na prática conceitos de:

* Comunicação em rede
* Protocolo TCP/IP
* Programação cliente-servidor
* Sockets em Java
* Threads e concorrência
* Troca de mensagens em tempo real

---

## 🚀 Funcionalidades

* Conexão entre cliente e servidor
* Comunicação em tempo real
* Envio e recebimento de mensagens
* Suporte para múltiplos clientes
* Interface simples e funcional
* Tratamento básico de conexão

---

## 🛠️ Tecnologias utilizadas

* Java
* Socket TCP/IP
* Threads
* Programação Orientada a Objetos (POO)

---

## 📂 Estrutura do Projeto

```text
src/
 ├── cliente/
 │    ├── Cliente.java
 │    ├── TelaChat.java
 │
 ├── servidor/
 │    ├── Servidor.java
 │    ├── GerenciadorClientes.java
```

---

## ▶️ Como executar

### 1. Inicie o servidor

Execute a classe:

```java
Servidor.java
```

---

### 2. Execute o cliente

Abra uma ou mais instâncias do cliente:

```java
Cliente.java
```

---

### 3. Configure o IP

No cliente, utilize o IP da máquina onde o servidor está sendo executado.

Exemplo:

```java
192.168.0.10
```

---

## 🌐 Funcionamento

1. O servidor inicia e fica aguardando conexões.
2. O cliente se conecta ao servidor utilizando IP e porta.
3. As mensagens enviadas são transmitidas entre os usuários conectados.
4. Cada cliente conectado é tratado em uma thread separada.

---

## 🎯 Objetivo acadêmico

Projeto desenvolvido para fins acadêmicos com o objetivo de aplicar conceitos de redes de computadores e programação distribuída.

---

## 👨‍💻 Autor

Thiago Froz Chaves
