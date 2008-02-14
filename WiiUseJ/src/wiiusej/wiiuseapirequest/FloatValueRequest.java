package wiiusej.wiiuseapirequest;

/**
 * Represents a request to set orientation Threshold in Wiiuse API.
 * Orientation Threshold is the minimum angle (in degrees) between two events. 
 * @author gduche
 *
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
	 * @return the float value
	 */
	public float getFloatValue() {
		return floatValue;
	}

	/**
	 * @param val the thresholhd to set
	 */
	public void setFloatValue(float val) {
		this.floatValue = val;
	}
	
	

}
