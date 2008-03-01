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

import wiiusej.values.GForce;
import wiiusej.values.Orientation;

/**
 * Class which represents a motion sensing event.
 * 
 * @author guiguito
 */
public class MotionSensingEvent extends WiimoteEvent{

	/* Motion Sensing */
	private Orientation orientation;
	private GForce gforce;

	/**
	 * Constructor for a Motion Sensing Event.
	 * 
	 * @param id
	 *            id of the wiimote concerned.
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
	public MotionSensingEvent(int id, float r, float p, float ya, float x, float y,
			float z) {
		super(id);
		setOrientationAndGforce(r, p, ya, x, y, z);
	}

	/**
	 * Set orientation and gravity force.
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
	private void setOrientationAndGforce(float r, float p, float ya, float x,
			float y, float z) {
		this.orientation = new Orientation(r, p, ya);
		this.gforce = new GForce(x, y, z);
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Get the gravity force.
	 * 
	 * @return the gforce
	 */
	public GForce getGforce() {
		return gforce;
	}

	@Override
	public String toString() {
		String out = "";
		/* Motion sensing */
		out += "/******** Motion sensing ********/\n";
		out += "--- Motion sensing : true \n";
		out += "--- " + orientation + "\n";
		out += "--- " + gforce + "\n";
		return out;
	}
}
