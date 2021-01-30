package Gui;

import Server.GuiClient;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;


public class ServerScene extends Application {

    public static String ClientIP = "localhost";
    public static int port = 5000;

    public static Button[] buttonScene = new Button [2];



    public void Inil() {
        buttonScene[0] = ButtonNew.generateButton(75, 20);
        buttonScene[0].setText("Client");

        buttonScene[1] = ButtonNew.generateButton(75, 20);
        buttonScene[1].setText("Host");


    }

    @Override
    public void start(Stage primaryStage){
        Inil();
        HBox rootIp = new HBox(5);
        rootIp.setAlignment(Pos.CENTER);

        Label Ip = new Label("IP: ");
        Ip.setTextFill(Color.WHITE);
        TextField TextIP = new TextField();

        rootIp.getChildren().addAll(Ip,TextIP);
        //Button zuweisen
        buttonScene[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    primaryStage.setScene(GuiClient.getClientScene(primaryStage, TextIP.getText()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        buttonScene[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    GuiSokobanServer.startServerScene(primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.getChildren().addAll(rootIp, buttonScene[0], buttonScene[1]);

        Scene sceneServer = new Scene(root, 1200, 600);
        root.setBackground(Background.EMPTY);
        root.setStyle("-fx-background-color: #000000;");
        primaryStage.setScene(sceneServer);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
