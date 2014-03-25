
import java.util.*;
import java.awt.*;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
	
public class Enemy {
	
	String name;
	int enemy;
	
	float xPos;
	float yPos;
	float speed;
	int face;
	int width;
	int height;
	
	int aggression;
	volatile boolean isAttacking;
	volatile boolean isIdle;
	boolean isDead;
	
	int maxHP;
	int currentHP;
	
	int damage;
	
	boolean walkAnimate;
	Image still1;
	Image still2;
	Image still3;
	Image still4;
	Animation stillAnimate1;
	Animation stillAnimate2;
	Animation stillAnimate3;
	Animation stillAnimate4;
	Animation walk1;
	Animation walk2;
	Animation walk3;
	Animation walk4;
	Animation attack1;
	Animation attack2;
	Animation attack3;
	Animation attack4;
	Animation death1;
	Animation death2;
	Animation death3;
	Animation death4;
	
	Rectangle rect;
	
	public Enemy(){
		this.name = "";
		this.enemy = 0;
		
		this.aggression = 1;
		this.isAttacking = false;
		this.isIdle = false;
		this.isDead = false;
		
		this.maxHP = 100;
		this.currentHP = maxHP;
		
		this.damage = 20;
		
		this.face = 2;
		this.xPos = 300;
		this.yPos = 100;
		this.speed = 0.05f;
		
		try{
			setEnemy0();
		}catch(Exception e){}
	}
	
	public Enemy(float xPos, float yPos){
		this.name = "";
		this.enemy = 0;
		
		this.aggression = 1;
		this.isAttacking = false;
		this.isIdle = false;
		
		this.maxHP = 100;
		this.currentHP = maxHP;
		
		this.damage = 20;
		
		this.face = 2;
		this.xPos = xPos;
		this.yPos = yPos;
		this.speed = 0.05f;

		try{
			setEnemy0();
		}catch(Exception e){}
	}
	
	public void setEnemy(int enemy){
		this.enemy = enemy;
	}
	
	public int getEnemy(){
		return this.enemy;
	}
	
	public void setAggression(int aggression){
		this.aggression = aggression;
	}
	
	public int getAggression(){
		return this.aggression;
	}
	
	public void setAttacking(boolean isAttacking){
		this.isAttacking = isAttacking;
	}
	
	public boolean getAttacking(){
		return this.isAttacking;
	}
	
	public float getXPos(){
		return this.xPos;
	}
	
	public float getYPos(){
		return this.yPos;
	}
	
	public void setXPos(float xPos){
		this.xPos = xPos;
	}
	
	public void setYPos(float yPos){
		this.yPos = yPos;
	}
	
	public void setFace(int face){
		this.face = face;
	}
	
