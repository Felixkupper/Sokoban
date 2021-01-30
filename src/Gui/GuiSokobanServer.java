package Gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class GuiSokobanServer {

    private static ServerSocket ss;
    private static Socket s;
    private static InputStreamReader in;
    private static BufferedReader bf;
    private static OutputStream out;

    /*
        Server wird gestart
        Wenn Scene aufgerufen wird muss der Host eine Map auswählen
        Dann wird Map geladen
        Map wird an den Client geschickt
        Thread starten zum abrufen der eingegeben Keys des Clients
            Empfängt Keys
            Berechnet Map
            Schickt Map zurück an den Client
            Aktualisiert das FlowPane und die Scene
            Thread wiederholt sich
        KeyListener wird gestartet
            Bei KeyEvent berechnet er die Map neu
            Schickt Map zurück an den Client
            Aktualisiert das FlowPane und die Scene
            getNextKey wiederholt sich

     */

    private static Button[] button = new Button[4];

    //neue Button erstellen
    private static void Inil() {
        button[0] = ButtonNew.generateButton(300, 50);
        button[0].setText("1: Kuntzes Kaputte Karte / Flavios Fehlerhaftes Feld");

        button[1] = ButtonNew.generateButton( 300, 50);
        button[1].setText("2: Tims Tolles Terrain");

        button[2] = ButtonNew.generateButton( 300, 50);
        button[2].setText("3: Zachs Pacmap");

        button[3] = ButtonNew.generateButton( 300, 50);
        button[3].setText("4: Map 1 but it works ;)");
    }

    // Map wird nach Move immer neu geladen
    private static void getNextKey(Stage primaryStage, KeyEvent event) throws IOException {
        String pressedKey = event.getCode().getName();
        LevelManager.row = LevelManager.firstPlayer.inputLogic(LevelManager.row, pressedKey);

        sendMapToClient(out, LevelManager.row);
        LevelManager.map = Display.drawGameFrame(LevelManager.row);


        LevelManager.level = new Scene(LevelManager.map,1200, 600);
        primaryStage.setScene(LevelManager.level);
        LevelManager.level.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    getNextKey(primaryStage, event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void startServerScene(Stage primaryStage) throws IOException {
        ss = new ServerSocket(ServerScene.port);
        s = ss.accept();

        System.out.println("client connected");

        in = new InputStreamReader(s.getInputStream());
        bf = new BufferedReader(in);

        out = s.getOutputStream();

        Inil();

        //Ausführung des Button zuwesien
        button[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(LevelManager.startLevel(0));
                LevelManager.level.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        try {
                            getNextKey(primaryStage, event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        button[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(LevelManager.startLevel(1));
                LevelManager.level.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        try {
                            getNextKey(primaryStage, event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        button[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(LevelManager.startLevel(2));
                LevelManager.level.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        try {
                            getNextKey(primaryStage, event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });

        button[3].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setScene(LevelManager.startLevel(3));
                LevelManager.level.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        try {
                            getNextKey(primaryStage, event);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });


        //Neues Fenster in der Scene erstllen für Buttons
        VBox root = new VBox();

        root.setBackground(Background.EMPTY);
        root.setStyle("-fx-background-color: #000000;");

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.getChildren().addAll(button[0], button[1], button[2], button[3]);

        //HauptScene erstellen
        Scene scene = new Scene(root, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sokoban");
        primaryStage.show();

        //Server erstellen
        Runnable r = new Runnable() {
            public void run(){
                String key = " ";
                try{
                    key = bf.readLine();
                    LevelManager.row = LevelManager.secondPlayer.inputLogic(LevelManager.row, key);
                    try {
                        sendMapToClient(out, LevelManager.row);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    LevelManager.map = Display.drawGameFrame(LevelManager.row);


                    LevelManager.level = new Scene(LevelManager.map,1200, 600);
                    primaryStage.setScene(LevelManager.level);
                }
                catch (IOException e){
                    e.printStackTrace();
                }

                run();
            }
        };

        Thread t = new Thread(r);
        t.start();
        t.run();
    }

    private static void sendMapToClient(OutputStream out, char[][] map) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(map);
    }
}
