import java.awt.Font;

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
	Player player;
	Image girlStillUp;
	Image girlStillDown;
	Image girlStillLeft;
	Image girlStillRight;
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
	
	Image mapBG;
	Image mapFG;
	
	String buttonPressed = "";
	boolean buttonIsPressed = false;
	boolean walkUp = false;
	boolean walkDown = false;
	boolean walkLeft = false;
	boolean walkRight = false;
	boolean quit= false;
	boolean zDown= false;
	boolean xDown= false;
	boolean cDown = false;
	boolean canAttack = true;
	boolean canMove = true;
	
	playerThread pThread;
	
	Map map;
	
	float mapRenderX = 0;
	float mapRenderY = 0;
	boolean mapMove = false;
	
	int screenSizeX = 640;
	int screenSizeY = 480;
	
	Music music;
	Sound attack1;
	Sound attack2;
	Sound attack3;
	soundThread SFXThread;
	
	int nextNum;
	Font font;
	TrueTypeFont Tfont;
	boolean drawMessage;
	Image textbox;
	
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
        super("Game Name");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	
    	//-------------------------------------
    	//PLAYER
    	player = new Player();
    	walkDown = true;
    	//IMAGES
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
    	//-------------------------------------
    	
    	//MAP
    	//-------------------------------------
    	map = new Map();
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
    	music.loop();
    	music.play();
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
    	//FONT
    	//-------------------------------------
    	nextNum = 0;
    	Font font = new Font("Fixedsys Regular", Font.BOLD, 30);
    	Tfont = new TrueTypeFont(font, false); 
    	textbox = new Image("/Users/Hillary/GameDev/CS140MP2/src/images/textboxthing.png");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	//MUSIC
    	if(!music.playing()){
    		music.play();
    	}
    	
    	mapMove = false;
    	
    	//ARROW INPUTS
    	Input input = container.getInput();
    	if(input.isKeyDown(Input.KEY_ESCAPE)){
    		buttonPressed = "ESCAPE";
    		quit = closeRequested();
    	} 
    	
    	if(input.isKeyDown(Input.KEY_UP) && player.getYPos() <= 100 && canMove){
    		if(!map.collide(player.getXPos(), player.getYPos(),1) && map.getMapVertical(1)){
    			mapRenderY = mapRenderY + 0.3f;
    			map.moveObjects(0, 0.3f);
    			mapMove = true;
        	}
    		map.setMPY(mapRenderY);
    		buttonIsPressed = true;
    		walkUp = true;
    		walkDown = false;
    		walkLeft = false;
    		walkRight = false;
    		player.setFace(1);
    	} else if(input.isKeyDown(Input.KEY_DOWN) && player.getYPos() >= screenSizeY - 175 && canMove){
    		if(!map.collide(player.getXPos(), player.getYPos(),2) && map.getMapVertical(2)){
    			mapRenderY = mapRenderY - 0.3f;
    			map.moveObjects(0, -0.3f);
    			mapMove = true;
    		}
    		map.setMPY(mapRenderY);
    		buttonIsPressed = true;
    		walkDown = true;
    		walkUp = false;
    		walkLeft = false;
    		walkRight = false;
    		player.setFace(2);
    	} else if(input.isKeyDown(Input.KEY_LEFT) && player.getXPos() <= 100 && canMove){
    		if(!map.collide(player.getXPos(), player.getYPos(),3) && map.getMapHorizontal()){
    			mapRenderX = mapRenderX + 0.3f;
    			map.moveObjects(0.3f, 0);
    			mapMove = true;
    		}
    		map.setMPX(mapRenderX);
    		buttonIsPressed = true;
    		walkLeft = true;
    		walkUp = false;
    		walkDown = false;
    		walkRight = false;
    		player.setFace(3);
    	} else if(input.isKeyDown(Input.KEY_RIGHT) && player.getXPos() >= screenSizeX - 150 && canMove){
    		if(!map.collide(player.getXPos(), player.getYPos(),4) && map.getMapHorizontal()){
    			mapRenderX = mapRenderX - 0.3f;
    			map.moveObjects(-0.3f, 0);
    			mapMove = true;
    		}
    		map.setMPX(mapRenderX);
    		buttonIsPressed = true;
    		walkRight = true;
    		walkUp = false;
    		walkLeft = false;
    		walkDown = false;
    		player.setFace(4);
    	}
    	
    	if(input.isKeyDown(Input.KEY_UP) && !mapMove && canMove){
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
    	} else if (input.isKeyDown(Input.KEY_DOWN) && !mapMove && canMove){
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
    	} else if(input.isKeyDown(Input.KEY_LEFT) && !mapMove && canMove){
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
    	} else if(input.isKeyDown(Input.KEY_RIGHT) && !mapMove && canMove){
    		buttonPressed = "ARROW RIGHT";
    		buttonIsPressed = true;
    		walkRight = true;
    		walkDown = false;
    		walkLeft = false;
    		walkUp = false;
    		
    		if(!map.collide(player.getXPos(), player.getYPos(),4)){
    			player.setXPos(player.getXPos() + 0.3f);
    		}
    		player.setFace(4);
    	} else if(!mapMove){
    		buttonPressed = "ARROW BUTTONS NOT PRESSED/ CANNOT MOVE";
    		buttonIsPressed = false;
    	}
    	
    	
    	//ZXC INTERACTIONS
    	if(input.isKeyPressed(Input.KEY_Z)){
    		
    		if(!drawMessage && !canAttack){
    			canAttack = true;
    		}
    		
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
    		
    		
    	} else if(input.isKeyPressed(Input.KEY_X)){
    		buttonPressed = "X";
    		buttonIsPressed = false;
    		xDown = true;
    		zDown = false;
    		cDown = false;
    	} else if(input.isKeyPressed(Input.KEY_C)){
    		buttonPressed = "C";
    		buttonIsPressed = false;
    		cDown = true;
    		zDown = false;
    		xDown = false;
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
    	
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	mapBG.draw(map.getMPX(), map.getMPY());
    	
    	map.drawObjects(g);
    	
    	g.drawString("PLAYER", 10, 385);
    	g.drawString(Float.toString(player.getXPos()), 10, 400);
    	g.drawString(Float.toString(player.getYPos()), 100, 400);
    	
    	g.drawString("MAP", 10, 415);
    	g.drawString(Float.toString(map.getMPX()), 10, 430);
    	g.drawString(Float.toString(map.getMPY()), 100, 430);
    	
    	g.drawString(buttonPressed, 10, 25);
    	
    	if(zDown && walkDown && player.getCombo(2) == 0){
    		girlAttack1.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkDown && player.getCombo(2) == 1){
    		girlAttack12.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkDown && player.getCombo(2) == 2){
    		girlAttack13.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkLeft && player.getCombo(3) == 0){
    		girlAttack2.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkLeft && player.getCombo(3) == 1){ 
    		girlAttack22.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkLeft && player.getCombo(3) == 2){
    		girlAttack23.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkRight && player.getCombo(4) == 0){
    		girlAttack3.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkRight && player.getCombo(4) == 1){
    		girlAttack32.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkRight && player.getCombo(4) == 2){
    		girlAttack33.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkUp && player.getCombo(1) == 0){
    		girlAttack4.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkUp && player.getCombo(1) == 1){
    		girlAttack42.draw(player.getXPos()-10, player.getYPos()-10);
    	}else if(zDown && walkUp && player.getCombo(1) == 2){
    		girlAttack43.draw(player.getXPos()-10, player.getYPos()-10);
    	}
    	
    	if(xDown && !zDown && !cDown){
    		g.drawString("CAST TECH", 10, 40);
    	}
    	
    	if(cDown && !zDown && !xDown){
    		g.drawString("CAST SPELL", 10, 40);
    	}
    	
    	if(quit){
    		container.exit();
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
    	} else if(!buttonIsPressed && !zDown && !xDown){
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
    }

    
}
