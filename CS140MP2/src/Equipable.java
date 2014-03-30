public class Equipable extends Items{

	int plusDmg;
	int plusStr;
	int plusAgi;
	int plusInt;
	
	int type;
	boolean equipped;
	
	public Equipable(){}
	
	public Equipable(String name, int itemClass, int plusDmg, int plusStr, int plusAgi, int plusInt, int type){
		this.name = name;
		this.itemClass = itemClass;
		this.plusDmg = plusDmg;
		this.plusAgi = plusAgi;
		this.plusInt = plusInt;
		this.type=type;
		this.equipped=false;
	}
	
	public int getPlusDmg() {
		return plusDmg;
	}
	public void setPlusDmg(int plusDmg) {
		this.plusDmg = plusDmg;
	}
	public int getPlusStr() {
		return plusStr;
	}
	public void setPlusStr(int plusStr) {
		this.plusStr = plusStr;
	}
	public int getPlusAgi() {
		return plusAgi;
	}
	public void setPlusAgi(int plusAgi) {
		this.plusAgi = plusAgi;
	}
	public int getPlusInt() {
		return plusInt;
	}
	public void setPlusInt(int plusInt) {
		this.plusInt = plusInt;
	}
	
}
