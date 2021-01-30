package Gui;

import javafx.scene.image.Image;

import java.io.File;

public class ImageLoader {

	public static Image iwall = new Image(new File("src\\rsc\\brick.png").toURI().toString(), 20, 20, false, false);
	public static Image Placeholder = new Image(new File("src\\rsc\\Placeholder.png").toURI().toString(), 20, 20, false,
			false);
	public static Image iplayer = new Image(new File("src\\rsc\\player.png").toURI().toString(), 20, 20, false, false);
	public static Image iblock = new Image(new File("src\\rsc\\block.png").toURI().toString(), 20, 20, false, false);
	public static Image igoal = new Image(new File("src\\rsc\\fire.gif").toURI().toString(), 20, 20, false, false);
	public static Image ifinish = new Image(new File("src\\rsc\\finish.png").toURI().toString(), 20, 20, false, false);
	public static Image iplayer2 = new Image(new File("src\\rsc\\player 2.png").toURI().toString(), 20, 20, false,
			false);

}
