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
 * This is the interface to implement to listen to events from wiimotes.
 * The differents methods are called in this order : 
 * onButtonsEvent, onIrEvent, onMotionSensingEvent, onStatusEvent, onDisconnectionEvent. 
 * 
 * @author guiguito
 */
public interface WiimoteListener extends java.util.EventListener {

	/**
	 * Method called on a button Event.
	 * @param e the buttonEvent with the last informations 
	 * 			about the buttons of the wiimote.
	 */
	void onButtonsEvent(ButtonsEvent e);
	
	/**
	 * Method called when an IR event occurs.
	 * @param e the IREvent with the IR points seen.
	 */
	void onIrEvent(IREvent e);
	
	/**
	 * Method called when a motion sensing event occurs.
	 * @param e the motion sensing event with orientation and acceleration.
	 */
	void onMotionSensingEvent(MotionSensingEvent e);
	
	/**
	 * Method called on a status event.
	 * A status event occurs when :
	 * 						- we ask it
	 * 						- an expansion controller has been plugged
	 * 						- an expansion controller has been unplugged
	 * This is where you can get the different values of
	 * the parameters setup on your wiimote. 
	 * @param e the status event.
	 */
	void onStatusEvent(StatusEvent e);

	/**
	 * This is the method called when a disconnection event occurs.
	 * A disconnection event happens when :
	 * 						- there are no battery left
	 * 						- the wiimote has just been turned off
	 *   					- the connection is dropped
	 * @param e the disconnection event.
	 */
	void onDisconnectionEvent(DisconnectionEvent e);

}
