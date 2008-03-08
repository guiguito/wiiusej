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
import wiiusej.values.RawAcceleration;

/**
 * Class which represents a motion sensing event.
 * 
 * @author guiguito
 */
public class MotionSensingEvent extends WiimoteEvent{

	/* Motion Sensing */
	private Orientation orientation;
	private GForce gforce;
	private RawAcceleration acceleration;

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
	 * @param xx
	 *            raw acceleration on x axis
	 * @param yy
	 *            raw acceleration on y axis
	 * @param zz
	 *            raw acceleration on z axis
	 */
	public MotionSensingEvent(int id, float r, float p, float ya, float x, float y,
			float z, short xx, short yy, short zz) {
		super(id);
		setOrientationAndGforce(r, p, ya, x, y, z, xx, yy, zz);
	}

	/**
	 * Set orientation, gravity force and raw acceleration.
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
     * @param xx
	 *            raw acceleration on x axis
	 * @param yy
	 *            raw acceleration on y axis
	 * @param zz
	 *            raw acceleration on z axis
	 */
	private void setOrientationAndGforce(float r, float p, float ya, float x,
			float y, float z, short xx, short yy, short zz) {
		this.orientation = new Orientation(r, p, ya);
		this.gforce = new GForce(x, y, z);
		this.acceleration = new RawAcceleration(xx, yy, zz);
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
	
	/**
	 * Get the raw acceleration.
	 * 
	 * @return the raw acceleration
	 */
	public RawAcceleration getRawAcceleration() {
		return acceleration;
	}

	@Override
	public String toString() {
		String out = "";
		/* Motion sensing */
		out += "/******** Motion sensing ********/\n";
		out += "--- Motion sensing : true \n";
		out += "--- " + orientation + "\n";
		out += "--- " + gforce + "\n";
		out += "--- " + acceleration + "\n";
		return out;
	}
}
