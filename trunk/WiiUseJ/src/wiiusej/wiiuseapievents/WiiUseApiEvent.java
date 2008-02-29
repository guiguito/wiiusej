package wiiusej.wiiuseapievents;

public abstract class WiiUseApiEvent extends WiimoteEvent{
	
	public static int GENERIC_EVENT = 1;
	public static int STATUS_EVENT = 2;
	public static int DISCONNECTION_EVENT = 3;	
	
	/* Event Type */
	private int eventType;
	
	/**
	 * Construct the WiiUseApiEvent setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id
	 * @param type
	 *            type of the event            
	 */
	public WiiUseApiEvent(int id, int type) {
		super(id);
		eventType = type;
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
