import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Spells {
	
	Animation heal;
	
	public Spells(){
		init();
	}
	
	public void init(){
		try{
			//HEAL
			int [] duration = {100, 100, 100, 100, 100, 100, 100, 100, 100};
			Image [] healing = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb1.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb2.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb3.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb4.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb5.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb6.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb7.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb8.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb9.png")};
			heal = new Animation(healing, duration, true);
		}catch(Exception e){}
	}
	public void animateSpell(int spellNum, Graphics g, Player player, ArrayList<Enemy> enemies){
		try{
			if(spellNum == 0){
				//HEAL SELF
				heal(g, player.getXPos()-8, player.getYPos());
				if(!player.getThread().isCasting){
					player.currentHP++;
					if(player.currentHP > player.maxHP){
						player.currentHP = player.maxHP;
					}
					player.getThread().isCasting = true;
				}
				
			}
		}catch(Exception e){System.out.println(e);}
		
	}
	
	public void heal(Graphics g, float x, float y){
		heal.draw(x, y);
	}
}
