package Gui;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LevelManager {

	private static File file;
	public static FlowPane map = new FlowPane();
    public static char[][] row;
    public static Scene level;
    public static Player firstPlayer;
    public static Player secondPlayer;

    public static Scene startLevel (int index) {

        //Backround schwarz machen
        map.setStyle("-fx-background-color: #000000;");

        //ruft aus dem Index die Map aus
        String[] MapLoader;

        if (index == 0){

            file = new File("src\\rsc\\map.txt");
            MapLoader = fileReader(file);
        }
        else if (index == 1){

            file = new File("src\\rsc\\map2.txt");
            MapLoader = fileReader(file);
        }
        else if (index == 2){

            file = new File("src\\rsc\\pacmap.txt");
            MapLoader = fileReader(file);
        }
        else if (index == 3){

            MapLoader = new String[12];
            MapLoader[0] = "#########################";
            MapLoader[1] = "#       1             2 #";
            MapLoader[2] = "#       #   $           ##";
            MapLoader[3] = "#  ###########  #    $   #";
            MapLoader[4] = "##      #       ##    ####";
            MapLoader[5] = " #      #        ######";
            MapLoader[6] = " #      ##         #";
            MapLoader[7] = " ##                #";
            MapLoader[8] = "  ## ##### #####  ##";
            MapLoader[9] = "   ##             .#";
            MapLoader[10]= "   #.        #######";
            MapLoader[11]= "   ###########";
        }
        else {
            MapLoader = new String[1];
            MapLoader[0] = "";
        }

        // 1280 x 720
        // 60 x 30
        // Ein Feld 20x20px
        // 60 x 20 = 1200
        // 30 x 20 = 600
        // 1200 x 600

        // Algoritmus
        // 2x For Schleifen
        // 1. Schleife geht Jede Zeile durch
        // 2. Schleife fragt jeden Buchstaben der aktuellen Zeile ab
        // Block-Abfrage
        // Dementsprechend wird eine Textur platziert



        firstPlayer = new Player('1');
        secondPlayer = new Player('2');

        int longest = 0;

        for(int i = 0; i < MapLoader.length - 1; i++) {
            if(MapLoader[i + 1].length() > MapLoader[i].length()) {
                longest = i + 1;
            }
        }
        row = new char[MapLoader.length][MapLoader[longest].length()];

        //String Array Map to CharArray Map

        for(int i = 0; i < row.length; i++) {
            row[i] = MapLoader[i].toCharArray();
        }

        map = Display.drawGameFrame(row);

        level = new Scene(map,1200, 600);

        return level;
    }



    //file reader erstellen um Maps einzulesen
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
