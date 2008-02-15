package wiiusej.wiiuseapirequest;

/**
 * Represents a request with an int value to pass to wiiuse API.
 * @author gduche
 *
 */
public class IntValueRequest extends WiiUseApiRequest {

	private int intValue;

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            the id of the wiimote concerned.
	 */
	public IntValueRequest(int id, int type) {
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
	public IntValueRequest(int id, int type, int th) {
		super(id, type);
		intValue = th;
	}

	/**
	 * Get the int value.
	 * @return the int value
	 */
	public int getIntValue() {
		return intValue;
	}

	/**
	 * Set the int value.
	 * @param val the value to set
	 */
	public void setIntValue(int val) {
		this.intValue = val;
	}
	
	

}
