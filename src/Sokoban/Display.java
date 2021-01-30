package Sokoban;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class Display {

	public static FlowPane drawGameFrame(char[][] MapLoader) {

		FlowPane map = new FlowPane();
		map.setStyle("-fx-background-color: #000000;");

		for (int i = 0; i < MapLoader.length; i++) {
			for (int j = 0; j <= MapLoader[i].length; j++) {

				if (j < MapLoader[i].length) {
					if (MapLoader[i][j] == '#') {
						map.getChildren().add(new ImageView(ImageLoader.iwall));
					} else if (MapLoader[i][j] == ' ') {
						map.getChildren().add(new ImageView(ImageLoader.Placeholder));
					} else if (MapLoader[i][j] == '1') {
						map.getChildren().add(new ImageView(ImageLoader.iplayer));
					} else if (MapLoader[i][j] == '$') {
						map.getChildren().add(new ImageView(ImageLoader.iblock));
					} else if (MapLoader[i][j] == '.') {
						map.getChildren().add(new ImageView(ImageLoader.igoal));
					} else if (MapLoader[i][j] == '@') {
						map.getChildren().add(new ImageView(ImageLoader.ifinish));
					} else if (MapLoader[i][j] == '2') {
						map.getChildren().add(new ImageView(ImageLoader.iplayer2));
					}
				} else {
					int anzahlPlatzhalter = 60 - MapLoader[i].length;
					for (int a = 0; a < anzahlPlatzhalter; a++) {
						map.getChildren().add(new ImageView(ImageLoader.Placeholder));
					}
				}
			}
		}
		return map;
	}

}
