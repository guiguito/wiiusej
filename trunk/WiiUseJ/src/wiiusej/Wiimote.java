package wiiusej;

import javax.swing.event.EventListenerList;

/**
 * Class that represents a wiimote.
 * You can register as an observer of this wiimote to listen events from it.
 * You manage it.
 * @author gduche
 *
 */
public class Wiimote implements WiiUseApiListener {

	private int id;//wiimote id
	
	private EventListenerList listeners = new EventListenerList();
	
	private WiiUseApiManager manager;
	
	
	/**
	 * Contructor.
	 * @param idd id of the wiimote
	 * @param manager manager wo built it.
	 */
	public Wiimote(int idd, WiiUseApiManager manager){
		id = idd;
		this.manager = manager;
	}
	
	/**
	 * Disconnect this wiimote.
	 */
	public void disconnect(){
		deactivateIRTRacking();
		deactivateMotionSensing();
		deactivateRumble();
		manager.closeConnection(id);
	}
	
	/**
	 * Activate the rumble.
	 */
	public void activateRumble() {
		manager.activateRumble(id);
	}

	/**
	 * Deactivate the rumble.
	 */
	public void deactivateRumble() {
		manager.deactivateRumble(id);
	}
	
	/**
	 * Activate IR Tracking.
	 */
	public void activateIRTRacking() {
		manager.activateIRTRacking(id);
	}

	/**
	 * Deactivate IR Tracking.
	 */
	public void deactivateIRTRacking() {
		manager.deactivateIRTRacking(id);
	}
	
	/**
	 * Activate motion sensing.
	 */
	public void activateMotionSensing() {
		manager.activateMotionSensing(id);
	}

	/**
	 * Deactivate motion sensing.
	 */
	public void deactivateMotionSensing() {
		manager.deactivateMotionSensing(id);
	}
	
	/**
	 * Activate smoothing.
	 */
	public void activateSmoothing() {
		manager.activateSmoothing(id);
	}

	/**
	 * Deactivate smoothing.
	 */
	public void deactivateSmoothing() {
		manager.deactivateSmoothing(id);
	}

	/**
	 * Activate continuous.
	 */
	public void activateContinuous() {
		manager.activateContinuous(id);
	}

	/**
	 * Deactivate continuous.
	 */
	public void deactivateContinuous() {
		manager.deactivateContinuous(id);
		
	}
	
	/**
	 * Set leds status.
	 * 
	 * @param l1
	 *            status of led1. True : ON, False : OFF
	 * @param l2
	 *            status of led2. True : ON, False : OFF
	 * @param l3
	 *            status of led3. True : ON, False : OFF
	 * @param l4
	 *            status of led4. True : ON, False : OFF
	 */
	public void setLeds(boolean l1, boolean l2, boolean l3, boolean l4) {
		manager.setLeds(id, l1, l2, l3, l4);
	}

	/**
	 * Set the orientation threshold (minimum angle between two degrees with accelerometer).
	 * @param th
	 *            threshold in degrees
	 */
	public void setOrientationThreshold(float th) {
		manager.setOrientationThreshold(id,th);
	}

	/**
	 * Get Status of the wiimote.
	 */
	public void getStatus() {
		manager.getStatus(id);
	}
	
	
	@Override
	public void wiimoteEvent(WiiMoteEvent e) {		
		if (e.getWiimoteId() == id){
			notifyWiiMoteEventListeners(e);
		}
	}
			
	/**
	 * Add a WiimoteListener to the listeners list.
	 * @param listener a WiimoteListener
	 */
	public void addWiiMoteEventListeners(WiimoteListener listener) {
		listeners.add(WiimoteListener.class, listener);
	}

	/**
	 * Remove a WiimoteListener from the listeners list.
	 * @param listener a WiimoteListener
	 */
	public void removeWiiMoteEventListeners(WiimoteListener listener) {
		listeners.remove(WiimoteListener.class, listener);
	}

	/**
	 * Get the list of WiimoteListener.
	 * @return the list of WiimoteListener.
	 */
	public WiimoteListener[] getWiiMoteEventListeners() {
		return listeners.getListeners(WiimoteListener.class);
	}

	/**
	 * Notify WiimoteListener that an event occured.
	 * @param evt WiimoteEvent occured
	 */
	private void notifyWiiMoteEventListeners(WiiMoteEvent evt) {		
		for (WiimoteListener listener : getWiiMoteEventListeners()) {			
			listener.wiimoteEvent(evt);
		}
	}
	
	@Override
	public String toString() {		
		return "Wiimote with ID : "+id;
	}

}
