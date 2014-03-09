import org.newdawn.slick.Graphics;
import java.awt.*;
import java.util.*;

public class gameObject {

	boolean move;
	
	float renderX;
	float renderY;
	float width;
	float height;
	
	int object;
	public String objectImage;
	
	ArrayList<String> fullMessage;
	String message;
	
	Rectangle area;
	
	public gameObject(){
		this.move = false;
		
		this.renderX = 0;
		this.renderY = 0;
		
		this.fullMessage = new ArrayList<String>();
		this.message = "";
		
		this.object = 0;
		setImage();
	}
	
	public gameObject(int object){
		this.move = false;
		
		this.renderX = 0;
		this.renderY = 0;
		
		this.fullMessage = new ArrayList<String>();
		this.message = "";
		
		this.object = object;
		setImage();
	}
	
	public boolean getMove(){
		return this.move;
	}
	
	public void setRX(float renderX){
		this.renderX = renderX;
		area.setRect(new Rectangle((int)this.renderX, (int)this.renderY, (int)width, (int)height));
	}
	
	public void setRY(float renderY){
		this.renderY = renderY;
		area.setRect(new Rectangle((int)this.renderX, (int)this.renderY, (int)width, (int)height));
	}
	
	public float getRX(){
		return this.renderX;
	}
	
	public float getRY(){
		return this.renderY;
	}
	
	public void setImage(){
		if(this.object == 0){
			this.objectImage = "/Users/Hillary/GameDev/CS140MP2/src/images/sign.png";
			setObject0();
		}
	}
	
	public void setMessage(String message){
		this.message = message;
		
		String tempo = "";
		for(int j = 0; j < this.message.length(); j++){
			tempo = tempo + this.message.charAt(j);
			if((j != 0 && j % 38 == 0) || j == this.message.length() - 1){
				this.fullMessage.add(tempo);
				tempo = "";
			}
		}
	}
	
	public void setMessage(int i, String message){
		this.fullMessage.add(i,message);
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public String getMessage(int i){
		if(i >= this.fullMessage.size()){
			return "";
		}
		
		return this.fullMessage.get(i);
	}
	
	public boolean hasNext(int i){
		if(i >= this.fullMessage.size()){
			return false;
		}
		
		return true; 
	}
	
	
	void setObject0(){
		this.renderX = 209;
		this.renderY = 476;
		this.width = 40;
		this.height = 40;
		
		area = new Rectangle((int)this.renderX, (int)this.renderY, (int)width, (int)height);
	}
	
	public Rectangle getRectangle(){
		return this.area;
	}
	
	public void drawRectangle(Graphics g){
		g.drawRect(renderX, renderY, width, height);
	}
	
	//FOR OBJECT EVENTS
	public void doEvent(){
		
	}
}
