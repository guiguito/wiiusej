/**
 * This file is part of WiiuseJ.
 *
 *  WiiuseJ is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  WiiuseJ is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with WiiuseJ.  If not, see <http://www.gnu.org/licenses/>.
 */
package wiiusej.wiiuseapievents;

/**
 * This class is used to gather events during a call to the Wiiuse API.
 * 
 * @author guiguito
 */
public class EventsGatherer {

	private WiiUseApiEvent[] events;
	private int index = 0;
	private GenericEvent genericEvent = null;

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
		genericEvent = new GenericEvent(id, buttonsJustPressed,
				buttonsJustReleased, buttonsHeld);
	}

	/**
	 * Prepare an IR event to populate.
	 * 
	 * @param x
	 *            calculated X coordinate.
	 * @param y
	 *            calculated Y coordinate.
	 * @param z
	 *            calculated distance.
	 * @param ax
	 *            absolute X coordinate.
	 * @param ay
	 *            absolute Y coordinate
	 * @param xVRes
	 *            IR virtual screen x resolution.
	 * @param yVRes
	 *            IR virtual screen y resolution.
	 * @param xOffset
	 *            IR X correction offset.
	 * @param yOffset
	 *            IR Y correction offset.
	 * @param sensorBarPostion
	 *            aspect ratio of the screen.
	 * @param screenAsPectRatio
	 *            IR sensor bar position.
	 */
	public void prepareIRevent(int x, int y, int z, int ax, int ay, int xVRes,
			int yVRes, int xOffset, int yOffset, short sensorBarPostion,
			short screenAsPectRatio) {
		genericEvent.prepareIRevent(x, y, z, ax, ay, xVRes, yVRes, xOffset,
				yOffset, sensorBarPostion, screenAsPectRatio);

	}

	/**
	 * Add an IR point to the WiiMoteEvent prepared
	 * 
	 * @param x
	 *            x coordinates
	 * @param y
	 *            y coordinates
	 * @param rx
	 *            raw X coordinate (0-1023).
	 * @param ry
	 *            raw Y coordinate (0-1023).
	 * @param size
	 *            size of the IR dot (0-15).
	 */
	public void addIRPointToPreparedWiiMoteEvent(int x, int y, short rx,
			short ry, short size) {
		if (genericEvent != null) {
			genericEvent.addIRpoint(x, y, rx, ry, size);
		}
	}

	/**
	 * Set orientation and gravity force of the prepared event.
	 * 
	 * @param orientationThreshold
	 *            value of the minimum angle between two events with the
	 *            accelerometer
	 * @param accelerationThreshold
	 *            value of the value variation between two events with the
	 *            accelerometer
	 * @param smoothingState
	 *            true if smoothing flag is activated
	 * @param alphaSmooth
	 *            value of the alpha smoothing parameter
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
	 * @param xx
	 *            raw acceleration on x axis
	 * @param yy
	 *            raw acceleration on y axis
	 * @param zz
	 *            raw acceleration on z axis
	 */
	public void addMotionSensingValues(float orientationThreshold,
			int accelerationThreshold, boolean smoothingState,
			float alphaSmooth, float r, float p, float ya, float x, float y,
			float z, short xx, short yy, short zz) {
		if (genericEvent != null) {
			genericEvent.setMotionSensingEvent(orientationThreshold,
					accelerationThreshold, smoothingState, alphaSmooth, r, p,
					ya, x, y, z, xx, yy, zz);
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
	 * @param continuousState
	 *            true if continuous flag is activated
	 * @param irState
	 *            true if ir is active
	 * @param motionSensingState
	 *            true if accelerometer is active
	 */
	public void addStatusEvent(int id, boolean connect, float batt, short led,
			boolean speak, int attach, boolean rumbleState,
			boolean continuousState, boolean irState, boolean motionSensingState) {
		StatusEvent evt = new StatusEvent(id, connect, batt, led, speak,
				attach, rumbleState, continuousState, irState,
				motionSensingState);
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
	 * @return events received
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
