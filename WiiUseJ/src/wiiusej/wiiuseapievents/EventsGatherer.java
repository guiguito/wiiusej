package wiiusej.wiiuseapievents;

import tests.Tests;

/**
 * Gather events in a call to the Wiiuse API.
 * 
 * @author gduche
 * 
 */
public class EventsGatherer {

	private WiiUseApiEvent[] events;
	private int index = 0;
	private WiiMoteEvent genericEvent = null;

	/**
	 * Create EventsGatherer.
	 * 
	 * @param nbWiimotes
	 *            nb wiimotes (nb a of events possible in a call to Wiiuse API)
	 */
	public EventsGatherer(int nbWiimotes) {
		events = new WiiUseApiEvent[nbWiimotes];
	}

	/**
	 * Add an event to the array.
	 * 
	 * @param e
	 *            the event to add.
	 */
	private void addEvent(WiiUseApiEvent e) {
		events[index] = e;
		index++;
	}

	/**
	 * Prepare a wiimote event to add.
	 * 
	 * @param id
	 *            id of the wiimote.
	 * @param buttonsJustPressed
	 *            buttons just pressed
	 * @param buttonsJustReleased
	 *            buttons just released
	 * @param buttonsHeld
	 *            buttons held
	 */
	public void prepareWiiMoteEvent(int id, short buttonsJustPressed,
			short buttonsJustReleased, short buttonsHeld) {
		genericEvent = new WiiMoteEvent(id, buttonsJustPressed,
				buttonsJustReleased, buttonsHeld);
	}

	/**
	 * Add an IR point to the WiiMoteEvent prepared
	 * 
	 * @param x
	 *            x coordinates
	 * @param y
	 *            y coordinates
	 */
	public void addIRPointToPreparedWiiMoteEvent(int x, int y) {
		if (genericEvent != null) {
			genericEvent.addIRpoint(x, y);
		}
	}
	
	/**
	 * Set orientation and gravity force of the prepared event.
	 * 
	 * @param r
	 *            roll
	 * @param p
	 *            pitch
	 * @param ya
	 *            yaw
	 * @param x
	 *            gravity force on x axis
	 * @param y
	 *            gravity force on y axis
	 * @param z
	 *            gravity force on z axis
	 */
	public void addMotionSensingValues(float r, float p, float ya, float x,
			float y, float z) {
		if (genericEvent != null) {
			genericEvent.setOrientationAndGforce(r, p, ya, x, y, z);
		}
	}

	/**
	 * Add the prepared WiimoteEvent to the gatherer.
	 */
	public void addWiimoteEvent() {
		if (genericEvent != null) {
			addEvent(genericEvent);
			genericEvent = null;
		}
	}

	/**
	 * Add a StatusEvent to the gatherer.
	 * 
	 * @param id
	 *            id of the wiimote
	 * @param connect
	 *            true if the wiimote is connected
	 * @param batt
	 *            battery level
	 * @param led
	 *            status of leds
	 * @param speak
	 *            speakers status
	 * @param attach
	 *            attachment status
	 * @param rumbleState
	 *            true if rumble is active
	 * @param orientationThreshold
	 *            value of the minimum angle between two events with the
	 *            accelerometer
	 * @param accelerationThreshold
	 *            value of the value variation between two events with the
	 *            accelerometer
	 * @param alphaSmooth
	 *            value of the alpha smoothing parameter
	 * @param continuousState
	 *            true if continuous flag is activated
	 * @param smoothingState
	 *            true if smoothing flag is activated
	 * @param irState
	 *            true if ir is active
	 * @param motionSensingState
	 *            true if accelerometer is active
	 */
	public void addStatusEvent(int id, boolean connect, float batt, short led,
			boolean speak, int attach, boolean rumbleState,
			float orientationThreshold, float accelerationThreshold,
			float alphaSmooth, boolean continuousState, boolean smoothingState,
			boolean irState, boolean motionSensingState) {
		StatusEvent evt = new StatusEvent(id, connect, batt, led, speak,
				attach, rumbleState, orientationThreshold,
				accelerationThreshold, alphaSmooth, continuousState,
				smoothingState, irState, motionSensingState);
		addEvent(evt);
	}

	/**
	 * Add a DisconnectionEvent to the gatherer.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void addDisconnectionEvent(int id) {
		DisconnectionEvent evt = new DisconnectionEvent(id);
		addEvent(evt);
	}

	/**
	 * Return an array containing the events.
	 * 
	 * @return
	 */
	public WiiUseApiEvent[] getEvents() {
		return java.util.Arrays.copyOfRange(events, 0, index);
	}

	/**
	 * Clear the gatherer and remove objects.
	 */
	public void clearEvents() {
		for (int i = 0; i < events.length; i++) {
			events[i] = null;
		}
		genericEvent = null;
		index = 0;
	}

}
