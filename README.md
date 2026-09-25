# Chat TCP/IP em Java

Projeto acadêmico em equipe: aplicação de chat com **Java Swing** e **sockets TCP/IP**. Um servidor aceita múltiplos clientes, recebe mensagens e as envia aos demais. O projeto exercita comunicação cliente-servidor, threads e interface gráfica.

## Funcionalidades

- Escolha entre iniciar servidor ou cliente.
- Conexão por IP e porta com identificação do usuário.
- Troca de mensagens em tempo real e broadcast.
- Tratamento de clientes simultâneos com threads.

## Executar

Requer JDK 17 ou superior. Na raiz do projeto:

```bash
mkdir -p bin
javac -d bin src/main/*.java src/chatform/*.java src/connection/*.java
java -cp bin main.Run
```

Para gerar um arquivo executável:

```bash
jar cfe chat-tcpip.jar main.Run -C bin .
java -jar chat-tcpip.jar
```

No programa, inicie um servidor, escolha uma porta e conecte os clientes informando o IP da máquina servidora e a mesma porta. Para testar no mesmo computador, use `127.0.0.1`. Para máquinas distintas, elas precisam se comunicar na rede e a porta deve estar liberada.

## Organização

- `src/main/Run.java`: ponto de entrada.
- `src/chatform/`: telas de início, conexão e chat.
- `src/connection/`: servidor e conexão do cliente.

## Contexto e créditos

Desenvolvido para fins acadêmicos por **Thiago Chaves dos Santos** e **Alexandrino Elizeu da Silva Guedes**. Parte da estrutura inicial e dos conceitos foi inspirada no [projeto de ogabriel](https://github.com/ogabriel/APS-UNIP-5sem), posteriormente adaptado para este trabalho. Consulte o histórico de commits para acompanhar as alterações. Licença: [MIT](LICENSE).