	public int getFace(){
		return this.face;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getSpeed(){
		return this.speed;
	}
	
	public int getCurrentHP(){
		return this.currentHP;
	}
	
	public void setRectangle(){
		rect.setRect(new Rectangle((int)this.xPos, (int)this.yPos, this.width, this.height));
	}
	
	public Rectangle getRectangle(){
		return this.rect;
	}
	
	public void receiveDmg(int damage){
		this.currentHP = currentHP - damage;
		if(currentHP <= 0){
			this.currentHP = 0;
		}
	}
	
	
	public void drawRectangle(Graphics g){
		g.drawRect(this.xPos, this.yPos, this.width, this.height);
		g.drawRect(this.xPos+25f, this.yPos+25f, 2, 2);
		
		if(this.isAttacking && !isIdle){
			g.setColor(Color.red);
			if(this.face == 1){
				g.drawRect(this.xPos+5f, this.yPos-10f, this.width-30, this.height-5);
			} else if(this.face == 2){
				g.drawRect(this.xPos+15f, this.yPos+20f, this.width-30, this.height-5);
			} else if(this.face == 3){
				g.drawRect(this.xPos-20f, this.yPos+15f, this.width-10, this.height-30);
			} else if(this.face == 4){
				g.drawRect(this.xPos+20f, this.yPos+15f, this.width-10, this.height-30);
			}
			g.setColor(Color.white);
		}
		
	}
	
	public void drawEnemy(Graphics g, boolean isWalking, boolean isAttacking){
		
		if(this.isDead){
			return;
		}
		
		if(currentHP <= 0){
			if(this.face == 1){
				death1.draw(this.xPos, this.yPos);
			} else if(this.face == 2){
				death2.draw(this.xPos, this.yPos);
			} else if(this.face == 3){
				death3.draw(this.xPos, this.yPos);
			} else if(this.face == 4){
				death4.draw(this.xPos, this.yPos);
			}
		}
		
		if(isAttacking && !isIdle && currentHP > 0){
			//ATTACKING
			if(this.face == 1){
				attack1.draw(this.xPos, this.yPos);
			} else if(this.face == 2){
				attack2.draw(this.xPos, this.yPos);
			} else if(this.face == 3){
				attack3.draw(this.xPos, this.yPos);
			} else if(this.face == 4){
				attack4.draw(this.xPos, this.yPos);
			}
			
		} else if(isWalking && currentHP > 0){
			//WALKING
		} else if(currentHP > 0){
			//STILL
			if(walkAnimate){
				if(this.face == 1){
					stillAnimate1.draw(this.xPos, this.yPos);
				} else if(this.face == 2){
					stillAnimate2.draw(this.xPos, this.yPos);
				} else if(this.face == 3){
					stillAnimate3.draw(this.xPos, this.yPos);
				} else if(this.face == 4){
					stillAnimate4.draw(this.xPos, this.yPos);
				} 
			} else {
				
			}
		}
	}
	
	public void AIMove(float playerXPos, float playerYPos, Rectangle playerRect, Map map, ArrayList<Enemy> enemyList){
		boolean moveX = true;
		boolean moveY = true;
		
		if(this.face == 1){
			if(playerRect.contains((int)this.xPos, (int)(this.yPos))){
				return;
			}
		} else if(this.face == 2){
			if(playerRect.contains((int)this.xPos, (int)(this.yPos + 32))){
				return;
			}
		} else if(this.face == 3){
			if(playerRect.contains((int)(this.xPos - 5), (int)this.yPos)){
				return;
			}
		} else if(this.face == 4){
			if(playerRect.contains((int)(this.xPos + 32), (int)this.yPos)){
				return;
			}
		}
		
		playerXPos = playerXPos + 16;
		playerYPos = playerYPos + 32;
		
			if(playerXPos+16 >= this.xPos && playerXPos-16 <= this.xPos){
				if(playerYPos > this.yPos){
					this.face = 2;
				} else {
					this.face = 1;
				}
			} else if(playerYPos+32 >= this.yPos && playerYPos-32 <= this.yPos){
				if(playerXPos > this.xPos){
					this.face = 4;
				} else {
					this.face = 3;
				}
			}
			
			for(int i = 0; i < enemyList.size(); i++){
				Enemy tempo = enemyList.get(i);
				if(tempo == this){
					i++;
					if(i == enemyList.size()){
						break;
					}
					tempo = enemyList.get(i);
				}
			
				if(playerXPos < this.xPos){
					if(map.collide(this.xPos - this.speed, this.yPos, 3) || tempo.collide(this.xPos - this.speed, this.yPos, this.face)){
						moveX = false;
						//this.xPos = this.xPos - this.speed;
					} 
				}
				
				if(playerXPos > this.xPos){
					if(map.collide(this.xPos + this.speed, this.yPos, 4) || tempo.collide(this.xPos + this.speed, this.yPos, this.face)){
						moveX = false;
						//this.xPos = this.xPos + this.speed;
					}
				}
				
				if(playerYPos < this.yPos){
					if(map.collide(this.xPos, this.yPos - this.speed, 1) || tempo.collide(this.xPos, this.yPos - this.speed, this.face)){
						moveY = false;
						//this.yPos = this.yPos - this.speed;
					}
				}
				
				if(playerYPos > this.yPos){
					if(map.collide(this.xPos, this.yPos + this.speed, 2) || tempo.collide(this.xPos, this.yPos + this.speed, this.face)){
						moveY = false;
						//this.yPos = this.yPos + this.speed;
					}
				}
			}
			
			if(moveX){
			
				if(playerXPos < this.xPos){
					this.xPos = this.xPos - this.speed;
					
				}
				
				if(playerXPos > this.xPos){
					this.xPos = this.xPos + this.speed;
				}
			} 
			
			if(moveY){
				if(playerYPos < this.yPos){
					this.yPos = this.yPos - this.speed;
				}
				
				if(playerYPos > this.yPos){
					this.yPos = this.yPos + this.speed;
				}
			}
			
			setRectangle();
	}
	
	public boolean hitbox(Rectangle playerRect){
		Rectangle tempo = new Rectangle(1,1,1,1);
		if(this.face == 1){
			tempo = new Rectangle((int)(this.xPos+5f), (int)(this.yPos-10f), this.width-30, this.height-5);
		} else if(this.face == 2){
			tempo = new Rectangle((int)(this.xPos+15f), (int)(this.yPos+20f), this.width-30, this.height-5);
		} else if(this.face == 3){
			tempo = new Rectangle((int)(this.xPos-20f), (int)(this.yPos+15f), this.width-10, this.height-30);
		} else if(this.face == 4){
			tempo = new Rectangle((int)(this.xPos+20f), (int)(this.yPos+15f), this.width-10, this.height-30);
		}
		
		if(tempo.intersects(playerRect)){
			return true;
		}
		
		return false;
	}
	
	public boolean collide(float posX, float posY, int direction){
		
		posX = posX + (float)(width/2);
		posY = posY + (float)(height/2);
		
		float OposX = posX;
		float OposY = posY;
		
		if(direction == 1){
			//UP
			posY = posY - (float)(height/2);
		} else if(direction == 2){
			//DOWN
			posY = posY + (float)(height/2);
		} else if(direction == 3){
			//LEFT
			posX = posX - (float)(width/2);
		} else if(direction == 4){
			//RIGHT
			posX = posX + (float)(width/2);
		}
		
		int x = (int)posX;
		int y = (int)posY;
		
		
		if(rect.contains(x,y) || rect.contains((int)(OposX), (int)(OposY))){
			return true;
		}
		
		
		
		return false;
	}
	
	
	public void setEnemy0() throws SlickException{
		this.name = "Rabite";
		this.enemy = 0;
		this.walkAnimate = true;
		
		this.width = 50;
		this.height = 50;
		
		this.rect = new Rectangle((int)this.xPos, (int)this.yPos, this.width, this.height);
		
		//STILL
		//-------------------------------
		//UP
		int[] duration = {120,120,120};
		Image[] sAnimate1 = {new Image("images/rabite11.png"), new Image("images/rabite12.png") , new Image("images/rabite13.png")};
		stillAnimate1 = new Animation(sAnimate1, duration, true);
		//DOWN
		Image[] sAnimate2 = {new Image("images/rabite21.png"), new Image("images/rabite22.png") , new Image("images/rabite23.png")};
		stillAnimate2 = new Animation(sAnimate2, duration, true);
		//LEFT
		Image[] sAnimate3 = {new Image("images/rabite31.png"), new Image("images/rabite32.png") , new Image("images/rabite33.png")};
		stillAnimate3 = new Animation(sAnimate3, duration, true);
		//RIGHT
		Image[] sAnimate4 = {new Image("images/rabite41.png"), new Image("images/rabite42.png") , new Image("images/rabite43.png")};
		stillAnimate4 = new Animation(sAnimate4, duration, true);
		//-------------------------------
		//MOVING
		//-------------------------------
		walk1 = stillAnimate1;
		walk2 = stillAnimate2;
		walk3 = stillAnimate3;
		walk4 = stillAnimate4;
		//-------------------------------
		//ATTCAKING
		//-------------------------------
		int[] duration2 = {200, 200, 200};
		Image[] Attack1 = {new Image("images/rabiteA11.png"),new Image("images/rabiteA12.png"),new Image("images/rabiteA13.png")};
		attack1 = new Animation(Attack1, duration2, true);
		Image[] Attack2 = {new Image("images/rabiteA21.png"),new Image("images/rabiteA22.png"),new Image("images/rabiteA23.png")};
		attack2 = new Animation(Attack2, duration2, true);
		Image[] Attack3 = {new Image("images/rabiteA31.png"),new Image("images/rabiteA32.png"),new Image("images/rabiteA33.png")};
		attack3 = new Animation(Attack3, duration2, true);
		Image[] Attack4 = {new Image("images/rabiteA41.png"),new Image("images/rabiteA42.png"),new Image("images/rabiteA43.png")};
		attack4 = new Animation(Attack4, duration2, true);
		//-------------------------------
		//DEATH ANIMATIONS
		//-------------------------------
		int[] duration3 = {100, 100, 100, 100, 100, 100};
		Image empty = new Image("images/empty.png");
		Image die1 = new Image("images/rabite13.png");
		Image die2 = new Image("images/rabite23.png");
		Image die3 = new Image("images/rabiteDie3.png");
		Image die4 = new Image("images/rabiteDie4.png");
		Image [] Adeath1 = {die1, empty, die1, empty, die1, empty};
		Image [] Adeath2 = {die2, empty, die2, empty, die2, empty};
		Image [] Adeath3 = {die3, empty, die3, empty, die3, empty};
		Image [] Adeath4 = {die4, empty, die4, empty, die4, empty};
		death1 = new Animation(Adeath1, duration3, true);
		death2 = new Animation(Adeath2, duration3, true);
		death3 = new Animation(Adeath3, duration3, true);
		death4 = new Animation(Adeath4, duration3, true);
	}
	
	
}
