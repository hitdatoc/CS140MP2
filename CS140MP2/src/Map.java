import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import java.util.*;

public class Map {
	
	float mapRenderX;
	float mapRenderY;
	
	int map;
	
	public String mapURLBG;
	public String mapURLFG;
	public String mapMusic;
	
	int mapGrid[][]; // mapGrid[row][column]
	int gridLength;
	int gridHeight;
	
	int mapLength;
	int mapHeight;
	boolean mapHorizontal;
	boolean mapVertical;
	
	ArrayList<gameObject> objectList;
	
	public Map(){
		this.mapRenderX = 0;
		this.mapRenderY = 0;
		
		this.mapLength = 0;
		this.mapHeight = 0;
		
		this.mapHorizontal = true;
		this.mapVertical = true;
		
		objectList = new ArrayList<gameObject>();
		
		this.map = 0;
		setMap();
	}
	
	public Map(int map){
		this.mapRenderX = 0;
		this.mapRenderY = 0;
		
		this.mapLength = 0;
		this.mapHeight = 0;
		
		this.mapHorizontal = true;
		this.mapVertical = true;
		
		objectList = new ArrayList<gameObject>();
		
		this.map = map;
		setMap();	
	}
	
	public float getMPX(){
		return this.mapRenderX;
	}
	
	public float getMPY(){
		return this.mapRenderY;
	}
	
	public void setMPX(float mapRenderX){
		this.mapRenderX = mapRenderX;
	}
	
	public void setMPY(float mapRenderY){
		this.mapRenderY = mapRenderY;
	}
	
	public int getThisMap(){
		return this.map;
	}
	
	public void setThisMap(int map){
		this.map = map;
	}
	
	public boolean getMapHorizontal(){
		if(mapHorizontal){
			
		}
		
		return false;
	}
	
	public boolean getMapVertical(int direction){
		if(mapVertical){
			if(this.map == 0 && mapRenderY <= 0 && direction == 1){
				return true;
			} else if(this.map == 0 && mapRenderY >= -185 && direction == 2){
				return true;
			}
		}
		
		return false;
	}
	
	public void setMap(){
		if(this.map == 0){
			mapRenderX = 40;
			mapRenderY = 0;
			
			mapLength = 550;
			mapHeight = 185;
			
			mapHorizontal = false;
			mapVertical = true;
			
			mapGrid = new int[21][17];
			gridLength = 17;
			gridHeight = 21;
			initGrid(gridHeight,gridLength);
			setMap0();
			setObject0();
			
			mapURLBG = "/Users/Hillary/GameDev/CS140MP2/src/images/rabforest1.png";
			mapURLFG = "/Users/Hillary/GameDev/CS140MP2/src/images/rabiteforestFG.png";
			mapMusic = "/Users/Hillary/GameDev/CS140MP2/src/music/2-08-powell.ogg";
		} else if(this.map == 1){
			mapRenderX = 0;
			mapRenderY = 0;
			
			mapGrid = new int[100][100];
			gridLength = 100;
			gridHeight = 100;
			initGrid(gridHeight,gridLength);
			
			mapURLBG = "/Users/Hillary/GameDev/CS140MP2/src/images/navarrefortress.png";
			mapURLFG = "/Users/Hillary/GameDev/CS140MP2/src/images/rabiteforestFG.png";
			mapMusic = "/Users/Hillary/GameDev/CS140MP2/src/music/2-08-powell.ogg";
		}
		
		
	}
	
	public String collideObject(float posX, float posY, int direction){
		posX = posX + 16f;
		posY = posY + 48f;
		
		if(direction == 1){
			//UP
			posY = posY - 32;
		} else if(direction == 2){
			//DOWN
			posY = posY + 32;
		} else if(direction == 3){
			//LEFT
			posX = posX - 32;
		} else if(direction == 4){
			//RIGHT
			posX = posX + 32;
		}
		
		int posIX = (int)posX;
		int posIY = (int)posY;
		gameObject tempo;
		
		for(int i = 0; i < objectList.size(); i++){
			tempo = objectList.get(i);
			if(tempo.getRectangle().contains(posIX, posIY)){
				return tempo.getMessage();
			}
		}
		
		return "";
	}
	
