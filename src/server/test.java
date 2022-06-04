package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class test {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        String greeting;
        try {
            serverSocket = new ServerSocket(port);

            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            greeting = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if ("hello server".equals(greeting)) {
            out.println("hello client");
        } else {
            out.println("unrecognised greeting");
        }
    }

    public void stop() {
        try {
            in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        test server=new test();
        server.start(1567);
    }
}
