package Server;


import Gui.Display;
import Gui.LevelManager;
import Gui.ServerScene;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class GuiClient {

    private static PrintWriter prW;
    private static Socket s;
    private static Scene clientScene;
    private static FlowPane gmap;
    private static char[][] cmap;
    private static InputStream is;

    public static void getNextKey(Stage primaryStage, KeyEvent event){
        String pressedKey = event.getCode().getName();
        prW.println(pressedKey);
        prW.flush();

        clientScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                getNextKey(primaryStage, event);
            }
        });
    }

    private static void startConnection(String ip) throws IOException {
        s = new Socket(ip, ServerScene.port);

        System.out.println("Server connected");

        prW = new PrintWriter(s.getOutputStream());

        is = s.getInputStream();

    }

    public static Scene getClientScene(Stage primaryStage, String ip) throws IOException {
        startConnection(ip);



        clientScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                getNextKey(primaryStage, event);

            }
        });

        //Server erstellen
        Runnable r = new Runnable() {
            public void run(){
                try {
                    cmap = readBoard(is);
                    gmap = Display.drawGameFrame(cmap);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                run();
            }
        };
        Thread t = new Thread(r);
        t.start();
        t.run();

        return clientScene;
    }

    private static char[][] readBoard(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        return (char[][]) ois.readObject();
    }
}