	public boolean collide(float posX, float posY, int direction){
		posX = posX + 16f;
		posY = posY + 48f;
		
		if(direction == 1){
			//UP
			posY = posY - 32;
		} else if(direction == 2){
			//DOWN
			posY = posY + 32;
		} else if(direction == 3){
			//LEFT
			posX = posX - 32;
		} else if(direction == 4){
			//RIGHT
			posX = posX + 32;
		}
		
		int i;
		int start = (int)(mapRenderX);
		for(i = 0; i < gridLength; i++){
			if((int)(posX) >= start && (int)(posX) <= start + 32){
				break;
			}
			start = start + 32;
		}
				
		int j;
		start = (int)(mapRenderY);
		for(j = 0; j < gridHeight; j++){
		if((int)(posY) >= start && (int)(posY) <= start + 32){
			break;
		}
			start = start + 32;
		}
		
		if(j >= gridHeight || i >= gridLength){
			return false;
		} 
		
		if(j >= 0 && j < gridHeight && i >= 0 && i < gridLength){		
			if(mapGrid[j][i] == 1){
				return true;
			}
		}
		
		return false;
	}
	
	public String collideString(float posX, float posY){
		posX = posX + 16f;
		posY = posY + 48f;
		
		String output = "";
		int i;
		int start = (int)(mapRenderX);
		for(i = 0; i < gridLength; i++){
			if((int)(posX) >= start && (int)(posX) <= start + 32){
				break;
			}
			start = start + 32;
		}
				
		int j;
		start = (int)(mapRenderY);
		for(j = 0; j < gridHeight; j++){
		if((int)(posY) >= start && (int)(posY) <= start + 32){
			break;
		}
			start = start + 32;
		}
		
		if(j >= gridHeight || i >= gridLength){
			output = output + "OUT OF BOUNDS -- " + Integer.toString(j) + " " + Integer.toString(i);
			return output;
		} 
		
		//System.out.println(Integer.toString(i) + " " + Integer.toString(j));
		if(j >= 0 && j < gridHeight && i >= 0 && i < gridLength){
			output = output + "IN ";
			
			if(mapGrid[j][i] == 1){
				output = output + "TRUE";
			}
		}
		
		output = output + " " + " x = " + Integer.toString(i) + " y = " + Integer.toString(j);
		return output;
	}
	
	public void initGrid(int x, int y){
		for(int i = 0; i < x; i++){
			if(i == 0 || i == x-1){
				for(int j = 0; j < y; j++){
					mapGrid[i][j] = 1;
				}
			} else {
				for(int j = 0; j < y; j++){
					if(j == 0 || j == y-1){
						mapGrid[i][j] = 1;
					} else {
						mapGrid[i][j] = 0;
					}
				}
			}
		}
		
	}
	
