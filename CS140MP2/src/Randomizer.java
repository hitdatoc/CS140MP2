import java.util.Random;


public class Randomizer extends Thread {
	
	Random ran;
	int x;
	int y;
	volatile boolean needSleep;
	int sleeptime;
	
	public Randomizer(){
		ran= new Random();
	}
	
	public void run(){
		while(true){
			if(needSleep){
				toSleep(3000);
				this.needSleep=false;
			}else{
				x=ran.nextInt(640);
				y=ran.nextInt(480);
				this.needSleep=true;
			}
		}
	}

	public void setSleepTime(int sleeptime){
		this.sleeptime = sleeptime;
	}
	
	public void setNeedSleep(boolean needSleep){
		this.needSleep = needSleep;
	}
	
	public boolean getNeedSleep(){
		return this.needSleep;
	}
	
	public void toSleep(int miliseconds){
		try{
			Thread.sleep(miliseconds);
		}catch(Exception e){}
	}
}
