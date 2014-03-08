
public class soundThread extends Thread{
	
	volatile boolean needSleep;
	int sleeptime;
	
	public soundThread(){
		this.needSleep = false;
		this.sleeptime = 0;
	}
	
	public void run(){
		while(true){
			if(needSleep){
				toSleep(sleeptime);
				needSleep = false;
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
