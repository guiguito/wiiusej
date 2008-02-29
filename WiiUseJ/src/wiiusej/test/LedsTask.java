package wiiusej.test;

import java.util.TimerTask;

import wiiusej.WiiUseApiManager;

public class LedsTask extends TimerTask {

	private int i = 0;
	private int inc = 1;
	
	@Override
	public void run() {
		if(i==0){
			WiiUseApiManager.getInstance().setLeds(1, true, false, false, false);
		}else if(i==1){
			WiiUseApiManager.getInstance().setLeds(1, false, true, false, false);
		}else if(i==2){
			WiiUseApiManager.getInstance().setLeds(1, false, false, true, false);
		}else if(i==3){
			WiiUseApiManager.getInstance().setLeds(1, false, false, false, true);
		}	
		if (i==0) i=1;
		if (i==3) i=-1;
		i = i+inc;		
		
	}

}

