import java.awt.*;
import java.util.*;

import org.newdawn.slick.Graphics;

public class Player {
	String name;
	
	float xPos;
	float yPos;
	int face; //UP - 1, DOWN - 2, LEFT - 3, RIGHT - 4
	
	boolean gender; //FEMALE - True, MALE - False
	
	int[] combo;
	
	public String StillUp;
	public String StillDown;
	public String StillLeft;
	public String StillRight;
	public String MoveDown;
	public String MoveDown2;
	public String MoveDown3;
	public String MoveDown4;
	public String MoveDown5;
	public String MoveDown6;
	public String MoveUp;
	public String MoveUp2;
	public String MoveUp3;
	public String MoveUp4;
	public String MoveUp5;
	public String MoveUp6;
	public String MoveLeft;
	public String MoveLeft2;
	public String MoveLeft3;
	public String MoveLeft4;
	public String MoveLeft5;
	public String MoveLeft6;
	public String MoveRight;
	public String MoveRight2;
	public String MoveRight3;
	public String MoveRight4;
	public String MoveRight5;
	public String MoveRight6;
	public String Attack1;
	public String Attack1_2;
	public String Attack1_3;
	public String Attack1_4;
	public String Attack12;
	public String Attack12_2;
	public String Attack12_3;
	public String Attack12_4;
	public String Attack13;
	public String Attack13_2;
	public String Attack13_3;
	public String Attack13_4;
	public String Attack2;
	public String Attack2_2;
	public String Attack2_3;
	public String Attack2_4;
	public String Attack22;
	public String Attack22_2;
	public String Attack22_3;
	public String Attack22_4;
	public String Attack23;
	public String Attack23_2;
	public String Attack23_3;
	public String Attack23_4;
	public String Attack3;
	public String Attack3_2;
	public String Attack3_3;
	public String Attack3_4;
	public String Attack32;
	public String Attack32_2;
	public String Attack32_3;
	public String Attack32_4;
	public String Attack33;
	public String Attack33_2;
	public String Attack33_3;
	public String Attack33_4;
	public String Attack4;
	public String Attack4_2;
	public String Attack4_3;
	public String Attack4_4;
	public String Attack42;
	public String Attack42_2;
	public String Attack42_3;
	public String Attack42_4;
	public String Attack43;
	public String Attack43_2;
	public String Attack43_3;
	public String Attack43_4;
	public String attackSFX1;
	public String attackSFX2;
	public String attackSFX3;
	
	Rectangle rect;
	Rectangle attackRect;
	
	int level;
	
	int str;
	int agi;
	int intl;
	int initDmg;
	
	int currentHP;
	int maxHP;
	boolean isAlive;
	
	int currentMana;
	int maxMana;
	
	playerThread pThread;
	
	public Player(){
		this.name = "Angela";
		
		this.xPos = 100;
		this.yPos = 50;
		this.face = 2;
		
		this.gender = true;
		
		this.str = 10;
		this.agi = 10;
		this.intl = 10;
		this.initDmg = 20;
		
		this.level = 1;
		this.isAlive = true;
		
		this.maxHP = str*10;
		this.maxMana = intl*10;
		
		this.currentHP = maxHP;
		this.currentMana = maxMana;
		
		this.combo = new int[5];
		for(int i = 0; i < 5; i++){
			this.combo[i] = 0;
		}
		
		this.rect = new Rectangle((int)xPos, (int)yPos, 32, 64);
		this.attackRect = new Rectangle((int)(this.xPos - 55f), (int)(this.yPos - 35f), 150, 150);
		
		set();
	}
	
	public void updateStats(boolean agi){
		int addHP = str*10 - maxHP;
		int addMP = intl*10 - maxMana;
		
		this.currentHP = currentHP + addHP;
		this.currentMana = currentMana + addMP;
		
		this.maxHP = str*10;
		this.maxMana = intl*10;
		
		this.initDmg = initDmg + 5;
		if(agi){
			initDmg = initDmg + 5;
		}
	}
	
