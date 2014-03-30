import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Items {

	String name;
	Image img;
	//0 - usable, 1 - equipable
	int itemClass;
	
	public Items(){}

	public Items(String name, int itemClass){
		this.name = name;
		this.itemClass = itemClass;
	}
	
	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	public void setImgPath(String path){
		try {
			this.img = new Image(path);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItemClass() {
		return itemClass;
	}

	public void setItemClass(int itemClass) {
		this.itemClass = itemClass;
	}
	
	
}
