import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
	
public class Enemy {
	
	String name;
	int enemy;
	
	float xPos;
	float yPos;
	float speed;
	int face;
	
	int aggression;
	
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
	
	public Enemy(){
		this.name = "";
		this.enemy = 0;
		
		this.aggression = 1;
		
		this.face = 2;
		this.xPos = 300;
		this.yPos = 100;
		this.speed = 0.05f;
		
		try{
			setEnemy0();
		}catch(Exception e){}
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
	
	public void drawEnemy(Graphics g, boolean isWalking, boolean isAttacking){
		if(isAttacking){
			//ATTACKING
		} else if(isWalking){
			//WALKING
		} else {
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
	
	public void AIMove(float playerXPos, float playerYPos){
		playerXPos = playerXPos + 16;
		playerYPos = playerYPos + 32;
		
		if(this.aggression == 1){
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
			
			if(playerXPos < this.xPos){
				this.xPos = this.xPos - this.speed;
			}
			
			if(playerXPos > this.xPos){
				this.xPos = this.xPos + this.speed;
			}
			
			if(playerYPos < this.yPos){
				this.yPos = this.yPos - this.speed;
			}
			
			if(playerYPos > this.yPos){
				this.yPos = this.yPos + this.speed;
			}

		} 
	}
	
	
	public void setEnemy0() throws SlickException{
		this.name = "Rabite";
		this.enemy = 0;
		this.walkAnimate = true;
		
		//STILL
		//-------------------------------
		//UP
		int[] duration = {120,120,120};
		Image[] sAnimate1 = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite11.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite12.png") , new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite13.png")};
		stillAnimate1 = new Animation(sAnimate1, duration, true);
		//DOWN
		Image[] sAnimate2 = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite21.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite22.png") , new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite23.png")};
		stillAnimate2 = new Animation(sAnimate2, duration, true);
		//LEFT
		Image[] sAnimate3 = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite31.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite32.png") , new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite33.png")};
		stillAnimate3 = new Animation(sAnimate3, duration, true);
		//RIGHT
		Image[] sAnimate4 = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite41.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite42.png") , new Image("/Users/Hillary/GameDev/CS140MP2/src/images/rabite43.png")};
		stillAnimate4 = new Animation(sAnimate4, duration, true);
		//-------------------------------
		//MOVING
		//-------------------------------
		walk1 = stillAnimate1;
		walk2 = stillAnimate2;
		walk3 = stillAnimate3;
		walk4 = stillAnimate4;
		//-------------------------------
	}
	
}
