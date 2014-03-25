import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.*;

public class Inventory {
	
	ArrayList<object> objectList; 
	int selected;
	boolean isSelected;
	Object weapon;
	boolean arrow;
	public Image smallerText;
	public Image playerImage;
	public Image bg;
	public Image inBox;
	public Image inBox2;
	public Image inBox3;
	
	public Inventory(){
		setImage();
	}
	
	public void setImage(){
		try {
			this.playerImage= new Image("images/magician.png");
			this.smallerText=new Image("images/smallertext.png");
			this.bg= new Image("images/large.png");
			this.inBox=new Image("images/inventory.png");
			this.inBox2=new Image("images/inventory2.png");
			this.inBox3=new Image("images/inventory3.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void drawObjects(Graphics g){
		g.drawImage(bg, 0, 0);
		int x=75;
		int y=50;
		for(int j=0; j<4; j++){
			for(int i=0; i<10; i++){
				if(selected==(j*10)+i){
					if(isSelected){
						g.drawImage(inBox3,x,y);
					}else{
						g.drawImage(inBox2,x,y);
					}
				}else{
					g.drawImage(inBox,x,y);
				}
				x+=50;
			}
			x=75;
			y+=50;
		}
		
	}
	
	public void drawCurrentItems(Graphics g){
		g.drawString("WEAPON", 275, 275);
		g.drawRect(350, 275, 40, 40);
		g.drawString("HEADGEAR", 400, 275);
		g.drawRect(500, 275, 40, 40);
		g.drawString("ACCESSORY", 250, 350);
		g.drawRect(350, 350, 40, 40);
		g.drawString("FOOTWEAR", 400, 350);
		g.drawRect(500, 350, 40, 40);
	}
	
	public void drawDialogue(Graphics g){
		
		g.drawImage(smallerText, 400,325);
	}
	
	public void setSelected(int s){
		this.selected=s;
	}
}
