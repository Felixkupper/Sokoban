package Main;


import Player.Board;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class sookooban {
	static private String[] map;
	static private File file;
	
	public static void main(String[] args) {
		/*
		map[0] = "#########################";
		map[1] = "#       1               #";
		map[2] = "#       #   $           ##";
		map[3] = "#  ###########  #    $   #";
		map[4] = "##      #       ##    ####";
		map[5] = " #      #        ######";
		map[6] = " #      ##         #";
		map[7] = " ##                #";
		map[8] = "  ## ##### #####  ##";
		map[9] = "   ##             .#";
		map[10]= "   #.        #######";
		map[11]= "   ###########";
		*/
		Scanner input = new Scanner(System.in);
		
		//Intro
		
		System.out.println("Which map would you like to play?");
		System.out.println("1: Kuntzes Kaputte Karte / Flavios Fehlerhaftes Feld");
		System.out.println("2: Tims Tolles Terrain");
		System.out.println("3: Zachs Pacmap");
		System.out.println("4: Map 1 but it works ;)");

		boolean isCorrect = true;;
		
		while(isCorrect) {

			int mapname = input.nextInt();

			switch(mapname) {
			case 1:
				file = new File("src\\map.txt");
				map = fileReader(file);
				isCorrect = false;
			break;
			case 2:
				file = new File("src\\map2.txt");
				map = fileReader(file);
				isCorrect = false;
			break;
			case 3:
				file = new File("src\\pacmap.txt");
				map = fileReader(file);
				isCorrect = false;
			break;
			case 4:
				map = new String[12];
				map[0] = "#########################";
				map[1] = "#       1               #";
				map[2] = "#       #   $           ##";
				map[3] = "#  ###########  #    $   #";
				map[4] = "##      #       ##    ####";
				map[5] = " #      #        ######";
				map[6] = " #      ##         #";
				map[7] = " ##                #";
				map[8] = "  ## ##### #####  ##";
				map[9] = "   ##             .#";
				map[10]= "   #.        #######";
				map[11]= "   ###########";
				isCorrect = false;
			break;
			default: System.out.println("Please try again, idiot :)");
					 isCorrect = true;
			break;
			}
		}
		
		//Game
		
		Board b = new Board(map, input);
		boolean spielende;
		
		do {
			spielende = b.game();
		}while(!spielende);
		
		
	}
	
	public static String[] fileReader(File f) {
		int temp = 0;
		String[] m = null;
		String line;
		
		try {
			//Counting the lines of the text file
			
            Scanner scanner = new Scanner(f);
            
            while (scanner.hasNextLine()) {
                temp++;
                line = scanner.nextLine();
            }
            scanner.close();
            
            //Inserting the lines into the String array
            
            m = new String[temp];
            scanner = new Scanner(f);
            
            for(int i = 0; i < temp; i++) {
            	line = scanner.nextLine();
            	m[i] = line;
            	System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		return m;
	}
}