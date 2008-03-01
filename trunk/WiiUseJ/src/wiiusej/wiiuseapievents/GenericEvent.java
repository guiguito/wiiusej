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
 * Class that is a bean to be filled by the wiiuse API.
 * 
 * @author guiguito
 */
public class GenericEvent extends WiiUseApiEvent {

	ButtonsEvent buttonsEvent = null;
	IREvent infraredEvent = null;
	MotionSensingEvent motionSensingEvent = null;

	/**
	 * Construct the Wiimote setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id
	 */
	public GenericEvent(int id) {
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
	public GenericEvent(int id, short buttonsJustPressed,
			short buttonsJustReleased, short buttonsHeld) {
		super(id, WiiUseApiEvent.GENERIC_EVENT);
		buttonsEvent = new ButtonsEvent(id, buttonsJustPressed,
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
	 * @return the buttons event.
	 */
	public ButtonsEvent getButtonsEvent() {
		return buttonsEvent;
	}

	/**
	 * Get IR event.
	 * @return the IR event if there is one or null.
	 */
	public IREvent getIREvent() {
		return infraredEvent;
	}

	/**
	 * Get motion sensing event.
	 * @return the motion sensing event if there is one or null.
	 */
	public MotionSensingEvent getMotionSensingEvent() {
		return motionSensingEvent;
	}
	
	/**
	 * Add an IR point to the generic event.
	 * Create an IR Event if it's not created yet.
	 * @param x x coordinates.
	 * @param y y coordinates
	 */
	public void addIRpoint(int x,int y){
		//@TODO add points size
		if (infraredEvent == null){
			infraredEvent = new IREvent(getWiimoteId());
		}
		infraredEvent.addIRpoint(x, y);
	}
	
	/**
	 * Set the Motion Sensing Event.
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
	public void setMotionSensingEvent(float r, float p, float ya, float x, float y,
			float z){
		motionSensingEvent = new MotionSensingEvent(getWiimoteId(), r, p, ya, x, y, z);
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
		}else{
			out += "/******** IR Tracking ********/\n";
			out += "--- Active : false\n";
		}

		if (motionSensingEvent != null) {
			out += motionSensingEvent;
		}else{
			out += "/******** Motion sensing ********/\n";
			out += "--- Motion sensing : false \n";
		}

		return out;
	}
	
}
