package connection;

import java.io.BufferedWriter;

public class ClientConnection {

    private final String username;

    private final BufferedWriter writer;

    public ClientConnection(
            String username,
            BufferedWriter writer
    ) {

        this.username = username;
        this.writer = writer;
    }

    public String getUsername() {
        return username;
    }

    public BufferedWriter getWriter() {
        return writer;
    }
}