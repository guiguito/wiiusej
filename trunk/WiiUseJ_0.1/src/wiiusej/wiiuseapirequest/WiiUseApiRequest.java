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
 * Represents a request we could do to the WiiUse API.
 * 
 * @author guiguito
 */
public class WiiUseApiRequest {

	public static int WIIUSE_STATUS_REQUEST = 1;
	public static int WIIUSE_ACTIVATE_SMOOTHING_REQUEST = 2;
	public static int WIIUSE_DEACTIVATE_SMOOTHING_REQUEST = -2;
	public static int WIIUSE_ACTIVATE_IR_TRACKING_REQUEST = 3;
	public static int WIIUSE_DEACTIVATE_IR_TRACKING_REQUEST = -3;
	public static int WIIUSE_ACTIVATE_MOTION_SENSING_REQUEST = 4;
	public static int WIIUSE_DEACTIVATE_MOTION_SENSING_REQUEST = -4;
	public static int WIIUSE_CLOSE_CONNECTION_REQUEST = 5;
	public static int WIIUSE_ACTIVATE_CONTINUOUS_REQUEST = 6;
	public static int WIIUSE_DEACTIVATE_CONTINUOUS_REQUEST = -6;
	public static int WIIUSE_ACTIVATE_RUMBLE_REQUEST = 7;
	public static int WIIUSE_DEACTIVATE_RUMBLE_REQUEST = -7;
	public static int WIIUSE_LEDS_REQUEST = 8;
	public static int WIIUSE_ORIENT_THRESHOLHD_REQUEST = 9;
	public static int WIIUSE_ACCEL_THRESHOLHD_REQUEST = 10;
	public static int WIIUSE_ALPHA_SMOOTHING_REQUEST = 11;
	public static int WIIUSE_RESYNC = 12;
	public static int WIIUSE_ASPECT_RATIO_4_3 = 13;
	public static int WIIUSE_ASPECT_RATIO_16_9 = 14;
	public static int WIIUSE_SENSOR_BAR_ABOVE = 15;
	public static int WIIUSE_SENSOR_BAR_BELOW = 16;	
	public static int WIIUSE_SET_VIRTUAL_RESOLUTION = 17;

	private int wiimoteId = 0;
	private int requestType = 0;

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            the id of the wiimote concerned.
	 */
	public WiiUseApiRequest(int id) {
		wiimoteId = id;
	}

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            the id of the wiimote concerned.
	 * 
	 */
	public WiiUseApiRequest(int id, int type) {
		wiimoteId = id;
		requestType = type;
	}

	/**
	 * Get id of the wiimote concerned by this request.
	 * 
	 * @return id of the wiimote concerned
	 */
	public int getId() {
		return wiimoteId;
	}

	/**
	 * Set id of the wiimote concerned by this request.
	 * 
	 * @param id
	 *            id fh the wiimote concernet
	 */
	public void setId(int id) {
		wiimoteId = id;
	}

	/**
	 * Get the request type.
	 * 
	 * @return the requestType
	 */
	public int getRequestType() {
		return requestType;
	}

	/**
	 * Set the request type.
	 * 
	 * @param requestType
	 *            the requestType to set
	 */
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

}
