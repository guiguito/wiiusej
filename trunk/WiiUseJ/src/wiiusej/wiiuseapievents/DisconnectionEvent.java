package wiiusej.wiiuseapievents;

public class DisconnectionEvent extends WiiUseApiEvent {

	/**
	 * Construct the DisconnectionEvent setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id
	 */
	public DisconnectionEvent(int id) {
		super(id,WiiUseApiEvent.DISCONNECTION_EVENT);
	}
		
	@Override
	public String toString() {
		String out = "";
		/* Status */
		out += "/*********** DISCONNECTION EVENT : WIIMOTE   ID :" + super.getWiimoteId() + " ********/\n";

		return out;
	}

}
