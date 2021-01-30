package drawMain;



public class Display {
	private char[][] cmap;
	
	public Display(char[][] c) {
		cmap = c;
	}
	
	public void display() {
		//Handling what you see on the screen
		
		for (int i = 0; i < 50; ++i) System.out.println();
		
		for(int i = 0; i < cmap.length; i++) {
			for(int j = 0; j < cmap[i].length; j++) {
				System.out.print(cmap[i][j]);
			}
			System.out.print("\n");
		}
	}
}