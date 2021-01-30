package Sokoban;

public class Player {

	private char pChar;
	private char groundChar = ' ';

	public Player(char playerChar) {
		pChar = playerChar;
	}

	public char[][] inputLogic(char[][] cmap, String input) {
		// Initializing variables

		char[][] pmap = cmap;
		int x = 0;
		int y = 0;
		int yMov = 0;
		int xMov = 0;
		// Locating the player

		for (int i = 0; i < pmap.length; i++) {
			for (int j = 0; j < pmap[i].length; j++) {
				if (pmap[i][j] == pChar) {
					x = j;
					y = i;
				}
			}
		}

		// Handling inputlogic for wasd
		switch (input.toLowerCase()) {

		case "w":
			yMov = yMov - 1;

			break;
		case "a":
			xMov = xMov - 1;

			break;
		case "s":
			yMov = yMov + 1;

			break;
		case "d":
			xMov = xMov + 1;

			break;

		}

		if ((y + yMov) < pmap.length && (y + yMov) >= 0) {
			if ((x + xMov) < pmap[y + yMov].length && (x + xMov) >= 0) {
				if (pmap[y + yMov][x + xMov] == '#') {

				} else if (pmap[y + yMov][x + xMov] == ' ') {
					pmap[y][x] = groundChar;
					pmap[y + yMov][x + xMov] = pChar;
					groundChar = ' ';
				} else if (pmap[y + yMov][x + xMov] == '.') {
					pmap[y][x] = groundChar;
					pmap[y + yMov][x + xMov] = pChar;
					groundChar = '.';
				} else if (pmap[y + yMov][x + xMov] == '$') {
					if (pmap[y + yMov * 2][x + xMov * 2] == ' ') {
						pmap[y + yMov * 2][x + xMov * 2] = '$';
						pmap[y][x] = groundChar;
						pmap[y + yMov][x + xMov] = pChar;
						groundChar = ' ';
					} else if (pmap[y + yMov * 2][x + xMov * 2] == '.') {
						pmap[y + yMov * 2][x + xMov * 2] = '@';
						pmap[y][x] = groundChar;
						pmap[y + yMov][x + xMov] = pChar;
						groundChar = ' ';
					}
				} else if (pmap[y + yMov][x + xMov] == '@') {
					if (pmap[y + yMov * 2][x + xMov * 2] == ' ') {
						pmap[y + yMov * 2][x + xMov * 2] = '$';
						pmap[y][x] = groundChar;
						pmap[y + yMov][x + xMov] = pChar;
						groundChar = '.';
					} else if (pmap[y + yMov * 2][x + xMov * 2] == '.') {
						pmap[y + yMov * 2][x + xMov * 2] = '@';
						pmap[y][x] = groundChar;
						pmap[y + yMov][x + xMov] = pChar;
						groundChar = '.';
					}
				}
			}
		}

		return pmap;
	}
}