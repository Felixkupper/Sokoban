package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class host {

    public static void main (String [] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();

        System.out.println("client connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String strg = bf.readLine();
        System.out.println("client" + strg);
    }
}

