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
package wiiusej.wiiuseapirequest;

/**
 * Represents a request with a float value to pass to wiiuse API.
 * 
 * @author guiguito
 */
public class FloatValueRequest extends WiiUseApiRequest {

	private float floatValue;

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            the id of the wiimote concerned.
	 */
	public FloatValueRequest(int id, int type) {
		super(id, type);
	}

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            the id of the wiimote concerned.
	 * @param type
	 *            type of the request
	 * @param th
	 *            threshold in degrees
	 */
	public FloatValueRequest(int id, int type, float th) {
		super(id, type);
		floatValue = th;
	}

	/**
	 * Get the float value.
	 * @return the float value
	 */
	public float getFloatValue() {
		return floatValue;
	}

	/**
	 * Set the float value.
	 * @param val the value to set
	 */
	public void setFloatValue(float val) {
		this.floatValue = val;
	}
	
	

}
