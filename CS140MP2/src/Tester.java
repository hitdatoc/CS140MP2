//VERSION BLEHBLEHBLEH
import java.awt.*;
import java.util.*;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class Tester extends BasicGame{
	Image mainMenuBG;
	Image castleBenj;
	
	Player player;
	Image girlStillUp;
	Image girlStillDown;
	Image girlStillLeft;
	Image girlStillRight;
	Image icon;
	Animation girlMoveDown;
	Animation girlMoveUp;
	Animation girlMoveLeft;
	Animation girlMoveRight;
	Animation girlAttack1;
	Animation girlAttack12;
	Animation girlAttack13;
	Animation girlAttack2;
	Animation girlAttack22;
	Animation girlAttack23;
	Animation girlAttack3;
	Animation girlAttack32;
	Animation girlAttack33;
	Animation girlAttack4;
	Animation girlAttack42;
	Animation girlAttack43;
	Animation casting;
	Animation endCast;
	Animation deadPlayer;
	
	//Animation heal;
	
	Animation fadeOut;
	
	Image mapBG;
	Image mapFG;
	
	String buttonPressed = "";
	boolean buttonIsPressed = false;
	boolean walkUp = false;
	boolean walkDown = false;
	boolean walkLeft = false;
	boolean walkRight = false;
	boolean quit = false;
	boolean zDown = false;
	boolean xDown = false;
	boolean cDown = false;
	boolean canAttack = true;
	boolean canMove = true;
	
	playerThread pThread;
	enemyThread eThread;
	
	Map map;
	
	Spells spell;
	
	float mapRenderX = 0;
	float mapRenderY = 0;
	boolean mapMove = false;
	
	int screenSizeX = 640;
	int screenSizeY = 480;
	
	Music music;
	Music deadMusic;
	Sound attack1;
	Sound attack2;
	Sound attack3;
	soundThread SFXThread;
	
	Inventory in;
	boolean inventoryOpen; 
	boolean mainOpen;
	
	int nextNum;
	Font font;
	TrueTypeFont Tfont;
	TrueTypeFont Tfont2;
	TrueTypeFont Sfont;
	boolean drawMessage;
	Image textbox;
	
	float iconMenuX;
	float iconMenuY;
	boolean menuUpDown;
	boolean menuWalk;
	boolean miniMenu;
	boolean quitMenu;
	boolean levelUp;
	boolean spelling;
	
	int statUp;
	
	ArrayList<Enemy> enemyList;
	ArrayList<Enemy> enemyAList;
	ArrayList<Randomizer> randies;
	
	Animation fireSpell;
	
	public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Tester());
            app.setDisplayMode(640, 480, false);
            app.start();
        } catch (Exception e){
        	System.out.println(e);
        }
    }
	
    public Tester() {
        super("Castle Benjful");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	//-------------------------------------
    	//MAIN MENU
    	//-------------------------------------
    	mainOpen = true;
    	inventoryOpen = false;
    	
    	mainMenuBG = new Image("/Users/Hillary/GameDev/CS140MP2/src/images/mainmenuBG.png");
    	castleBenj = new Image("/Users/Hillary/GameDev/CS140MP2/src/images/castlebenjful.png");
    	
    	iconMenuX = 240;
    	iconMenuY = 240;
    	menuUpDown = true; // TRUE - UP , FALSE - DOWN
    	menuWalk = false;
    	miniMenu = false;
    	quitMenu = false;
    	levelUp = false;
    	spelling = false;
    	statUp = 1;
    	//-------------------------------------
    	//PLAYER
    	player = new Player();
    	player.setXPos(240);
    	player.setYPos(300);
    	walkDown = true;
    	//-------------------------------------
    	//IMAGES
    	//-------------------------------------
    	//ICON
    	icon = new Image("images/iconGirl.png");
    	//-------------------------------------
    	//STILL
    	//-------------------------------------
    	girlStillUp = new Image(player.StillUp);
    	girlStillDown = new Image(player.StillDown);
    	girlStillLeft = new Image(player.StillLeft);
    	girlStillRight = new Image(player.StillRight);
    	//-------------------------------------
    	//WALKING
    	//-------------------------------------
    	int [] duration = {100,100,100,100,100,100};
    	//UP
    	Image [] girlWalkUp = {new Image(player.MoveUp), new Image(player.MoveUp2), new Image(player.MoveUp3), new Image(player.MoveUp4), new Image(player.MoveUp5), new Image(player.MoveUp6)}; 
    	girlMoveUp = new Animation(girlWalkUp, duration, true);
    	//DOWN
    	Image [] girlWalkDown = {new Image(player.MoveDown), new Image(player.MoveDown2), new Image(player.MoveDown3), new Image(player.MoveDown4), new Image(player.MoveDown5), new Image(player.MoveDown6)};
    	girlMoveDown = new Animation(girlWalkDown, duration, true);
    	//LEFT
    	Image [] girlWalkLeft = {new Image(player.MoveLeft), new Image(player.MoveLeft2), new Image(player.MoveLeft3), new Image(player.MoveLeft4), new Image(player.MoveLeft5), new Image(player.MoveLeft6)};
    	girlMoveLeft = new Animation(girlWalkLeft, duration, true);
    	//RIGHT
    	Image [] girlWalkRight = {new Image(player.MoveRight), new Image(player.MoveRight2), new Image(player.MoveRight3), new Image(player.MoveRight4), new Image(player.MoveRight5), new Image(player.MoveRight6)};
    	girlMoveRight = new Animation(girlWalkRight, duration, true);
    	//-------------------------------------
    	//ATTACKING
    	//-------------------------------------  	
    	int [] duration2 = {100,80,80,160};
    	//-------------------------------------
    	//DOWN (1)
    	Image [] Attack1 = {new Image(player.Attack1), new Image(player.Attack1_2), new Image(player.Attack1_3), new Image(player.Attack1_4)};
    	girlAttack1 = new Animation(Attack1, duration2, true);
    	girlAttack1.stopAt(3);
    	//DOWN (2)
    	Image [] Attack12 = {new Image(player.Attack22), new Image(player.Attack22_2), new Image(player.Attack22_3), new Image(player.Attack22_4)};
    	girlAttack12 = new Animation(Attack12, duration2, true);
    	girlAttack12.stopAt(3);
    	//DOWN (3)
    	Image [] Attack13 = {new Image(player.Attack23), new Image(player.Attack23_2), new Image(player.Attack23_3), new Image(player.Attack23_4)};
    	girlAttack13 = new Animation(Attack13, duration2, true);
    	girlAttack13.stopAt(3);
    	//-------------------------------------
    	//LEFT (1)
    	Image [] Attack2 = {new Image(player.Attack2), new Image(player.Attack2_2), new Image(player.Attack2_3), new Image(player.Attack2_4)};
    	girlAttack2 = new Animation(Attack2, duration2, true);
    	girlAttack2.stopAt(3);
    	//LEFT (2)
    	Image [] Attack22 = {new Image(player.Attack32), new Image(player.Attack32_2), new Image(player.Attack32_3), new Image(player.Attack32_4)};
    	girlAttack22 = new Animation(Attack22, duration2, true);
    	girlAttack22.stopAt(3);
    	//LEFT (3)
    	Image [] Attack23 = {new Image(player.Attack33), new Image(player.Attack33_2), new Image(player.Attack33_3), new Image(player.Attack33_4)};
    	girlAttack23 = new Animation(Attack23, duration2, true);
    	girlAttack23.stopAt(3);
    	//-------------------------------------
    	//RIGHT (1)
    	Image [] Attack3 = {new Image(player.Attack3), new Image(player.Attack3_2), new Image(player.Attack3_3), new Image(player.Attack3_4)};
    	girlAttack3 = new Animation(Attack3, duration2, true);
    	girlAttack3.stopAt(3);
    	//RIGHT (2)
    	Image [] Attack32 = {new Image(player.Attack42), new Image(player.Attack42_2), new Image(player.Attack42_3), new Image(player.Attack42_4)};
    	girlAttack32 = new Animation(Attack32, duration2, true);
    	girlAttack32.stopAt(3);
    	//RIGHT (3)
    	Image [] Attack33 = {new Image(player.Attack43), new Image(player.Attack43_2), new Image(player.Attack43_3), new Image(player.Attack43_4)};
    	girlAttack33 = new Animation(Attack33, duration2, true);
    	girlAttack33.stopAt(3);
    	//-------------------------------------
    	//UP (1)
    	Image [] Attack4 = {new Image(player.Attack4), new Image(player.Attack4_2), new Image(player.Attack4_3), new Image(player.Attack4_4)};
    	girlAttack4 = new Animation(Attack4, duration2, true);
    	girlAttack4.stopAt(3);
    	//UP (2)
    	Image [] Attack42 = {new Image(player.Attack12), new Image(player.Attack12_2), new Image(player.Attack12_3), new Image(player.Attack12_4)};
    	girlAttack42 = new Animation(Attack42, duration2, true);
    	girlAttack42.stopAt(3);
    	//UP (3)
    	Image [] Attack43 = {new Image(player.Attack13), new Image(player.Attack13_2), new Image(player.Attack13_3), new Image(player.Attack13_4)};
    	girlAttack43 = new Animation(Attack43, duration2, true);
    	girlAttack43.stopAt(3);
    	//-------------------------------------
    	pThread = new playerThread();
    	pThread.start();
    	player.setThread(pThread);
    	//-------------------------------------
    	//DYING ANIMATIONS
    	//-------------------------------------
    	int[] duration3 = {300, 300, 300, 300, 300, 300, 300, 300, 300, 300};
    	Image [] fading = {new Image("images/fade1.png"), new Image("images/fade2.png"),new Image("images/fade3.png"),new Image("images/fade4.png"),new Image("images/fade5.png"),new Image("images/fade6.png"),new Image("images/fade7.png"),new Image("images/fade8.png"),new Image("images/fade9.png"),new Image("images/fade10.png")};
    	fadeOut = new Animation(fading, duration3, true);
    	fadeOut.stopAt(9);
    	
    	int[] duration4 = {500, 500, 500, 500};
    	Image [] deadP = {new Image("images/dead21.png"), new Image("images/dead22.png"), new Image("images/dead23.png") , new Image("images/dead24.png")};
    	deadPlayer = new Animation(deadP, duration4, true);
    	deadPlayer.stopAt(3);
    	//-------------------------------------
    	//CASTING ANIMATIONS
    	//-------------------------------------
    	int [] duration5 = {100, 100, 100};
    	Image [] cast = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/casting1.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/casting2.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/casting3.png")};
    	casting = new Animation(cast, duration5, true);
    	
    	Image[] fcast = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/casting3.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/casting4.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/casting5.png")};
    	endCast = new Animation(fcast, duration5, true);
    	endCast.stopAt(2);
    	//-------------------------------------
    	//ENEMIES
    	//-------------------------------------
    	eThread = new enemyThread();
    	eThread.start();
    	initEnemies();
    	//-------------------------------------
    	//SPELLS
    	//-------------------------------------
    	spell = new Spells();
    	int[] durationFire = {150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150};
		Image [] fire = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell1.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell2.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell3.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell4.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell5.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell6.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell7.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell8.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell9.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell10.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell11.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell12.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell13.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell14.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell15.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell16.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell17.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell18.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell19.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell5.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell4.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell3.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell2.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell1.png")};
		fireSpell = new Animation(fire, durationFire, true);
    	//-------------------------------------
    	//MAP
    	//-------------------------------------
    	map = new Map();
    	map.setThisMap(1);
    	mapBG = new Image(map.mapURLBG);
    	mapFG = new Image(map.mapURLFG);
    	mapRenderX = map.getMPX();
    	mapRenderY = map.getMPY();
    	//-------------------------------------
    	
    	//SOUNDS
    	//-------------------------------------
    	//MAP MUSIC
    	//-------------------------------------
    	music = new Music(map.mapMusic);
    	deadMusic = new Music("music/3-20-long-goodbye.ogg");
    	//-------------------------------------
    	//SFX
    	//-------------------------------------
    	SFXThread = new soundThread();
    	SFXThread.start();
    	attack1 = new Sound(player.attackSFX1);
    	attack2 = new Sound(player.attackSFX2);
    	attack3 = new Sound(player.attackSFX3);
    	//-------------------------------------
    	//OBJECTS
    	//-------------------------------------
    	drawMessage = false;
    	//-------------------------------------
    	//INVENTORY
    	//-------------------------------------
    	in = new Inventory();
    	//-------------------------------------
    	//FONT
    	//-------------------------------------
    	nextNum = 0;
    	Font font = new Font("Fixedsys Regular", Font.BOLD, 30);
    	Font font2 = new Font("Fixedsys Regular", Font.BOLD, 25);
    	Font font3 = new Font("Fixedsys Regular", Font.BOLD, 40);
    	Tfont = new TrueTypeFont(font, false); 
    	Tfont2 = new TrueTypeFont(font3, false); 
    	Sfont = new TrueTypeFont(font2, false);
    	textbox = new Image("images/textboxthing.png");
    	//-------------------------------------
    	
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	//MUSIC
    	if(!music.playing() && player.isAlive){
    		music.play();
    	}
    	
    	if(!player.isAlive && !deadMusic.playing()){
    		music.stop();
    		deadMusic.play();
    	}
    	
    	mapMove = false;
    	
    	//EXIT
    	Input input = container.getInput();
    	if(input.isKeyDown(Input.KEY_ESCAPE)){
    		buttonPressed = "ESCAPE";
    		quit = closeRequested();
    	} 
    	
    	//LEVEL UP
    	if(!mainOpen && !inventoryOpen && levelUp){
    		
    		if(input.isKeyPressed(Input.KEY_Z)){
    			if(statUp == 1){
    				player.str++;
    				player.updateStats(false);
    			} else if(statUp == 2){
    				player.agi++;
    				player.updateStats(true);
    			} else if(statUp == 3){
    				player.intl++;
    				player.updateStats(false);
    			}
    			
    			player.level++;
    			levelUp = false;
    			initEnemies();
    		}
    		
    		if(input.isKeyPressed(Input.KEY_LEFT)){
    			statUp--;
    			if(statUp < 1){
    				statUp = 1;
    			}
    		}
    		
    		if(input.isKeyPressed(Input.KEY_RIGHT)){
    			statUp++;
    			if(statUp > 3){
    				statUp = 3;
    			}
    		}
    	}
    	
    	
		
    	//--------------------------------------------------
    	//MAIN MENU
    	//--------------------------------------------------
    	if(!mainOpen && !player.isAlive){
    		if(input.isKeyPressed(Input.KEY_Q)){
    			mainOpen = true;
    			
    			iconMenuY = 240;
    			
    			player = new Player();
    			player.isAlive = true;
    			player.setXPos(240);
    	    	player.setYPos(300);
    	    	walkDown = true;
    	    	
    	    	deadMusic.stop();
    	    	initEnemies();
    		}
    		
    	}
    	
    	if(mainOpen){
    		Rectangle icon = new Rectangle((int)(iconMenuX), (int)(iconMenuY), 32, 64);
    		Rectangle up = new Rectangle(230, 100, 70, 80);
    		Rectangle down = new Rectangle(230, 460, 70, 30);
    		
    		if(input.isKeyDown(Input.KEY_UP) && !miniMenu){
    			iconMenuY = iconMenuY - 0.3f;
    			menuUpDown = true;
    			menuWalk = true;
    			icon = new Rectangle((int)(iconMenuX), (int)(iconMenuY), 32, 64);
    		} else if(input.isKeyDown(Input.KEY_DOWN) && !miniMenu){
    			if(!icon.intersects(down)){
    				iconMenuY = iconMenuY + 0.3f;
    			}
    			menuUpDown = false;
    			menuWalk = true;
    			icon = new Rectangle((int)(iconMenuX), (int)(iconMenuY), 32, 64);
    		} else {
    			menuWalk = false;
    		}
    		
    		//MINIMENU (QUIT)
    		//-------------------------------------------
    		if(icon.intersects(down)){;
    			quitMenu = true;
    		
    			if(input.isKeyPressed(Input.KEY_Z)){
    				quit = true;
    			}
    			
    		} else {
    			quitMenu = false;
    		}
    		
    		//-------------------------------------------
    		
    		if(up.contains(icon)){
    			mainOpen = false;
    			System.out.println("FALSE");
    		}
    		
    	}
    	//--------------------------------------------------
    	//INVENTORY
    	//--------------------------------------------------
    	if(input.isKeyPressed(Input.KEY_I) && !inventoryOpen){
    		inventoryOpen = true;
    		System.out.println("Inventory is Open");
    		in.selected = 0;
    	}
    	//--------------------------------------------------
    	if(inventoryOpen){
    		if(input.isKeyPressed(Input.KEY_O)){
        		System.out.println("Inventory is Closed");
        		inventoryOpen = false;
        	}
    		
    		if(input.isKeyPressed(Input.KEY_Z)){
    			if(!in.isSelected){
    				in.isSelected = true;
    				in.arrow=true;
    			} else {
    				in.isSelected=false;
    				if(in.arrow){
    					if(in.selected<in.objectList.size()){
    						if(in.objectList.get(in.selected).itemClass==1){
    							if(((Equipable)in.objectList.get(in.selected)).type==0){
			    					if(!((Equipable)in.objectList.get(in.selected)).equipped){
			    						if(in.weaponEquipped>=0){
			    							((Equipable)in.objectList.get(in.weaponEquipped)).equipped=false;
			    						}
			    						in.weaponEquipped=in.selected;
										((Equipable)in.objectList.get(in.selected)).equipped=true;
			    					}else{
			    						in.weaponEquipped=-1;
										((Equipable)in.objectList.get(in.selected)).equipped=false;
			    					}
    							}else if(((Equipable)in.objectList.get(in.selected)).type==1){
    								if(!((Equipable)in.objectList.get(in.selected)).equipped){
			    						if(in.headgearEquipped>=0){
			    							((Equipable)in.objectList.get(in.headgearEquipped)).equipped=false;
			    						}
			    						in.headgearEquipped=in.selected;
										((Equipable)in.objectList.get(in.selected)).equipped=true;
			    					}else{
			    						in.headgearEquipped=-1;
										((Equipable)in.objectList.get(in.selected)).equipped=false;
			    					}
    							}else if(((Equipable)in.objectList.get(in.selected)).type==2){
    								if(!((Equipable)in.objectList.get(in.selected)).equipped){
			    						if(in.armorEquipped>=0){
			    							((Equipable)in.objectList.get(in.armorEquipped)).equipped=false;
			    						}
			    						in.armorEquipped=in.selected;
										((Equipable)in.objectList.get(in.selected)).equipped=true;
			    					}else{
			    						in.armorEquipped=-1;
										((Equipable)in.objectList.get(in.selected)).equipped=false;
			    					}
    							}else{
    								if(!((Equipable)in.objectList.get(in.selected)).equipped){
			    						if(in.bootsEquipped>=0){
			    							((Equipable)in.objectList.get(in.bootsEquipped)).equipped=false;
			    						}
			    						in.bootsEquipped=in.selected;
										((Equipable)in.objectList.get(in.selected)).equipped=true;
			    					}else{
			    						in.bootsEquipped=-1;
										((Equipable)in.objectList.get(in.selected)).equipped=false;
			    					}
    							}
		    					
    						}else{
    							in.objectList.remove(in.selected);
    							if(in.weaponEquipped>=0 && in.weaponEquipped>in.selected){
    								in.weaponEquipped--;
    							}
    							if(in.headgearEquipped>=0 && in.headgearEquipped>in.selected){
    								in.headgearEquipped--;
    							}
    							if(in.armorEquipped>=0 && in.armorEquipped>in.selected){
    								in.armorEquipped--;
    							}
    							if(in.bootsEquipped>=0 && in.bootsEquipped>in.selected){
    								in.bootsEquipped--;
    							}
    						}
    					}
						System.out.println("Yes");
					}else{
						System.out.println("No");
					}
    			}	
    		}
    		
    		if(!in.isSelected){
    			if(input.isKeyPressed(Input.KEY_RIGHT)){
	    			if(in.selected+1<40){
	    				in.selected++;
	    			}
	    		} else if(input.isKeyPressed(Input.KEY_LEFT)){
	    			if(in.selected-1>=0){
	    				in.selected--;
	    			}
	    		} else if(input.isKeyPressed(Input.KEY_UP)){
	    			if(in.selected<40 && in.selected-10>=0){
	    				in.selected-=10;
	    			}
	    		} else if(input.isKeyPressed(Input.KEY_DOWN)){
	    			if(in.selected+10<40 && in.selected>=0){
	    				in.selected+=10;
	    			}
	    		} 
    		} else {
    			if(input.isKeyPressed(Input.KEY_UP)){
 	    			in.arrow = true;
 	    		}else if(input.isKeyPressed(Input.KEY_DOWN)){
 	    			in.arrow = false;
 	    		} else if(input.isKeyPressed(Input.KEY_X)){
 	    			in.isSelected = false;
 	    		}
    		}
    	}
    	//--------------------------------------------------
    	//MAP MOVEMENT
    	//--------------------------------------------------
    	if(input.isKeyDown(Input.KEY_UP) && player.getYPos() <= 100 && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		if(!map.collide(player.getXPos(), player.getYPos(),1) && map.getMapVertical(1)){
    			mapRenderY = mapRenderY + 0.3f;
    			map.moveObjects(0, 0.3f);
    			mapMove = true;
    			
    			for(int i = 0; i < enemyList.size(); i++){
        			enemyList.get(i).setYPos(enemyList.get(i).getYPos() + 0.3f);
        		}
        	}
    		map.setMPY(mapRenderY);
    		buttonIsPressed = true;
    		walkUp = true;
    		walkDown = false;
    		walkLeft = false;
    		walkRight = false;
    		player.setFace(1);
    		
    		
    	} else if(input.isKeyDown(Input.KEY_DOWN) && player.getYPos() >= screenSizeY - 175 && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		System.out.println("MAP MOVE UP: " + map.getMapVertical(2));
    		if(!map.collide(player.getXPos(), player.getYPos(),2) && map.getMapVertical(2)){
    			System.out.println();
    			mapRenderY = mapRenderY - 0.3f;
    			map.moveObjects(0, -0.3f);
    			mapMove = true;
    			
    			for(int i = 0; i < enemyList.size(); i++){
        			enemyList.get(i).setYPos(enemyList.get(i).getYPos() - 0.3f);
        		}
    		}
    		map.setMPY(mapRenderY);
    		buttonIsPressed = true;
    		walkDown = true;
    		walkUp = false;
    		walkLeft = false;
    		walkRight = false;
    		player.setFace(2);
    		
    		
    	} else if(input.isKeyDown(Input.KEY_LEFT) && player.getXPos() <= 100 && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		if(!map.collide(player.getXPos(), player.getYPos(),3) && map.getMapHorizontal()){
    			mapRenderX = mapRenderX + 0.3f;
    			map.moveObjects(0.3f, 0);
    			mapMove = true;
    			
    			for(int i = 0; i < enemyList.size(); i++){
        			enemyList.get(i).setXPos(enemyList.get(i).getXPos() + 0.3f);
        		}
    		}
    		map.setMPX(mapRenderX);
    		buttonIsPressed = true;
    		walkLeft = true;
    		walkUp = false;
    		walkDown = false;
    		walkRight = false;
    		player.setFace(3);
    		
    		
    	} else if(input.isKeyDown(Input.KEY_RIGHT) && player.getXPos() >= screenSizeX - 150 && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		if(!map.collide(player.getXPos(), player.getYPos(),4) && map.getMapHorizontal()){
    			mapRenderX = mapRenderX - 0.3f;
    			map.moveObjects(-0.3f, 0);
    			mapMove = true;
    			
    			for(int i = 0; i < enemyList.size(); i++){
        			enemyList.get(i).setXPos(enemyList.get(i).getXPos() - 0.3f);
        		}
    		}
    		map.setMPX(mapRenderX);
    		buttonIsPressed = true;
    		walkRight = true;
    		walkUp = false;
    		walkLeft = false;
    		walkDown = false;
    		player.setFace(4);
    		
    		
    	}
    	
    	//PLAYER MOVEMENT
    	if(input.isKeyDown(Input.KEY_UP) && !mapMove && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		buttonPressed = "ARROW UP";
    		buttonIsPressed = true;
    		walkUp = true;
    		walkDown = false;
    		walkLeft = false;
    		walkRight = false;
    		
    		if(!map.collide(player.getXPos(), player.getYPos(),1)){
    			player.setYPos(player.getYPos() - 0.3f);
    		}
    		player.setFace(1);
    	} else if (input.isKeyDown(Input.KEY_DOWN) && !mapMove && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		buttonPressed = "ARROW DOWN";
    		buttonIsPressed = true;
    		walkDown = true;
    		walkUp = false;
    		walkLeft = false;
    		walkRight = false;
    		
    		if(!map.collide(player.getXPos(), player.getYPos(),2)){
    			player.setYPos(player.getYPos() + 0.3f);
    		}
    		player.setFace(2);
    	} else if(input.isKeyDown(Input.KEY_LEFT) && !mapMove && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		buttonPressed = "ARROW LEFT";
    		buttonIsPressed = true;
    		walkLeft = true;
    		walkDown = false;
    		walkUp = false;
    		walkRight = false;
    		
    		if(!map.collide(player.getXPos(), player.getYPos(),3)){
    			player.setXPos(player.getXPos() - 0.3f);
    		}
    		player.setFace(3);
    	} else if(input.isKeyDown(Input.KEY_RIGHT) && !mapMove && canMove && !mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		buttonPressed = "ARROW RIGHT";
    		buttonIsPressed = true;
    		walkRight = true;
    		walkDown = false;
    		walkLeft = false;
    		walkUp = false;
    		
    		if(!map.collide(player.getXPos(), player.getYPos(),4) && !mainOpen){
    			player.setXPos(player.getXPos() + 0.3f);
    		}
    		player.setFace(4);
    	} else if(!mapMove && !mainOpen){
    		buttonPressed = "ARROW BUTTONS NOT PRESSED / CANNOT MOVE";
    		buttonIsPressed = false;
    	}
    	
    	
    	//ZXC INTERACTIONS
    	if(!drawMessage && !canAttack){
			canAttack = true;
		}
    	
    	if(input.isKeyPressed(Input.KEY_Z) && !mainOpen && !inventoryOpen && !levelUp && !xDown && !spelling && player.isAlive){
    		
    		if(map.collideObject(player.getXPos(), player.getYPos(), player.getFace()) != "" && !drawMessage){
    			drawMessage = true;
    			canAttack = false;
    			canMove = false;
    		} else if(drawMessage && map.collideGameObject(player.getXPos(), player.getYPos(), player.getFace()).hasNext(nextNum+3)){
    			drawMessage = true;
    			canAttack = false;
    			canMove = false;
    			nextNum = nextNum+3;
    		} else if(drawMessage){
    			drawMessage = false;
    			canMove = true;
    			nextNum = 0;
    		}
    		
    		if(canAttack){
    			boolean checker = false;
        		
        		buttonPressed = "Z";
        		buttonIsPressed = false;
        		zDown = true; 
        		xDown = false;
        		cDown = false;
        		
        		//PLAYER ATTACKS ENEMY
        		for(int i = 0; i < enemyList.size(); i++){
        			if(player.hitbox(enemyList.get(i).rect)){
        				enemyList.get(i).receiveDmg(player.attackDmg());
        			}
        		}
        		
        		if((pThread.getSleepTime(1) > 0 && walkUp) || (pThread.getSleepTime(2) > 0 && walkDown) || (pThread.getSleepTime(3) > 0 && walkLeft) || (pThread.getSleepTime(4) > 0 && walkRight)){
    				checker = true;
    			}
        		
        		if(pThread.getSleepTime(1) <= 0 && walkUp){
    				player.setCombo(1, 0);
    			} else if(pThread.getSleepTime(2) <= 0 && walkDown){
    				player.setCombo(2, 0);
    			} else if(pThread.getSleepTime(3) <= 0 && walkLeft){
    				player.setCombo(3, 0);
    			} else if(pThread.getSleepTime(4) <= 0 && walkRight){
    				player.setCombo(4, 0);
    			}
        		
        		if((player.getCombo(1) == 0 && walkUp) || (player.getCombo(2) == 0 && walkDown) || (player.getCombo(3) == 0 && walkLeft) || (player.getCombo(4) == 0 && walkRight)){
        			if(!SFXThread.getNeedSleep()){
        				attack1.play();
        				SFXThread.setSleepTime(420);
        				SFXThread.setNeedSleep(true);
        			}
        			
        			if(walkUp){
        				pThread.setSleepTime(1, 1000);
        			} else if(walkDown){
        				pThread.setSleepTime(2, 1000);
        			} else if(walkLeft){
        				pThread.setSleepTime(3, 1000);
        			} else if(walkRight){
        				pThread.setSleepTime(4, 1000);
        			}
        			
        		} else if((player.getCombo(1) == 1 && walkUp) || (player.getCombo(2) == 1 && walkDown) || (player.getCombo(3) == 1 && walkLeft) || (player.getCombo(4) == 1 && walkRight)){
        			
        			if(walkUp && checker){
        				pThread.setSleepTime(1, 1000);
        			} else if(walkDown && checker){
        				pThread.setSleepTime(2, 1000);
        			} else if(walkLeft && checker){
        				pThread.setSleepTime(3, 1000);
        			} else if(walkRight && checker){
        				pThread.setSleepTime(4, 1000);
        			}
        			
        			if(!SFXThread.getNeedSleep() && checker){
        				attack2.play();
        				SFXThread.setSleepTime(420);
        				SFXThread.setNeedSleep(true);
        			}
        			
        			
        		} else if((player.getCombo(1) == 2 && walkUp) || (player.getCombo(2) == 2 && walkDown) || (player.getCombo(3) == 2 && walkLeft) || (player.getCombo(4) == 2 && walkRight)){
        			
        			if(!SFXThread.getNeedSleep() && checker){
        				attack3.play();
        				SFXThread.setSleepTime(420);
        				SFXThread.setNeedSleep(true);
        			}
        		}
    		}
    		
    		
    	} 
    	
    	//HEALING
    	if(!mainOpen && !inventoryOpen && !levelUp && player.isAlive){
	    	if(xDown && input.isKeyPressed(Input.KEY_X)){
	    		xDown = false;
	    		canMove = true;
	    		canAttack = true;
	    	}
	    	
	    	if(!xDown && input.isKeyPressed(Input.KEY_X)){
	    		xDown = true;
	    		canMove = false;
	    		canAttack = false;
	    	}
    	}
    	
    	//FIRE SPELL
    	if(spelling){
    		if(player.currentMana <= 0 || player.currentHP <= 0){
    			player.currentMana = 0;
    			spelling = false;
    			canMove = true;
    			canAttack = true;
    			fireSpell.restart();
    		}
    		
    		if(input.isKeyPressed(Input.KEY_C)){
    			spelling = false;
    			canMove = true;
    			canAttack = true;
    			fireSpell.restart();
    		}
    	}
    	
    	if(!mainOpen && !inventoryOpen && !levelUp && player.isAlive){
    		if(input.isKeyPressed(Input.KEY_C) && player.currentMana > 0){
    			spelling = true;
    			canMove = false;
    			canAttack = false;
    		}
    	}
    	
    	
    	
    	
    	
    	
    	if(girlAttack1.isStopped() || girlAttack12.isStopped() || girlAttack13.isStopped() || girlAttack2.isStopped() || girlAttack22.isStopped() || girlAttack23.isStopped() || girlAttack3.isStopped() || girlAttack32.isStopped() || girlAttack33.isStopped() || girlAttack4.isStopped() || girlAttack42.isStopped() || girlAttack43.isStopped()){
    		zDown = false;
    		xDown = false;
    		
    		if(girlAttack1.isStopped() || girlAttack12.isStopped() || girlAttack13.isStopped()){
    			girlAttack1.restart();
        		girlAttack12.restart();
        		girlAttack13.restart();
    			player.setCombo(2, player.getCombo(2)+1);
    		}
    		
    		if(girlAttack2.isStopped() || girlAttack22.isStopped() || girlAttack23.isStopped()){
    			girlAttack2.restart();
    			girlAttack22.restart();
    			girlAttack23.restart();
    			player.setCombo(3, player.getCombo(3)+1);
    		}
    		
    		if(girlAttack3.isStopped() || girlAttack32.isStopped() || girlAttack33.isStopped()){
    			girlAttack3.restart();
    			girlAttack32.restart();
    			girlAttack33.restart();
    			player.setCombo(4, player.getCombo(4)+1);
    		}
    		
    		if(girlAttack4.isStopped() || girlAttack42.isStopped() || girlAttack43.isStopped()){
    			girlAttack4.restart();
    			girlAttack42.restart();
    			girlAttack43.restart();
    			player.setCombo(1, player.getCombo(1)+1);
    		}
    	}
    	
    	
    	//------------------------------------------------
    	//ENEMIES
    	//------------------------------------------------
    	if(!mainOpen && !inventoryOpen){
        	int random = 0;
        	enemyAList = new ArrayList<Enemy>();
        	enemyAList = new ArrayList<Enemy>();
        	for(int i = 0; i < enemyList.size(); i++){
        		//------------------------------------------------
        		//MOVE ENEMIES
        		//------------------------------------------------
        		if(enemyList.get(i).getCurrentHP() <= 0 && !enemyList.get(i).isDead && !eThread.isSleeping){
        			//Enemy HP < 0
        			eThread.setEnemy(enemyList.get(i));
        		}
        		
        		if(enemyList.get(i).aggression == 1 && enemyList.get(i).getCurrentHP() > 0){
        			//Move to Player (1)
        			enemyList.get(i).AIMove(player.getXPos(), player.getYPos(), player.getRectangle(), map, enemyList);
        			if(player.getARectangle().contains(enemyList.get(i).getRectangle()) && enemyList.get(i).getCurrentHP() > 0){
        				enemyAList.add(enemyList.get(i));
        			}
        		} else if(enemyList.get(i).aggression == 2 && enemyList.get(i).getCurrentHP() > 0){
        			//Random Movement (2)
        			int randX = randies.get(random).x;
        			int randY = randies.get(random).y;
        			enemyList.get(i).AIMove(randX , randY, new Rectangle(randX, randY, 50, 50), map, enemyList);
        			random++;
        		}
        	}
        	
        	//------------------------------------------------
    		//ATTACK ENEMIES
        	//------------------------------------------------
        	boolean someoneAttacking = false;
        	for(int j = 0; j < enemyAList.size(); j++){
        		if(enemyAList.get(j).getAttacking()){
        			someoneAttacking = true;
        		}
        	}
        	
        	if(!someoneAttacking && enemyAList.size() > 0){
        		Random randomNum = new Random();
        		int rNum = randomNum.nextInt(enemyAList.size());
        		
        		while(enemyAList.get(rNum).getCurrentHP() <= 0){
        			rNum = randomNum.nextInt(enemyAList.size());
        		}
        		
        		
        		if(!eThread.isSleeping){
        			enemyAList.get(rNum).setAttacking(true);
        			eThread.setEnemy(enemyAList.get(rNum));
        			if(enemyAList.get(rNum).hitbox(player.getRectangle())){
            			player.receiveDmg(enemyAList.get(rNum).damage);
            		}
        		}
        		
        		
        	}
        	//------------------------------------------------
    		//CHECK DEAD ENEMIES
        	//------------------------------------------------
        	boolean allDead = true;
        	for(int k = 0; k < enemyList.size(); k++){
        		if(!enemyList.get(k).isDead){
        			allDead = false;
        		}
        	}
        	
        	if(allDead){
        		levelUp = true;
        		spelling = false;
        		canMove = true;
    			canAttack = true;
    			fireSpell.restart();
        	}
        	//------------------------------------------------
    	}
    	
    	
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	//---------------------------------------------
    	//QUIT
    	//---------------------------------------------
    	if(quit){
    		container.exit();
    	}
    	//---------------------------------------------
    	//DRAW MAIN MENU
    	//---------------------------------------------
    	if(mainOpen){
    		g.drawImage(mainMenuBG, 0, 0);
    		g.drawImage(castleBenj, 300, 0);
    		
    		if(!this.menuWalk && this.menuUpDown){
    			//FACE UP
    			g.drawImage(girlStillUp, this.iconMenuX, this.iconMenuY);
    		} else if(!this.menuWalk && !this.menuUpDown){
    			//FACE DOWN
    			g.drawImage(girlStillDown, this.iconMenuX, this.iconMenuY);
    		}
    		
    		if(this.menuWalk && this.menuUpDown){
    			girlMoveUp.draw(this.iconMenuX, this.iconMenuY);
    		} else if(this.menuWalk && !this.menuUpDown){
    			girlMoveDown.draw(this.iconMenuX, this.iconMenuY);
    		}
    		
    		if(quitMenu){
    			g.drawString("Quit?", this.iconMenuX, this.iconMenuY - 20);
    		}
    	}
    	//---------------------------------------------
    	//DRAW MAP
    	//---------------------------------------------
    	if(!mainOpen){
    		mapBG.draw(map.getMPX(), map.getMPY());
    	}
    	//---------------------------------------------
    	//DRAW INVENTORY
    	//---------------------------------------------
    	if(inventoryOpen && !mainOpen){
    		in.drawObjects(g);
    		g.drawString("INVENTORY", 250,30);
    		g.drawString("EQUIPMENT", 400, 250);
    		in.drawCurrentItems(g);
    		Tfont2.drawString(300, 400, player.getName());
    		if(in.isSelected){
    			if(in.selected<in.objectList.size()){
	    			in.drawDialogue(g);
	    			
	    			if(in.objectList.get(in.selected).itemClass==1){
	    				if(!((Equipable)in.objectList.get(in.selected)).equipped){
	    					g.drawString("Equip?", 450 , 350);
	    				}else{
	    					g.drawString("Unequip?", 450 , 350);
	    				}
	    			}else{
    					g.drawString("Use?", 450 , 350);
	    			}
	    			g.drawString("Yes", 480, 375);
	    			g.drawString("No", 480, 400);
	    			if(in.arrow){
	    				g.drawString(">", 470, 375);
	    			}else{
	    				g.drawString(">", 470, 400);
	    			}
    			}
    		}
    		if(in.selected<in.objectList.size()){
    			g.drawString(in.objectList.get(in.selected).name, 50, 300);
    			if(in.objectList.get(in.selected).itemClass==0){
    				g.drawString("Usable", 50, 350);
    				g.drawString("Effect: ", 50, 400);
    			}else{
    				g.drawString("Equipable", 50, 350);
    				g.drawString("Item type:", 50, 400);
    				if(((Equipable)in.objectList.get(in.selected)).type==0){
    					g.drawString("Weapon", 155, 400);
    				}else if(((Equipable)in.objectList.get(in.selected)).type==1){
    					g.drawString("Headgear", 155, 400);
    				}else if(((Equipable)in.objectList.get(in.selected)).type==2){
    					g.drawString("Armor", 155, 400);
    				}else{
    					g.drawString("Boots", 155, 400);
    				}
    			}
    		}
    		
    	}
    	
    	if(player.isAlive && !mainOpen && !inventoryOpen){

        	map.drawObjects(g);
        	
        	player.drawARectangle(g);
        	player.drawRectangle(g);
        	g.drawString("PLAYER", 10, 385);
        	g.drawString(Float.toString(player.getXPos()), 10, 400);
        	g.drawString(Float.toString(player.getYPos()), 100, 400);
        	
        	g.drawString("MAP", 10, 415);
        	g.drawString(Float.toString(map.getMPX()), 10, 430);
        	g.drawString(Float.toString(map.getMPY()), 100, 430);
        	
        	g.drawString(buttonPressed, 10, 25);
        	
        	
        	//DRAW ENEMIES
        	for(int i = 0; i < enemyList.size(); i++){
            	if(!enemyList.get(i).isDead){
            		enemyList.get(i).drawEnemy(g, true, enemyList.get(i).getAttacking());
            		enemyList.get(i).drawRectangle(g);
            	}
            }
        	
        	if(zDown && walkDown && player.getCombo(2) == 0){
        		girlAttack1.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos(), player.getYPos()+32, 64, 50);
        		g.setColor(Color.white);
        	}else if(zDown && walkDown && player.getCombo(2) == 1){
        		girlAttack12.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()+16, player.getYPos(), 32, 80);
        		g.setColor(Color.white);
        	}else if(zDown && walkDown && player.getCombo(2) == 2){
        		girlAttack13.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos(), player.getYPos()+32, 32, 48);
        		g.setColor(Color.white);
        	}else if(zDown && walkLeft && player.getCombo(3) == 0){
        		girlAttack2.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()-8, player.getYPos()+48, 32, 32);
        		g.setColor(Color.white);
        	}else if(zDown && walkLeft && player.getCombo(3) == 1){ 
        		girlAttack22.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()-16, player.getYPos(), 32, 64);
        		g.setColor(Color.white);
        	}else if(zDown && walkLeft && player.getCombo(3) == 2){
        		girlAttack23.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()-16, player.getYPos(), 32, 72);
        		g.setColor(Color.white);
        	}else if(zDown && walkRight && player.getCombo(4) == 0){
        		girlAttack3.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()+32, player.getYPos()+48, 32, 32);
        		g.setColor(Color.white);
        	}else if(zDown && walkRight && player.getCombo(4) == 1){
        		girlAttack32.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()+32, player.getYPos(), 32, 64);
        		g.setColor(Color.white);
        	}else if(zDown && walkRight && player.getCombo(4) == 2){
        		girlAttack33.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()+32, player.getYPos(), 32, 72);
        		g.setColor(Color.white);
        	}else if(zDown && walkUp && player.getCombo(1) == 0){
        		girlAttack4.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos(), player.getYPos(), 64, 64);
        		g.setColor(Color.white);
        	}else if(zDown && walkUp && player.getCombo(1) == 1){
        		girlAttack42.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos()-16, player.getYPos()-16, 64, 32);
        		g.setColor(Color.white);
        	}else if(zDown && walkUp && player.getCombo(1) == 2){
        		girlAttack43.draw(player.getXPos()-10, player.getYPos()-10);
        		g.setColor(Color.red);
        		g.drawRect(player.getXPos(), player.getYPos()-16, 32, 48);
        		g.setColor(Color.white);
        	}
        	
        	
        	if(buttonIsPressed && walkUp && !zDown){
        		g.drawString("WALKING UP", 10, 40);
        		girlMoveUp.draw(player.getXPos(), player.getYPos());
        	} else if(buttonIsPressed && walkDown && !zDown){
        		girlMoveDown.draw(player.getXPos(), player.getYPos());
        	} else if(buttonIsPressed && walkLeft && !zDown){
        		girlMoveLeft.draw(player.getXPos(), player.getYPos());
        	} else if(buttonIsPressed && walkRight && !zDown){
        		girlMoveRight.draw(player.getXPos(), player.getYPos());
        	} else if(!buttonIsPressed && !zDown){
        		if(walkUp){
        			girlStillUp.draw(player.getXPos(),player.getYPos());
        		} else if(walkDown){
        			girlStillDown.draw(player.getXPos(), player.getYPos());
        		} else if(walkLeft){
        			girlStillLeft.draw(player.getXPos(), player.getYPos());
        		} else if(walkRight){
        			girlStillRight.draw(player.getXPos(), player.getYPos());
        		} else {
        			girlStillDown.draw(player.getXPos(), player.getYPos());
        		}
        	}
        	
        	
        	//ANIMATE HEAL
        	if(xDown && !zDown){
        		spell.animateSpell(0, g, player, enemyList);
        	}
        	
        	//ANIMATE FIRE SPELL
        	if(spelling){
        		spell.animateSpell(1, g, player, enemyList);
        		fireSpell.draw();
        	}
        	
        	
        	//fireSpell.draw();
        	
        	mapFG.draw(map.getMPX(), map.getMPY());
        	
        	if(drawMessage){
        		g.drawImage(textbox, 0, map.getMPY() + 500f);
        		//LINE 1
        		Tfont.drawString(23, map.getMPY() + 518f, map.collideGameObject(player.getXPos(), player.getYPos(), player.getFace()).getMessage(nextNum), Color.black);
        		Tfont.drawString(20, map.getMPY() + 515f, map.collideGameObject(player.getXPos(), player.getYPos(), player.getFace()).getMessage(nextNum), Color.white);
        		
        		//LINE 2
        		Tfont.drawString(23, map.getMPY() + 558f, map.collideGameObject(player.getXPos(), player.getYPos(), player.getFace()).getMessage(nextNum+1), Color.black);
        		Tfont.drawString(20, map.getMPY() + 555f, map.collideGameObject(player.getXPos(), player.getYPos(), player.getFace()).getMessage(nextNum+1), Color.white);
        		
        		//LINE 3
        		Tfont.drawString(23, map.getMPY() + 598f, map.collideGameObject(player.getXPos(), player.getYPos(), player.getFace()).getMessage(nextNum+2), Color.black);
        		Tfont.drawString(20, map.getMPY() + 595f, map.collideGameObject(player.getXPos(), player.getYPos(), player.getFace()).getMessage(nextNum+2), Color.white);
        		
        	}
        	
        	//----------------------------------------------
        	//DRAW LEVEL UP
    		//----------------------------------------------
        	if(levelUp){
        		if(statUp == 1){
        			g.drawString(">", player.getXPos()-40, player.getYPos()-20);
        		} else if(statUp == 2){
        			g.drawString(">", player.getXPos()+10, player.getYPos()-20);
        		} else if(statUp == 3){
        			g.drawString(">", player.getXPos()+60, player.getYPos()-20);
        		}
        		
        		g.drawString("STR", player.getXPos()-30, player.getYPos()-20);
        		g.drawString("AGI", player.getXPos()+20, player.getYPos()-20);
        		g.drawString("INT", player.getXPos()+70, player.getYPos()-20);
        	}
    	}
    	
    	
    	//----------------------------------------------
    	//DRAW STATUS
		//----------------------------------------------
    	if(!mainOpen && !inventoryOpen){
    		
        	Sfont.drawString(175, 13, "HP", Color.black);
        	Sfont.drawString(175, 10, "HP", Color.white);
        	
        	Sfont.drawString(210, 13, Integer.toString(player.currentHP), Color.black);
        	Sfont.drawString(210, 10, Integer.toString(player.currentHP), Color.white);
        	
        	Sfont.drawString(255, 13, "/", Color.black);
        	Sfont.drawString(255, 10, "/", Color.white);
        	
        	Sfont.drawString(265, 13, Integer.toString(player.maxHP), Color.black);
        	Sfont.drawString(265, 10, Integer.toString(player.maxHP), Color.white);
        	
        	Sfont.drawString(345, 13, Integer.toString(player.currentMana), Color.black);
        	Sfont.drawString(345, 10, Integer.toString(player.currentMana), Color.white);
        	
        	Sfont.drawString(390, 13, "/", Color.black);
        	Sfont.drawString(390, 10, "/", Color.white);
        	
        	Sfont.drawString(400, 13, Integer.toString(player.maxMana), Color.black);
        	Sfont.drawString(400, 10, Integer.toString(player.maxMana), Color.white);
        	
        	Sfont.drawString(445, 13, "MP", Color.black);
        	Sfont.drawString(445, 10, "MP", Color.white);
        	
        	g.drawImage(icon, 310, 10);
        	
        	Sfont.drawString(300, 43, "lvl" + Integer.toString(player.level), Color.black);
        	Sfont.drawString(300, 40, "lvl" + Integer.toString(player.level), Color.white);
    	}
    	//----------------------------------------------
    	// DRAW DEATH
    	//----------------------------------------------
    	if(!player.isAlive && !mainOpen){
    		
    		fadeOut.draw();
    		deadPlayer.draw(player.getXPos(), player.getYPos());
    		
    		Tfont.drawString(400, 430, player.name + " died...");
    	}
    	//----------------------------------------------
    }
    
    //INITIALIZE ENEMIES
    public void initEnemies(){    	
    	enemyList = new ArrayList<Enemy>();
    	enemyAList = new ArrayList<Enemy>();
    	randies = new ArrayList<Randomizer>();
    	
    	Random ranran = new Random();
    	int ranranInt = 0;
    	if(player.level <= 3){
    		ranranInt = ranran.nextInt(1) + 1;
    	} else if(player.level > 3 && player.level <= 5){
    		ranranInt = ranran.nextInt(4);
    	} else {
    		ranranInt = ranran.nextInt(6);
    	}
    	
    	for(int i = 0; i < ranranInt; i++){
    		Enemy e = new Enemy(player.level);
    		
    		int ran = 0;
    		if(player.level <= 3){
    			ran = ranran.nextInt(3);
    		} else {
    			ran = ranran.nextInt(6);
    		}
    		
    		e.setEnemy(ran);
    		
    		if(i < 3){
    			e.setXPos(180 + i*100);
    			e.setYPos(150);
    		} else {
    			e.setXPos(180 + (i-4)*100);
    			e.setYPos(250);
    		}
    		
    		int agro = ranran.nextInt(10);
    		if(agro == 1){
    			e.setAggression(2);
    			Randomizer r = new Randomizer();
    			r.start();
    			randies.add(r);
    		} else {
    			e.setAggression(1);
    		}
    		
    		enemyList.add(e);
    	}
    	
    }

    
}
