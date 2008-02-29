package wiiusej.wiiuseapievents;

public abstract class WiimoteEvent {
	
	/* ID */
	private int wiimoteId = -1;
	
	/**
	 * Construct the WiiUseApiEvent setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id           
	 */
	public WiimoteEvent(int id) {
		wiimoteId = id;
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
	
	public abstract String toString();
}
