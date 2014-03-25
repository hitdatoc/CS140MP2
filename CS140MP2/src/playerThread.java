
public class playerThread extends Thread{
	
	int[] sleeptime;
	boolean isCasting;
	
	public playerThread(){
		this.isCasting = false;
		this.sleeptime = new int[5];
		for(int i = 0; i < 5; i++){
			sleeptime[i] = 0;
		}
	}
	
	public void run(){
		while(true){
			if(sleeptime[1] > 0 || sleeptime[2] > 0 || sleeptime [3] > 0 || sleeptime[4] > 0){
				toSleep(1);
			}
			
			if(sleeptime[1] > 0){
				sleeptime[1] = sleeptime[1] - 1;
			} else if(sleeptime[1] < 0){
				sleeptime[1] = 0;
			}
			
			if(sleeptime[2] > 0){
				sleeptime[2] = sleeptime[2] - 1;
			} else if(sleeptime[2] < 0){
				sleeptime[2] = 0;
			}
			
			if(sleeptime[3] > 0){
				sleeptime[3] = sleeptime[3] - 1;
			} else if(sleeptime[3] < 0){
				sleeptime[3] = 0;
			}

			if(sleeptime[4] > 0){
				sleeptime[4] = sleeptime[4] - 1;
			} else if(sleeptime[4] < 0){
				sleeptime[4] = 0;
			}
			
			if(isCasting){
				this.toSleep(3000);
				this.isCasting = false;
			}
		}
	}
	
	public void setSleepTime(int direction, int time){
		this.sleeptime[direction] = time;
	}
	
	public int getSleepTime(int direction){
		return this.sleeptime[direction];
	}
	
	public void toSleep(int miliseconds){
		try{
			Thread.sleep(miliseconds);
		}catch(Exception e){}
	}

}
