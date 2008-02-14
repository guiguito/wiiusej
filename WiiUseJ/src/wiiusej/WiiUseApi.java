package wiiusej;

import wiiusej.wiiuseapievents.EventsGatherer;

/**
 * Singleton used to manipulate WiiUse Api.
 * @author gduche
 * 
 */
public class WiiUseApi {
	
	static {
		System.loadLibrary("libWiiuseJ");
	}
	
	private static WiiUseApi instance = new WiiUseApi();
	
	/**
	 * Get the only instance of WiiUseApi.
	 * @return
	 */
	static WiiUseApi getInstance(){
		return instance;
	}	

	/**
	 * Try to connect to 2 wiimotes. Make them rumble to show they are
	 * connected.
	 * @param nb number of wiimotes to connect
	 * @return 0 if there is an error otherwise it returns the number of
	 *         wiimotes connected.
	 */	
	native int doConnections(int nb);

	/**
	 * Close connection to the wiimote with the given id.
	 * 
	 */
	native void closeConnection(int id);	
	
	/**
	 * Shutdown Wiiuse API.
	 * @return 0 if there is an error, 1 if everything is ok.
	 */
	native void shutdownApi();
	
	/**
	 * Activate rumble on the wiimote with the given id.
	 * @param id the id of the wiimote.
	 */
	native void activateRumble(int id);	
	
	/**
	 * Deactivate rumble on the wiimote with the given id.
	 * 
	 * @param id the id of the wiimote.
	 */
	native void deactivateRumble(int id);		
	
	/**
	 * Activate IR Tracking on the wiimote with the given id.
	 * @param id the id of the wiimote.
	 */
	native void activateIRTracking(int id);
	
	/**
	 * Deactivate IR Tracking on the wiimote with the given id.
	 * @param id the id of the wiimote.
	 */
	native void deactivateIRTracking(int id);
	
	/**
	 * Activate motion sensing on the wiimote with the given id.
	 * @param id the id of the wiimote.
	 */
	native void activateMotionSensing(int id);
	
	/**
	 * Deactivate motion sensing on the wiimote with the given id.
	 * @param id the id of the wiimote.
	 */
	native void deactivateMotionSensing(int id);
	 
	/**
	 * Set wiimote leds status.
	 * @param id the id of the wiimote concerned
	 * @param led1 status of led1: True=ON, False=OFF
	 * @param led2 status of led2: True=ON, False=OFF
	 * @param led3 status of led3: True=ON, False=OFF
	 * @param led4 status of led4: True=ON, False=OFF
	 */
	native void setLeds(int id, boolean led1, boolean led2, boolean led3, boolean led4);
	
	/**
	 * Set how many degrees an angle must change to generate an event.
	 * @param id id of the wiimote concerned
	 * @param angle minimum angle detected by an event
	 */
	native void setOrientThreshold(int id, float angle);
	
	/**
	 * Set how much acceleration must change to generate an event.
	 * @param id id of the wiimote concerned
	 * @param value minimum value detected by an event
	 */
	native void setAccelThreshold(int id, float value);
	
	/**
	 * Set alpha smoothing parameter for the given id.
	 * @param id id of the wiimote concerned
	 * @param value alpha smoothing value
	 */
	native void setSmoothAlpha(int id, float value);
	
	/**
	 * Try to resync with the wiimote by starting a new handshake.
	 * @param id id of the wiimote concerned
	 */
	native void reSync(int id);
	
	/**
	 * Make the the accelerometers give smoother results.
	 * This is set by default.
	 * @param id the id of the wiimote concerned
	 */
    native void activateSmoothing(int id);
    
    /**
	 * Make the the accelerometers give raw results.
	 * @param id the id of the wiimote concerned
	 */
    native void deactivateSmoothing(int id);
    
    /**
     * Make the wiimote generate an event each time we poll.
     * Not set by default.
     * @param id the id of the wiimote concerned
     */
    native void activateContinuous(int id);
    
    /**
     * Make the wiimote generate an event only when there is one.
     * @param id the id of the wiimote concerned
     */
    native void deactivateContinuous(int id);
	
	/**
	 * Get status and values from the wiimotes and send it through callbacks.
	 * 
	 * @param id the id of the wiimote of which we want the status.
	 */
	native void getStatus(int id);	
	
	/**
	 * Check for new Events and Get it.
	 * 
	 * @param gath the object where we store all the new events.
	 */
	native void specialPoll(EventsGatherer gath);	   
    
	
	/* Tests */
	public static void main(String[] args) {

		/* Test JNI Side */
		/*
		WiiUseApi manager = new WiiUseApi();

		int value = manager.loadLibrary();
		System.out.println("loadLibrary : " + value);

		value = manager.doConnections();
		System.out.println("doConnections : " + value);  
		 WiiMoteEvent mote = new WiiMoteEvent();		
		 

		manager.getStatus(1);
		System.out.println("Status : \n" + mote);

		System.out.println("");
		System.out.println("!!!!!!!!!!!!!! Polling !!!!!!!!!");
		while (true) {
			manager.specialPoll(mote);
			System.out.println(mote);
			mote.EmptyIRPoints();
		}
		 */
		// manager.closeConnectionsAndShutDown();
	}

}
