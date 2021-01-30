package Sokoban;

import Gui.ButtonNew;
import Gui.GuiSokobanServer;
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
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main extends Application {

	public static String ClientIP = "localhost";
	public static int port = 6000;

	public static Button[] buttonScene = new Button[2];

	public void Inil() {
		buttonScene[0] = ButtonNew.generateButton(75, 20);
		buttonScene[0].setText("Client");

		buttonScene[1] = ButtonNew.generateButton(75, 20);
		buttonScene[1].setText("Host");
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("192.168.0.106");
		Inil();
		HBox rootIp = new HBox(5);
		rootIp.setAlignment(Pos.CENTER);

		Label Ip = new Label("IP: ");
		Ip.setTextFill(Color.WHITE);
		TextField TextIP = new TextField();

		rootIp.getChildren().addAll(Ip, TextIP);

		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		root.getChildren().addAll(rootIp, buttonScene[0], buttonScene[1]);

		Scene sceneServer = new Scene(root, 1200, 600);
		root.setBackground(Background.EMPTY);
		root.setStyle("-fx-background-color: #000000;");
		primaryStage.setScene(sceneServer);
		primaryStage.show();

		buttonScene[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					ClientIP = TextIP.getText();

					primaryStage.setScene(ClientScene.getClientScene(primaryStage));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		buttonScene[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					primaryStage.setScene(ServerScene.getServerScene(primaryStage));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
