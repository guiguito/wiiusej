package wiiusej.wiiuseapievents;

public abstract class WiiUseApiEvent {
	
	public static int GENERIC_EVENT = 1;
	public static int STATUS_EVENT = 2;
	public static int DISCONNECTION_EVENT = 3;
	
	
	/* Event Type */
	private int eventType;
	
	/* ID */
	private int wiimoteId = -1;
	
	/**
	 * Default constructor.
	 */
	public WiiUseApiEvent(){
	}
	
	/**
	 * Construct the WiiUseApiEvent setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id
	 * @param type
	 *            type of the event            
	 */
	public WiiUseApiEvent(int id, int type) {
		wiimoteId = id;
		eventType = type;
	}	
	
	
	/**
	 * Get Wiimote ID
	 * 
	 * @return the wiimote id.
	 */
	public int getWiimoteId() {
		return wiimoteId;
	}

	/**
	 * Set Wiimote ID
	 * 
	 * @param wiimoteId
	 *            id of the wiimote
	 */
	void setWiimoteId(int wiimoteId) {
		this.wiimoteId = wiimoteId;
	}

	
	
	/**
	 * Get the event type.
	 * @return the eventType
	 */
	public int getEventType() {
		return eventType;
	}

	public abstract String toString();
	

}
