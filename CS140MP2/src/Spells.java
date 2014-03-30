import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Spells {
	
	Animation heal;
	Animation fireSpell;
	
	public Spells(){
		init();
	}
	
	public void init(){
		try{
			//HEAL
			int [] duration = {100, 100, 100, 100, 100, 100, 100, 100, 100};
			Image [] healing = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb1.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb2.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb3.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb4.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb5.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb6.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb7.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb8.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/blueorb9.png")};
			heal = new Animation(healing, duration, true);
			
			//FIRE SPELL
			int[] durationFire = {150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150,150};
			Image [] fire = {new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell1.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell2.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell3.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell4.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell5.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell6.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell7.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell8.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell9.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell10.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell11.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell12.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell13.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell14.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell15.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell16.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell17.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell18.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell19.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell5.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell4.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell3.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell2.png"), new Image("/Users/Hillary/GameDev/CS140MP2/src/images/spells/firespell1.png")};
			fireSpell = new Animation(fire, durationFire, true);
		}catch(Exception e){System.out.println(e);}
	}
	
	public void animateSpell(int spellNum, Graphics g, Player player, ArrayList<Enemy> enemies){
		try{
			fireSpell.restart();
			
			if(spellNum == 0){
				//HEAL SELF
				heal(g, player.getXPos()-8, player.getYPos());
				if(!player.getThread().isCasting){
					player.currentHP = player.currentHP + player.str/10;
					player.currentMana = player.currentMana - player.intl/10;
					if(player.currentHP > player.maxHP){
						player.currentHP = player.maxHP;
					}
					player.getThread().isCasting = true;
				}
				
			} else if(spellNum == 1){
				if(!player.getThread().isCasting){
					player.currentMana = player.currentMana - player.intl;
					
					for(int i = 0; i < enemies.size(); i++){
						enemies.get(i).currentHP = enemies.get(i).currentHP - (2*(player.intl));
					}
					
					player.getThread().isCasting = true;
				}
				
				
				
				//fireSpell.restart();
			}
		}catch(Exception e){}
		
	}
	
	public void heal(Graphics g, float x, float y){
		heal.draw(x, y);
	}
	
	public void fireSpell(){
		fireSpell.draw();
	}
}
