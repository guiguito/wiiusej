package wiiusej;

/**
 * Represents a request to set leds of the wiimote with WiiUse API.
 * @author gduche
 *
 */
public class LedsRequest extends wiiusej.WiiUseApiRequest {

	private boolean led1, led2, led3, led4;

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            id of the wiimote concerned
	 * @param type
	 *            type of the request
	 */
	public LedsRequest(int id, int type) {
		super(id, type);
	}

	/**
	 * Constructor setting the id of the wiimote concerned.
	 * 
	 * @param id
	 *            id of the wiimote concerned
	 * @param type
	 *            type of the request
	 * @param l1
	 *            led1 status. True=ON, False=OFF
	 * @param l2
	 *            led2 status. True=ON, False=OFF
	 * @param l3
	 *            led3 status. True=ON, False=OFF
	 * @param l4
	 *            led4 status. True=ON, False=OFF
	 */
	public LedsRequest(int id, int type, boolean l1, boolean l2, boolean l3,
			boolean l4) {
		super(id, type);
		led1 = l1;
		led2 = l2;
		led3 = l3;
		led4 = l4;
	}

	/**
	 * @return the led1
	 */
	public boolean isLed1() {
		return led1;
	}

	/**
	 * @param led1
	 *            the led1 to set
	 */
	public void setLed1(boolean led1) {
		this.led1 = led1;
	}

	/**
	 * @return the led2
	 */
	public boolean isLed2() {
		return led2;
	}

	/**
	 * @param led2
	 *            the led2 to set
	 */
	public void setLed2(boolean led2) {
		this.led2 = led2;
	}

	/**
	 * @return the led3
	 */
	public boolean isLed3() {
		return led3;
	}

	/**
	 * @param led3
	 *            the led3 to set
	 */
	public void setLed3(boolean led3) {
		this.led3 = led3;
	}

	/**
	 * @return the led4
	 */
	public boolean isLed4() {
		return led4;
	}

	/**
	 * @param led4
	 *            the led4 to set
	 */
	public void setLed4(boolean led4) {
		this.led4 = led4;
	}

}
