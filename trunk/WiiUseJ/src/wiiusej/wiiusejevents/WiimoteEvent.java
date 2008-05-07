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
package wiiusej.wiiusejevents;

import wiiusej.values.Expansion;

/**
 * Class that is a bean to be filled by the wiiuse API on an event that occurs
 * on a wiimote.
 * 
 * @author guiguito
 */
public class WiimoteEvent extends WiiUseApiEvent {

	WiimoteButtonsEvent buttonsEvent = null;
	IREvent infraredEvent = null;
	MotionSensingEvent motionSensingEvent = null;
	Expansion expansion = null;

	/**
	 * Construct the Wiimote setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id
	 */
	public WiimoteEvent(int id) {
		super(id, WiiUseApiEvent.GENERIC_EVENT);
	}

	/**
	 * Construct the Wiimote setting up the id and the buttons.
	 * 
	 * @param id
	 *            the Wiimote id
	 * @param buttonsJustPressed
	 *            buttons just pressed
	 * @param buttonsJustReleased
	 *            buttons just released
	 * @param buttonsHeld
	 *            buttons held
	 */
	public WiimoteEvent(int id, short buttonsJustPressed,
			short buttonsJustReleased, short buttonsHeld) {
		super(id, WiiUseApiEvent.GENERIC_EVENT);
		buttonsEvent = new WiimoteButtonsEvent(id, buttonsJustPressed,
				buttonsJustReleased, buttonsHeld);
	}

	/**
	 * Tell if there is an IR Event.
	 * 
	 * @return TRUE if there is an IR event.
	 */
	public boolean isThereIrEvent() {
		return infraredEvent != null;
	}

	/**
	 * Tell if there is a motion sensing Event.
	 * 
	 * @return TRUE if there is a motion sensing event.
	 */
	public boolean isThereMotionSensingEvent() {
		return motionSensingEvent != null;
	}

	/**
	 * Get buttons event.
	 * 
	 * @return the buttons event.
	 */
	public WiimoteButtonsEvent getButtonsEvent() {
		return buttonsEvent;
	}

	/**
	 * Get IR event.
	 * 
	 * @return the IR event if there is one or null.
	 */
	public IREvent getIREvent() {
		return infraredEvent;
	}

	/**
	 * Get motion sensing event.
	 * 
	 * @return the motion sensing event if there is one or null.
	 */
	public MotionSensingEvent getMotionSensingEvent() {
		return motionSensingEvent;
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
	 * @param irSensitivity
	 *            Sensitivity of the infrared camera.
	 * @param distance
	 *            Pixel Distance between first two dots
	 */
	public void prepareIRevent(int x, int y, int z, int ax, int ay, int xVRes,
			int yVRes, int xOffset, int yOffset, short sensorBarPostion,
			short screenAsPectRatio, short irSensitivity, float distance) {
		if (infraredEvent == null) {
			infraredEvent = new IREvent(getWiimoteId(), x, y, z, ax, ay, xVRes,
					yVRes, xOffset, yOffset, sensorBarPostion,
					screenAsPectRatio, irSensitivity, distance);
		}
	}

	/**
	 * Add an IR point to the generic event. Create an IR Event if it's not
	 * created yet.
	 * 
	 * @param x
	 *            x coordinates.
	 * @param y
	 *            y coordinates
	 * @param rx
	 *            raw X coordinate (0-1023).
	 * @param ry
	 *            raw Y coordinate (0-1023).
	 * @param size
	 *            size of the IR dot (0-15).
	 */
	public void addIRpoint(int x, int y, short rx, short ry, short size) {
		if (infraredEvent != null)
			infraredEvent.addIRpoint(x, y, rx, ry, size);
	}

	/**
	 * Set the Motion Sensing Event.
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
	 * @param ar
	 *            absolute roll
	 * @param ap
	 *            absolute pitch
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
	public void setMotionSensingEvent(float orientationThreshold,
			int accelerationThreshold, boolean smoothingState,
			float alphaSmooth, float r, float p, float ya, float ar, float ap,
			float x, float y, float z, short xx, short yy, short zz) {
		motionSensingEvent = new MotionSensingEvent(getWiimoteId(),
				orientationThreshold, accelerationThreshold, smoothingState,
				alphaSmooth, r, p, ya, ar, ap, x, y, z, xx, yy, zz);
	}

	@Override
	public String toString() {
		String out = "";
		/* Status */
		out += "/*********** GENERIC EVENT : WIIMOTE   ID :"
				+ super.getWiimoteId() + " ********/\n";

		out += buttonsEvent;

		if (infraredEvent != null) {
			out += infraredEvent;
		} else {
			out += "/******** IR Tracking ********/\n";
			out += "--- Active : false\n";
		}

		if (motionSensingEvent != null) {
			out += motionSensingEvent;
		} else {
			out += "/******** Motion sensing ********/\n";
			out += "--- Motion sensing : false \n";
		}

		return out;
	}

}
