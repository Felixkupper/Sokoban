package Player;

import java.util.Scanner;

public class Player {
	public char[][] inputLogic(char[][] cmap, Scanner input) {
		//Initializing variables
		
		char[][] pmap = cmap;
		int x = 0;
		int y = 0;
		
		//Locating the player
		
		for(int i = 0; i < pmap.length; i++) {
			for(int j = 0; j < pmap[i].length; j++) {
				if(pmap[i][j] == '1' || pmap[i][j] == '2') {
					x = j;
					y = i;
				}
			}
		}
		
		//Handling inputlogic for wasd
		
		String action = input.next();
		
		switch(action.toLowerCase()) {
		
		case "w": 
			
			if(pmap[y][x] == '2'){ //wenn 2
				if(pmap[y-1][x] == '#'){
					
				}else if(pmap[y-1][x] == ' ') {
					pmap[y-1][x] = '1';
					pmap[y][x]   = '.';
				}else if(pmap[y-1][x] == '.'){
					pmap[y-1][x] = '2';
					pmap[y][x]   = '.';
				}else if(pmap[y-1][x] == '$' && pmap[y-2][x] == ' '){
					pmap[y-1][x] = '1';
					pmap[y-2][x] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y-1][x] == '$' && pmap[y-2][x] == '#'){
					
				}else if(pmap[y-1][x] == '$' && pmap[y-2][x] == '.'){
					pmap[y-1][x] = '1';
					pmap[y-2][x] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y-1][x] == '$' && pmap[y-2][x] == '.'){
					pmap[y-1][x] = '1';
					pmap[y-2][x] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y-1][x] == '@' && pmap[y-2][x] == '.'){
					pmap[y-1][x] = '2';
					pmap[y-2][x] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y-1][x] == '@' && pmap[y-2][x] == ' '){
					pmap[y-1][x] = '2';
					pmap[y-2][x] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y-1][x] == '@' && pmap[y-2][x] == '#'){
					
				}
			}if(pmap[y-1][x] == '#'){ 
				
			}else if(pmap[y-1][x] == '.'){
				pmap[y-1][x] = '2';
				pmap[y][x]   = ' ';
			}else if(pmap[y-1][x] == ' '){
				pmap[y-1][x] = '1';
				pmap[y][x]   = ' ';
			}else if(pmap[y-1][x] == '$' && pmap[y-2][x] == ' '){
				pmap[y-1][x] = '1';
				pmap[y-2][x] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y-1][x] == '$' && pmap[y-2][x] == '#'){
				
			}else if(pmap[y-1][x] == '$' && pmap[y-2][x] == '.'){
				pmap[y-1][x] = '1';
				pmap[y-2][x] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y-1][x] == '@' && pmap[y-2][x] == '.'){
				pmap[y-1][x] = '2';
				pmap[y-2][x] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y-1][x] == '@' && pmap[y-2][x] == ' '){
				pmap[y-1][x] = '2';
				pmap[y-2][x] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y-1][x] == '@' && pmap[y-2][x] == '#'){
				
			}
			break;
		case "a":
			
			if(pmap[y][x] == '2'){ //wenn 2
				if(pmap[y][x-1] == '#'){
					
				}else if(pmap[y][x-1] == ' ') {
					pmap[y][x-1] = '1';
					pmap[y][x]   = '.';
				}else if(pmap[y][x-1] == '.'){
					pmap[y][x-1] = '2';
					pmap[y][x]   = '.';
				}else if(pmap[y][x-1] == '$' && pmap[y][x-2] == ' '){
					pmap[y][x-1] = '1';
					pmap[y][x-2] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y][x-1] == '$' && pmap[y][x-2] == '#'){
					
				}else if(pmap[y][x-1] == '$' && pmap[y][x-2] == '.'){
					pmap[y][x-1] = '1';
					pmap[y][x-2] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y][x-1] == '$' && pmap[y][x-2] == '.'){
					pmap[y][x-1] = '1';
					pmap[y][x-2] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y][x-1] == '@' && pmap[y][x-2] == '.'){
					pmap[y][x-1] = '2';
					pmap[y][x-2] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y][x-1] == '@' && pmap[y][x-2] == ' '){
					pmap[y][x-1] = '2';
					pmap[y][x-2] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y][x-1] == '@' && pmap[y][x-2] == '#'){
					
				}
			}if(pmap[y][x-1] == '#'){
				
			}else if(pmap[y][x-1] == '.'){
				pmap[y][x-1] = '2';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x-1] == ' '){
				pmap[y][x-1] = '1';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x-1] == '$' && pmap[y][x-2] == ' '){
				pmap[y][x-1] = '1';
				pmap[y][x-2] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x-1] == '$' && pmap[y][x-2] == '#'){
				
			}else if(pmap[y][x-1] == '$' && pmap[y][x-2] == '.'){
				pmap[y][x-1] = '1';
				pmap[y][x-2] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x-1] == '@' && pmap[y][x-2] == '.'){
				pmap[y][x-1] = '2';
				pmap[y][x-2] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x-1] == '@' && pmap[y][x-2] == ' '){
				pmap[y][x-1] = '2';
				pmap[y][x-2] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x-1] == '@' && pmap[y][x-2] == '#'){
				
			}
			break;
		case "s":
			
			if(pmap[y][x] == '2'){ //wenn 2
				if(pmap[y+1][x] == '#'){
					
				}else if(pmap[y+1][x] == ' ') {
					pmap[y+1][x] = '1';
					pmap[y][x]   = '.';
				}else if(pmap[y+1][x] == '.'){
					pmap[y+1][x] = '2';
					pmap[y][x]   = '.';
				}else if(pmap[y+1][x] == '$' && pmap[y+2][x] == ' '){
					pmap[y+1][x] = '1';
					pmap[y+2][x] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y+1][x] == '$' && pmap[y+2][x] == '#'){
					
				}else if(pmap[y+1][x] == '$' && pmap[y+2][x] == '.'){
					pmap[y+1][x] = '1';
					pmap[y+2][x] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y+1][x] == '$' && pmap[y+2][x] == '.'){
					pmap[y+1][x] = '1';
					pmap[y+2][x] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y+1][x] == '@' && pmap[y+2][x] == '.'){
					pmap[y+1][x] = '2';
					pmap[y+2][x] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y+1][x] == '@' && pmap[y+2][x] == ' '){
					pmap[y+1][x] = '2';
					pmap[y+2][x] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y+1][x] == '@' && pmap[y+2][x] == '#'){
					
				}
			}if(pmap[y+1][x] == '#'){
				
			}else if(pmap[y+1][x] == '.'){
				pmap[y+1][x] = '2';
				pmap[y][x]   = ' ';
			}else if(pmap[y+1][x] == ' '){
				pmap[y+1][x] = '1';
				pmap[y][x]   = ' ';
			}else if(pmap[y+1][x] == '$' && pmap[y+2][x] == ' '){
				pmap[y+1][x] = '1';
				pmap[y+2][x] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y+1][x] == '$' && pmap[y+2][x] == '#'){
				
			}else if(pmap[y+1][x] == '$' && pmap[y+2][x] == '.'){
				pmap[y+1][x] = '1';
				pmap[y+2][x] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y+1][x] == '@' && pmap[y+2][x] == '.'){
				pmap[y+1][x] = '2';
				pmap[y+2][x] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y+1][x] == '@' && pmap[y+2][x] == ' '){
				pmap[y+1][x] = '2';
				pmap[y+2][x] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y+1][x] == '@' && pmap[y+2][x] == '#'){
				
			}
			
			break;
		case "d": 
			
			if(pmap[y][x] == '2'){ //wenn 2
				if(pmap[y][x+1] == '#'){
					
				}else if(pmap[y][x+1] == ' ') {
					pmap[y][x+1] = '1';
					pmap[y][x]   = '.';
				}else if(pmap[y][x+1] == '.'){
					pmap[y][x+1] = '2';
					pmap[y][x]   = '.';
				}else if(pmap[y][x+1] == '$' && pmap[y][x+2] == ' '){
					pmap[y][x+1] = '1';
					pmap[y][x+2] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y][x+1] == '$' && pmap[y][x+2] == '#'){
					
				}else if(pmap[y][x+1] == '$' && pmap[y][x+2] == '.'){
					pmap[y][x+1] = '1';
					pmap[y][x+2] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y][x+1] == '$' && pmap[y][x+2] == '.'){
					pmap[y][x+1] = '1';
					pmap[y][x+2] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y][x+1] == '@' && pmap[y][x+2] == '.'){
					pmap[y][x+1] = '2';
					pmap[y][x+2] = '@';
					pmap[y][x]   = '.';
				}else if(pmap[y][x+1] == '@' && pmap[y][x+2] == ' '){
					pmap[y][x+1] = '2';
					pmap[y][x+2] = '$';
					pmap[y][x]   = '.';
				}else if(pmap[y][x+1] == '@' && pmap[y][x+2] == '#'){
					
				}
			}if(pmap[y][x+1] == '#'){
				
			}else if(pmap[y][x+1] == '.'){
				pmap[y][x+1] = '2';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x+1] == ' '){
				pmap[y][x+1] = '1';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x+1] == '$' && pmap[y][x+2] == ' '){
				pmap[y][x+1] = '1';
				pmap[y][x+2] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x+1] == '$' && pmap[y][x+2] == '#'){
				
			}else if(pmap[y][x+1] == '$' && pmap[y][x+2] == '.'){
				pmap[y][x+1] = '1';
				pmap[y][x+2] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x+1] == '@' && pmap[y][x+2] == '.'){
				pmap[y][x+1] = '2';
				pmap[y][x+2] = '@';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x+1] == '@' && pmap[y][x+2] == ' '){
				pmap[y][x+1] = '2';
				pmap[y][x+2] = '$';
				pmap[y][x]   = ' ';
			}else if(pmap[y][x+1] == '@' && pmap[y][x+2] == '#'){
				
			}
			break;
		
		}
		
		return pmap;
	}
}