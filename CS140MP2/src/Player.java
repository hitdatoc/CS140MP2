public class Player {
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
	
	int currentHP;
	int maxHP;
	
	public Player(){
		this.xPos = 100;
		this.yPos = 50;
		this.face = 2;
		
		this.gender = true;
		
		this.combo = new int[5];
		for(int i = 0; i < 5; i++){
			this.combo[i] = 0;
		}
		
		set();
	}
	
	public void set(){
		if(this.gender){
			//FEMALE
		
			//STILL
			StillUp = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleF1.gif";
			StillDown = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleF2.gif";
			StillLeft = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleF3.gif";
			StillRight = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleF4.png";
			
			//WALKING
			MoveDown = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF2-1.png";
			MoveDown2 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF2-2.png";
			MoveDown3 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF2-3.png";
			MoveDown4 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF2-4.png";
			MoveDown5 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF2-5.png";
			MoveDown6 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF2-6.png";
			MoveUp = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF1-1.png";
			MoveUp2 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF1-2.png";
			MoveUp3 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF1-3.png";
			MoveUp4 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF1-4.png";
			MoveUp5 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF1-5.png";
			MoveUp6 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF1-6.png";
			MoveLeft = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF3-1.png";
			MoveLeft2 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF3-2.png";
			MoveLeft3 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF3-3.png";
			MoveLeft4 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF3-4.png";
			MoveLeft5 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF3-5.png";
			MoveLeft6 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF3-6.png";
			MoveRight = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF4-1.png";
			MoveRight2 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF4-2.png";
			MoveRight3 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF4-3.png";
			MoveRight4 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF4-4.png";
			MoveRight5 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF4-5.png";
			MoveRight6 = "/Users/Hillary/GameDev/CS140MP2/src/images/sampleWalkF4-6.png";
			
			//ATTACKING
			Attack1 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack1.png";
			Attack1_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack1_2.png";
			Attack1_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack1_3.png";
			Attack1_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack1_4.png";
			Attack12 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF12_1.png";
			Attack12_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF12_2.png";
			Attack12_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF12_3.png";
			Attack12_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF12_4.png";
			Attack13 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF13_1.png";
			Attack13_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF13_2.png";
			Attack13_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF13_3.png";
			Attack13_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF13_4.png";
			Attack2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack2.png";
			Attack2_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack2_2.png";
			Attack2_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack2_3.png";
			Attack2_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack2_4.png";
			Attack22 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF22_1.png";
			Attack22_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF22_2.png";
			Attack22_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF22_3.png";
			Attack22_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF22_4.png";
			Attack23 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF23_1.png";
			Attack23_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF23_2.png";
			Attack23_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF23_3.png";
			Attack23_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF23_4.png";
			Attack3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack3.png";
			Attack3_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack3_2.png";
			Attack3_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack3_3.png";
			Attack3_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack3_4.png";
			Attack32 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF32_1.png";
			Attack32_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF32_2.png";
			Attack32_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF32_3.png";
			Attack32_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF32_4.png";
			Attack33 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF33_1.png";
			Attack33_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF33_2.png";
			Attack33_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF33_3.png";
			Attack33_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF33_4.png";
			Attack4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack4.png";
			Attack4_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack4_2.png";
			Attack4_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack4_3.png";
			Attack4_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attack4_4.png";
			Attack42 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF42_1.png";
			Attack42_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF42_2.png";
			Attack42_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF42_3.png";
			Attack42_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF42_4.png";
			Attack43 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF43_1.png";
			Attack43_2 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF43_2.png";
			Attack43_3 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF43_3.png";
			Attack43_4 = "/Users/Hillary/GameDev/CS140MP2/src/images/attackF43_4.png";
						
		} else {
			//MALE
		}
		
		attackSFX1 = "/Users/Hillary/GameDev/CS140MP2/src/music/atk-hit1.ogg";
		attackSFX2 = "/Users/Hillary/GameDev/CS140MP2/src/music/atk-hit2.ogg";
		attackSFX3 = "/Users/Hillary/GameDev/CS140MP2/src/music/atk-hit3-withslash.ogg";
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
	
	public int getFace(){
		return this.face;
	}
	
	public void setFace(int face){
		this.face = face;
		
		for(int i = 0; i < 5; i++){
			if(i != face){
				combo[i] = 0;
			}
		}
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
