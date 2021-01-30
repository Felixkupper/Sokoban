package Sokoban;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Gui.Display;
import Gui.LevelManager;

public class ClientScene {

	private static PrintWriter prW;
	private static Socket s;
	private static Scene clientScene;
	private static FlowPane gmap;
	private static char[][] cmap;
	private static InputStream is;

	public static void getNextKey(Stage primaryStage, KeyEvent event) {
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

	public static Scene getClientScene(Stage primaryStage) throws IOException, ClassNotFoundException {

		s = new Socket(Main.ClientIP, Main.port);

		prW = new PrintWriter(s.getOutputStream());
		is = s.getInputStream();

		gmap = new FlowPane();

		cmap = readBoard(is);
		gmap = Display.drawGameFrame(cmap);

		clientScene = new Scene(gmap, 1200, 600);
		primaryStage.setTitle(Main.ClientIP);
		Thread t = new Thread(new Runnable() {
			public void run() {

				try {
					cmap = readBoard(is);
					System.out.println("Board wurde eingelesen");
					gmap = Display.drawGameFrame(cmap);
					System.out.println("FlowPane aktualisiert");
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							clientScene = new Scene(gmap, 1200, 600);
							clientScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
								@Override
								public void handle(KeyEvent event) {
									getNextKey(primaryStage, event);
								}
							});
							try {
								primaryStage.setScene(clientScene);
							} catch (Exception e) {
								// TODO: handle exception
							}

						}
					});

				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		t.start();

		clientScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				getNextKey(primaryStage, event);

			}
		});

		primaryStage.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			prW.close();
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return clientScene;
	}

	private static char[][] readBoard(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(in);
		System.out.println("MapArray recieved");
		return (char[][]) ois.readObject();
	}
}
