package wiiusej.wiiuseapirequest;

/**
 * Represents a request we could do to the WiiUse API.
 * @author gduche
 *
 */
public class WiiUseApiRequest {

	public static int WIIUSE_STATUS_REQUEST=1;
	public static int WIIUSE_ACTIVATE_SMOOTHING_REQUEST=2;
	public static int WIIUSE_DEACTIVATE_SMOOTHING_REQUEST=-2;
	public static int WIIUSE_ACTIVATE_IR_TRACKING_REQUEST=3;
	public static int WIIUSE_DEACTIVATE_IR_TRACKING_REQUEST=-3;	
	public static int WIIUSE_ACTIVATE_MOTION_SENSING_REQUEST=4;
	public static int WIIUSE_DEACTIVATE_MOTION_SENSING_REQUEST=-4;
	public static int WIIUSE_CLOSE_CONNECTION_REQUEST=5;
	public static int WIIUSE_ACTIVATE_CONTINUOUS_REQUEST=6;
	public static int WIIUSE_DEACTIVATE_CONTINUOUS_REQUEST=-6;
	public static int WIIUSE_ACTIVATE_RUMBLE_REQUEST=7;
	public static int WIIUSE_DEACTIVATE_RUMBLE_REQUEST=-7;
	public static int WIIUSE_LEDS_REQUEST=8;
	public static int WIIUSE_ORIENT_THRESHOLHD_REQUEST=9;
	public static int WIIUSE_ACCEL_THRESHOLHD_REQUEST=10;
	public static int WIIUSE_ALPHA_SMOOTHING_REQUEST=11;
	
	private int wiimoteId=0;
	private int requestType=0;
	
	/**
	 * Constructor setting the id of the wiimote concerned.
	 * @param id the id of the wiimote concerned.
	 */
	public WiiUseApiRequest(int id){
		wiimoteId = id;
	}
	
	/**
	 * Constructor setting the id of the wiimote concerned.
	 * @param id the id of the wiimote concerned.
	 * 
	 */
	public WiiUseApiRequest(int id, int type){
		wiimoteId = id;
		requestType = type;
	}
	
	/**
	 * Get id of the wiimote concerned by this request.
	 * @return id of the wiimote concerned
	 */
	public int getId(){
		return wiimoteId;
	}
	
	/**
	 * Set id of the wiimote concerned by this request.
	 * @param id id fh the wiimote concernet
	 */
	public void setId(int id){
		wiimoteId = id;
	}

	/**
	 * Get the request type.
	 * @return the requestType
	 */
	public int getRequestType() {
		return requestType;
	}

	/**
	 * Set the request type.
	 * @param requestType the requestType to set
	 */
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	
	
	
}

