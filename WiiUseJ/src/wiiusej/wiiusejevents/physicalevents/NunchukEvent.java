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
package wiiusej.wiiusejevents.physicalevents;

/**
 * This class represents the values from the joystick and its events.
 * 
 * @author guiguito
 */
public class NunchukEvent extends ExpansionEvent {

	NunchukButtonsEvent buttonsEvent;
	MotionSensingEvent nunchukMotionSensingEvent;
	JoystickEvent nunchukJoystickEvent;

	/**
	 * @param id
	 */
	public NunchukEvent(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see wiiusej.wiiusejevents.GenericEvent#toString()
	 */
	@Override
	public String toString() {
		String out = "";
		/* Status */
		out += "/*********** Nunchuk EVENT : WIIMOTE   ID :" + getWiimoteId()
				+ " ********/\n";
		out += buttonsEvent;
		out += nunchukJoystickEvent;
		out += nunchukMotionSensingEvent;
		return out;
	}

}
