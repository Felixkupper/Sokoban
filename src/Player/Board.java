package Player;

import drawMain.Display;

import java.util.Scanner;

public class Board {
	private Display d;
	private Player p;
	private Scanner input;
	char[][] row;
	
	public Board(String[] str, Scanner putin) {
		//General inserting of maps
		
		int longest = 0;
		
		for(int i = 0; i < str.length - 1; i++) {
			if(str[i + 1].length() > str[i].length()) {
				longest = i + 1;
			}
		}
		
		row = new char[str.length][str[longest].length()];
		
		//String Array Map to CharArray Map
		
		for(int i = 0; i < row.length; i++) {
			row[i] = str[i].toCharArray();
		}
		
		//
		input = putin;
		d = new Display(row);
		p = new Player();	
	}
	
	public boolean game() {
		boolean punkt = true;
		
		d.display();
		
		row = p.inputLogic(row, input);
		
		//Checking if the player won
		
		for(int i = 0; i < row.length; i++) {
			for(int j = 0; j < row[i].length; j++) {
				if(row[i][j] == '.' || row[i][j] == '2') {
					punkt = false;
				}
			}
		}
		
		//Winner Screen
		
		if(punkt) {
			d.display();
			System.out.println("YOU WON...but you are not allowed to play again ;)");
		}
		
		return punkt;
	}
}