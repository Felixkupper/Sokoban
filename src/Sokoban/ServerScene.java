package Sokoban;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Gui.ButtonNew;
import Gui.Display;
import Gui.LevelManager;

public class ServerScene {

	private static ServerSocket ss;
	private static Socket s;
	private static InputStreamReader in;
	private static BufferedReader bf;
	private static OutputStream out;

	private static Button[] button = new Button[4];

	// neue Button erstellen
	private static void Inil() {
		button[0] = ButtonNew.generateButton(300, 50);
		button[0].setText("1: Kuntzes Kaputte Karte / Flavios Fehlerhaftes Feld");

		button[1] = ButtonNew.generateButton(300, 50);
		button[1].setText("2: Tims Tolles Terrain");

		button[2] = ButtonNew.generateButton(300, 50);
		button[2].setText("3: Zachs Pacmap");

		button[3] = ButtonNew.generateButton(300, 50);
		button[3].setText("4: Map 1 but it works ;)");
	}

	// Map wird nach Move immer neu geladen
	private static void getNextKey(Stage primaryStage, KeyEvent event) throws IOException {
		String pressedKey = event.getCode().getName();
		LevelManager.row = LevelManager.firstPlayer.inputLogic(LevelManager.row, pressedKey);

		sendMapToClient(out, LevelManager.row);
		LevelManager.map = Display.drawGameFrame(LevelManager.row);

		LevelManager.level = new Scene(LevelManager.map, 1200, 600);
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

	@SuppressWarnings("deprecation")
	public static Scene getServerScene(Stage primaryStage) throws IOException {
		Scene scene;

		ss = new ServerSocket(Main.port);
		s = ss.accept();

		System.out.println("client connected");

		in = new InputStreamReader(s.getInputStream());
		bf = new BufferedReader(in);

		out = s.getOutputStream();

		Inil();

		button[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setScene(LevelManager.startLevel(0));
				try {
					sendMapToClient(out, LevelManager.row);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				try {
					sendMapToClient(out, LevelManager.row);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				try {
					sendMapToClient(out, LevelManager.row);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				try {
					sendMapToClient(out, LevelManager.row);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

		// Neues Fenster in der Scene erstllen fÃ¼r Buttons
		VBox root = new VBox();

		root.setBackground(Background.EMPTY);
		root.setStyle("-fx-background-color: #000000;");

		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		root.getChildren().addAll(button[0], button[1], button[2], button[3]);

		scene = new Scene(root, 1200, 600);

		Thread t = new Thread(new Runnable() {

			public void run() {
				System.out.println("Thread gestartet");
				String key = " ";
				try {
					key = bf.readLine();
					System.out.println("Key eingelesen");
					LevelManager.row = LevelManager.secondPlayer.inputLogic(LevelManager.row, key);
					System.out.println("Map Aktualisiert");
					try {
						sendMapToClient(out, LevelManager.row);
						System.out.println("Map zurück an Client geschickt");
					} catch (IOException e) {
						System.out.println("Fehler beim senden der Map an Client");
						e.printStackTrace();
					}
					LevelManager.map = Display.drawGameFrame(LevelManager.row);
					System.out.println("FlowPane im Levelmanager aktualisiert");

					LevelManager.level = new Scene(LevelManager.map, 1200, 600);
					System.out.println("Scene im Levelmanager aktualisiert");

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

					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							primaryStage.setScene(LevelManager.level);
						}
					});

					// System.out.println("Neue scene in der Primarystage gesetzt");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Key wurde nicht eingelesen");
				}
				run();
			}

		});

		t.start();

		primaryStage.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return scene;
	}

	private static void sendMapToClient(OutputStream out, char[][] map) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(map);
	}
}
