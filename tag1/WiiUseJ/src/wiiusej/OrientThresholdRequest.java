package wiiusej;

/**
 * Represents a request to set orientation Threshold in Wiiuse API.
 * Orientation Threshold is the minimum angle (in degrees) between two events. 
 * @author gduche
 *
 */
public class OrientThresholdRequest extends WiiUseApiRequest {

	private float thresholhd;

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            the id of the wiimote concerned.
	 */
	public OrientThresholdRequest(int id, int type) {
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
	public OrientThresholdRequest(int id, int type, float th) {
		super(id, type);
		thresholhd = th;
	}

	/**
	 * @return the thresholhd
	 */
	public float getThresholhd() {
		return thresholhd;
	}

	/**
	 * @param thresholhd the thresholhd to set
	 */
	public void setThresholhd(float thresholhd) {
		this.thresholhd = thresholhd;
	}
	
	

}
