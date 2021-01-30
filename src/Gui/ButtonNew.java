package Gui;

import javafx.scene.control.Button;

public class ButtonNew {

	public static Button generateButton(int width, int height) {
		Button btn = new Button();
		btn.setMinWidth(width);
		btn.setMinHeight(height);

		return btn;
	}

}