	void setMap0(){
		//MANUAL BLOCKING
		//----------------------------
		//ROW 1
		//----------------------------
		for(int i = 0; i < 11; i++){
			mapGrid[1][i] = 1;
		}
		
		for(int i = 14; i < 17; i++){
			mapGrid[1][i] = 1;
		}
		//----------------------------
		//ROW 2
		//----------------------------
		for(int i = 0; i < 10; i++){
			mapGrid[2][i] = 1;
		}
		
		for(int i = 14; i < 17; i++){
			mapGrid[2][i] = 1;
		}
		
		//----------------------------
		//ROW 3
		//----------------------------
		for(int i = 0; i < 3; i++){
			mapGrid[3][i] = 1;
		}
		
		for(int i = 14; i < 17; i++){
			mapGrid[3][i] = 1;
		}
		//----------------------------
		//ROW 4
		//----------------------------
		for(int i = 0; i < 2; i++){
			mapGrid[4][i] = 1;
		}
		
		for(int i = 14; i < 17; i++){
			mapGrid[4][i] = 1;
		}
		//----------------------------
		//ROW 5
		//----------------------------
		for(int i = 0; i < 2; i++){
			mapGrid[5][i] = 1;
		}
		//----------------------------
		//ROW 6
		//----------------------------
		for(int i = 0; i < 2; i++){
			mapGrid[6][i] = 1;
		}
		
		for(int i = 8; i < 11; i++){
			mapGrid[6][i] = 1;
		}
		//----------------------------
		//ROW 7
		//----------------------------
		for(int i = 0; i < 3; i++){
			mapGrid[7][i] = 1;
		}
		
		for(int i = 7; i < 12; i++){
			mapGrid[7][i] = 1;
		}
		//----------------------------
		//ROW 8
		//----------------------------
		for(int i = 0; i < 4; i++){
			mapGrid[8][i] = 1;
		}
		
		for(int i = 6; i < 13; i++){
			mapGrid[8][i] = 1;
		}
		//----------------------------
		//ROW 9
		//----------------------------
		for(int i = 0; i < 4; i++){
			mapGrid[9][i] = 1;
		}
		
		for(int i = 6; i < 13; i++){
			mapGrid[9][i] = 1;
		}
		//----------------------------
		//ROW 10
		//----------------------------
		for(int i = 0; i < 4; i++){
			mapGrid[10][i] = 1;
		}
		
		for(int i = 5; i < 13; i++){
			mapGrid[10][i] = 1;
		}
		//----------------------------
		//ROW 11
		//----------------------------
		for(int i = 0; i < 4; i++){
			mapGrid[11][i] = 1;
		}
		
		for(int i = 6; i < 13; i++){
			mapGrid[11][i] = 1;
		}
		//----------------------------
		//ROW 12
		//----------------------------
		for(int i = 0; i < 4; i++){
			mapGrid[12][i] = 1;
		}
		
		for(int i = 6; i < 11; i++){
			mapGrid[12][i] = 1;
		}
		//----------------------------
		//ROW 13
		//----------------------------
		for(int i = 0; i < 3; i++){
			mapGrid[13][i] = 1;
		}
		
		for(int i = 6; i < 9; i++){
			mapGrid[13][i] = 1;
		}
		//----------------------------
		//ROW 14
		//----------------------------
		for(int i = 0; i < 3; i++){
			mapGrid[14][i] = 1;
		}
		
		for(int i = 5; i < 8; i++){
			mapGrid[14][i] = 1;
		}
		//----------------------------
		//ROW 15
		//----------------------------
		for(int i = 5; i < 8; i++){
			mapGrid[15][i] = 1;
		}
		
		for(int i = 13; i < 17; i++){
			mapGrid[15][i] = 1;
		}
		//----------------------------
		//ROW 16
		//----------------------------
		for(int i = 13; i < 17; i++){
			mapGrid[16][i] = 1;
		}
		//----------------------------
		//ROW 17
		//----------------------------
		for(int i = 0; i < 2; i++){
			mapGrid[17][i] = 1;
		}
		
		for(int i = 13; i < 17; i++){
			mapGrid[17][i] = 1;
		}
		//----------------------------
		//ROW 18
		//----------------------------
		for(int i = 0; i < 2; i++){
			mapGrid[18][i] = 1;
		}
		
		for(int i = 12; i < 17; i++){
			mapGrid[18][i] = 1;
		}
		//----------------------------
		//ROW 19
		//----------------------------
		for(int i = 0; i < 2; i++){
			mapGrid[19][i] = 1;
		}
		
		for(int i = 6; i < 17; i++){
			mapGrid[19][i] = 1;
		}
		//----------------------------
		//ROW 20
		//----------------------------
		for(int i = 0; i < 2; i++){
			mapGrid[20][i] = 1;
		}
		
		for(int i = 5; i < 17; i++){
			mapGrid[20][i] = 1;
		}
		//----------------------------
	}

	void setObject0(){
		gameObject sign = new gameObject();
		sign.setMessage("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		
		objectList.add(sign);
	}
	
	public void drawObjects(Graphics g){
		try{
			gameObject tempo;
			for(int i = 0; i < objectList.size(); i++){
				tempo = objectList.get(i);
				g.drawImage(new Image(tempo.objectImage), tempo.getRX(), tempo.getRY());
			}
		}catch(Exception e){System.out.println(e);}
	}
	
	public void moveObjects(float x, float y){
		gameObject tempo;
		for(int i = 0; i < objectList.size(); i++){
			tempo = objectList.get(i);
			tempo.setRX(tempo.getRX() + x);
			tempo.setRY(tempo.getRY() + y);
			
		}
	}
}