	public void set(){
		if(this.gender){
			//FEMALE
		
			//STILL
			StillUp = "images/sampleF1.gif";
			StillDown = "images/sampleF2.gif";
			StillLeft = "images/sampleF3.gif";
			StillRight = "images/sampleF4.png";
			
			//WALKING
			MoveDown = "images/sampleWalkF2-1.png";
			MoveDown2 = "images/sampleWalkF2-2.png";
			MoveDown3 = "images/sampleWalkF2-3.png";
			MoveDown4 = "images/sampleWalkF2-4.png";
			MoveDown5 = "images/sampleWalkF2-5.png";
			MoveDown6 = "images/sampleWalkF2-6.png";
			MoveUp = "images/sampleWalkF1-1.png";
			MoveUp2 = "images/sampleWalkF1-2.png";
			MoveUp3 = "images/sampleWalkF1-3.png";
			MoveUp4 = "images/sampleWalkF1-4.png";
			MoveUp5 = "images/sampleWalkF1-5.png";
			MoveUp6 = "images/sampleWalkF1-6.png";
			MoveLeft = "images/sampleWalkF3-1.png";
			MoveLeft2 = "images/sampleWalkF3-2.png";
			MoveLeft3 = "images/sampleWalkF3-3.png";
			MoveLeft4 = "images/sampleWalkF3-4.png";
			MoveLeft5 = "images/sampleWalkF3-5.png";
			MoveLeft6 = "images/sampleWalkF3-6.png";
			MoveRight = "images/sampleWalkF4-1.png";
			MoveRight2 = "images/sampleWalkF4-2.png";
			MoveRight3 = "images/sampleWalkF4-3.png";
			MoveRight4 = "images/sampleWalkF4-4.png";
			MoveRight5 = "images/sampleWalkF4-5.png";
			MoveRight6 = "images/sampleWalkF4-6.png";
			
			//ATTACKING
			Attack1 = "images/attack1.png";
			Attack1_2 = "images/attack1_2.png";
			Attack1_3 = "images/attack1_3.png";
			Attack1_4 = "images/attack1_4.png";
			Attack12 = "images/attackF12_1.png";
			Attack12_2 = "images/attackF12_2.png";
			Attack12_3 = "images/attackF12_3.png";
			Attack12_4 = "images/attackF12_4.png";
			Attack13 = "images/attackF13_1.png";
			Attack13_2 = "images/attackF13_2.png";
			Attack13_3 = "images/attackF13_3.png";
			Attack13_4 = "images/attackF13_4.png";
			Attack2 = "images/attack2.png";
			Attack2_2 = "images/attack2_2.png";
			Attack2_3 = "images/attack2_3.png";
			Attack2_4 = "images/attack2_4.png";
			Attack22 = "images/attackF22_1.png";
			Attack22_2 = "images/attackF22_2.png";
			Attack22_3 = "images/attackF22_3.png";
			Attack22_4 = "images/attackF22_4.png";
			Attack23 = "images/attackF23_1.png";
			Attack23_2 = "images/attackF23_2.png";
			Attack23_3 = "images/attackF23_3.png";
			Attack23_4 = "images/attackF23_4.png";
			Attack3 = "images/attack3.png";
			Attack3_2 = "images/attack3_2.png";
			Attack3_3 = "images/attack3_3.png";
			Attack3_4 = "images/attack3_4.png";
			Attack32 = "images/attackF32_1.png";
			Attack32_2 = "images/attackF32_2.png";
			Attack32_3 = "images/attackF32_3.png";
			Attack32_4 = "images/attackF32_4.png";
			Attack33 = "images/attackF33_1.png";
			Attack33_2 = "images/attackF33_2.png";
			Attack33_3 = "images/attackF33_3.png";
			Attack33_4 = "images/attackF33_4.png";
			Attack4 = "images/attack4.png";
			Attack4_2 = "images/attack4_2.png";
			Attack4_3 = "images/attack4_3.png";
			Attack4_4 = "images/attack4_4.png";
			Attack42 = "images/attackF42_1.png";
			Attack42_2 = "images/attackF42_2.png";
			Attack42_3 = "images/attackF42_3.png";
			Attack42_4 = "images/attackF42_4.png";
			Attack43 = "images/attackF43_1.png";
			Attack43_2 = "images/attackF43_2.png";
			Attack43_3 = "images/attackF43_3.png";
			Attack43_4 = "images/attackF43_4.png";
						
		} else {
			//MALE
		}
		
		attackSFX1 = "music/atk-hit1.ogg";
		attackSFX2 = "music/atk-hit2.ogg";
		attackSFX3 = "music/atk-hit3-withslash.ogg";
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setRectangle(){
		rect.setRect(new Rectangle((int)this.xPos, (int)this.yPos, 32, 64));
	}
	
	public Rectangle getRectangle(){
		return this.rect;
	}
	
	
	public void drawRectangle(Graphics g){
		g.drawRect(this.xPos, this.yPos, 32, 64);
	}
	
	public void setARectangle(){
		attackRect.setRect(new Rectangle((int)(this.xPos - 55f), (int)(this.yPos - 35f), 150, 150));
	}
	
	public Rectangle getARectangle(){
		return this.attackRect;
	}
	
	public void drawARectangle(Graphics g){
		g.drawRect((int)(this.xPos - 55f), (int)(this.yPos - 35f), 150, 150);
	}
	
	public float getXPos(){
		return this.xPos;
	}
	
	public float getYPos(){
		return this.yPos;
	}
	
	public void setXPos(float xPos){
		this.xPos = xPos;
		setRectangle();
		setARectangle();
	}
	
	public void setYPos(float yPos){
		this.yPos = yPos;
		setRectangle();
		setARectangle();
	}
	
	public int getFace(){
		return this.face;
	}
	
	public void setThread(playerThread pThread){
		this.pThread = pThread;
	}
	
	public playerThread getThread(){
		return this.pThread;
	}
	
	public void setFace(int face){
		this.face = face;
		
		for(int i = 0; i < 5; i++){
			if(i != face){
				combo[i] = 0;
			}
		}
	}
	
	
	public boolean hitbox(Rectangle enemyRect){
		Rectangle rectum = new Rectangle(1,1,1,1);
		if(this.face == 1){
			if(this.combo[1] == 0){
				rectum = new Rectangle((int)(this.xPos), (int)(this.yPos), 64, 64);
			} else if(this.combo[1] == 1){
				rectum = new Rectangle((int)(this.xPos-16), (int)(this.yPos-16), 64, 32);
			} else if(this.combo[1] == 2){
				rectum = new Rectangle((int)(this.xPos), (int)(this.yPos-16), 32, 48);
			}
		} else if(this.face == 2){
			if(this.combo[2] == 0){
				rectum = new Rectangle((int)(this.xPos), (int)(this.yPos+32), 64, 50);
			} else if(this.combo[2] == 1){
				rectum = new Rectangle((int)(this.xPos+16), (int)(this.yPos), 32, 80);
			} else if(this.combo[2] == 2){
				rectum = new Rectangle((int)(this.xPos), (int)(this.yPos+32), 32, 48);
			}
		} else if(this.face == 3){
			if(this.combo[3] == 0){
				rectum = new Rectangle((int)(this.xPos-8), (int)(this.yPos+48), 32, 32);
			} else if(this.combo[3] == 1){
				rectum = new Rectangle((int)(this.xPos-16), (int)(this.yPos), 32, 64);
			} else if(this.combo[3] == 2){
				rectum = new Rectangle((int)(this.xPos-16), (int)(this.yPos), 32, 72);
			}
		} else if(this.face == 4){
			if(this.combo[4] == 0){
				rectum = new Rectangle((int)(this.xPos+32), (int)(this.yPos+48), 32, 32);
			} else if(this.combo[4] == 1){
				rectum = new Rectangle((int)(this.xPos+32), (int)(this.yPos), 32, 64);
			} else if(this.combo[4] == 2){
				rectum = new Rectangle((int)(this.xPos+32), (int)(this.yPos), 32, 64);
			}
		}
		
		if(rectum.intersects(enemyRect)){
			return true;
		}
		
		return false;
	}
	
	public int attackDmg(){
		Random random = new Random();
		float randomInt = (float)(random.nextInt(10+(agi*2)) + 1);
		System.out.println("PDAMAGE: " + this.initDmg + " PFINAL DAMAGE: " + (int)(this.initDmg + (this.initDmg*(randomInt/100))) + " PRANDOM: " + randomInt);
		return (int)(this.initDmg + (this.initDmg*(randomInt/100)));
	}
	
	public void receiveDmg(int damage){
		Random random = new Random();
		float randomInt = (float)(random.nextInt(10+str) + 1);
		System.out.println("DAMAGE: " + damage*(randomInt/100) + " FINAL DAMAGE: " + (int)(damage - (damage*(randomInt/100))) + " RANDOM: " + randomInt);
		this.currentHP = currentHP - (int)(damage - damage*(randomInt/100));
		if(this.currentHP <= 0){
			this.currentHP = 0;
			this.isAlive = false;
		}
		//System.out.println("NEW HP: " + currentHP);
	}
	
	public void setCombo(int direction, int x){
		this.combo[direction] = x;
		
		if(combo[direction] >= 3){
			combo[direction] = 0;
		}
	}
	
	public int getCombo(int direction){
		return this.combo[direction];
	}
	
}
