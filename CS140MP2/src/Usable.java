public class Usable extends Items{

	int plusHp;
	int plusMana;
	int resu;
	
	public Usable(){}
	
	public Usable(String name, int itemClass, int plusHp, int plusMana, int resu){
		this.name = name;
		this.itemClass = itemClass;
		this.plusHp = plusHp;
		this.plusMana = plusMana;
		this.resu = resu;
	}
	
	public int getPlusHp() {
		return plusHp;
	}
	public void setPlusHp(int plusHp) {
		this.plusHp = plusHp;
	}
	public int getPlusMana() {
		return plusMana;
	}
	public void setPlusMana(int plusMana) {
		this.plusMana = plusMana;
	}
	public int getResu() {
		return resu;
	}
	public void setResu(int resu) {
		this.resu = resu;
	}
	
	
}
