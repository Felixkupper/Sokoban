package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main (String [] args) throws IOException{
        Socket s = new Socket("localhost", 5000);

        PrintWriter prW = new PrintWriter(s.getOutputStream());
        prW.println("Test");
        prW.flush();
    }
}
