import java.util.*;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class NPC extends gameObject{
	
	int npc;
	
	public NPC(){
		this.move = true;
		
		this.renderX = 0;
		this.renderY = 0;
		
		this.fullMessage = new ArrayList<String>();
		this.message = "";
		
		this.npc = 0;
	}
	
	public void drawNPC(Graphics g){
		
	}
	
	
}
