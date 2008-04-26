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
 * Represents a request with two int values to pass to wiiuse API.
 * 
 * @author guiguito
 */
public class TwoIntValueRequest extends IntValueRequest {
	
	private int secondIntValue;
	
	/**
	 * Constructor setting the id of the wiimote 
	 * concerned and the type of the request.
	 * 
	 * @param id the id of the wiimote concerned.
	 * @param type type of the request
	 */
	public TwoIntValueRequest(int id, int type) {
		super(id, type);
	}

	/**
	 * Constructor setting the id of the wiimote 
	 * concerned, the type of the request 
	 * and the two int values.
	 * 
	 * @param id the id of the wiimote concerned.
	 * @param type type of the request
	 * @param x first int value.
	 * @param y second int value.
	 */
	public TwoIntValueRequest(int id, int type, int x, int y) {
		super(id, type, x);
		secondIntValue = y;
	}

	/**
	 * Get the second int value.
	 * @return the secondIntValue
	 */
	public int getSecondIntValue() {
		return secondIntValue;
	}

	/**
	 * Set the second int value.
	 * @param secondIntValue the secondIntValue to set.
	 */
	public void setSecondIntValue(int secondIntValue) {
		this.secondIntValue = secondIntValue;
	}
	
	
	
}
