package wiiusej;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class that is a bean to be filled by the wiiuse API.
 * 
 * @author gduche
 * 
 */
public class WiiMoteEvent {

	/* Buttons MACRO */
	private static short WIIMOTE_BUTTON_TWO = 0x0001;
	private static short WIIMOTE_BUTTON_ONE = 0x0002;
	private static short WIIMOTE_BUTTON_B = 0x0004;
	private static short WIIMOTE_BUTTON_A = 0x0008;
	private static short WIIMOTE_BUTTON_MINUS = 0x0010;
	private static short WIIMOTE_BUTTON_ZACCEL_BIT6 = 0x0020;
	private static short WIIMOTE_BUTTON_ZACCEL_BIT7 = 0x0040;
	private static short WIIMOTE_BUTTON_HOME = 0x0080;
	private static short WIIMOTE_BUTTON_LEFT = 0x0100;
	private static short WIIMOTE_BUTTON_RIGHT = 0x0200;
	private static short WIIMOTE_BUTTON_DOWN = 0x0400;
	private static short WIIMOTE_BUTTON_UP = 0x0800;
	private static short WIIMOTE_BUTTON_PLUS = 0x1000;
	private static short WIIMOTE_BUTTON_ZACCEL_BIT4 = 0x2000;
	private static short WIIMOTE_BUTTON_ZACCEL_BIT5 = 0x4000;
	private static int WIIMOTE_BUTTON_UNKNOWN = 0x8000;
	private static short WIIMOTE_BUTTON_ALL = 0x1F9F;

	private static short WIIMOTE_LED_1 = 1;
	private static short WIIMOTE_LED_2 = 2;
	private static short WIIMOTE_LED_3 = 4;
	private static short WIIMOTE_LED_4 = 8;

	private static short NB_LEDS = 4;

	/* ID */
	private int wiimoteId = -1;

	/* Status variables */
	private boolean connected = false;

	private float batteryLevel = -1;

	private short leds = 0;

	private boolean isSpeakerEnabled = false;

	private boolean isThereAttachment = false;

	private boolean isRumbleActive = false;

	private float orientationThreshold = 0;

	private boolean isContinuousActive = false;

	private boolean isSmoothingActive = false;

	/* Buttons */
	private short buttonsJustPressed = 0;
	private short buttonsJustReleased = 0;
	private short buttonsHeld = 0;

	/* IR Tracking */
	private boolean isIrActive = false;
	private Point2DInteger[] IRPoints;

	/* Motion Sensing */
	private boolean isMotionSensingActive = false;
	private Orientation orientation;
	private GForce gforce;

	/**
	 * Default constructor
	 */
	public WiiMoteEvent() {
		// init IRPoints array
		IRPoints = new Point2DInteger[NB_LEDS];
	}

	/**
	 * Construct the Wiimote setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id
	 */
	public WiiMoteEvent(int id) {
		this();
		wiimoteId = id;
	}

	/**
	 * Get Wiimote ID
	 * 
	 * @return the wiimote id.
	 */
	public int getWiimoteId() {
		return wiimoteId;
	}

	/**
	 * Set Wiimote ID
	 * 
	 * @param wiimoteId
	 *            id of the wiimote
	 */
	void setWiimoteId(int wiimoteId) {
		this.wiimoteId = wiimoteId;
	}

	/**
	 * True if the wiimote is connected false otherwise.
	 * 
	 * @return return the connected status.
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Set the connected value to true.
	 */
	void setConnected() {
		this.connected = true;
	}

	/**
	 * Set the connected value to false.
	 */
	void setDisconnected() {
		this.connected = false;
	}

	/**
	 * Get battery level.
	 * 
	 * @return battery level. 1 = 100%
	 */
	public float getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * Get status of the leds .
	 * 
	 * @return a short representing LEDS turned on.
	 */
	public short getLeds() {
		return leds;
	}

	/**
	 * Tell if the speaker is enable for this wiimote
	 * 
	 * @return TRUE if it enabled false otherwise
	 */
	public boolean isSpeakerEnabled() {
		return isSpeakerEnabled;
	}

	/**
	 * Tell if there is an attachment to the Wiimote
	 * 
	 * @return TRUE if it there is one false otherwise
	 */
	public boolean isThereAttachment() {
		return isThereAttachment;
	}

	/**
	 * Set battery level, leds, speaker state and attachment in one method.
	 * These method is called (and those variables are filled) only when a
	 * status has been requested on this wiimote.
	 * 
	 * @param batt
	 *            battery level
	 * @param led
	 *            status of leds
	 * @param speak
	 *            speakers status
	 * @param attach
	 *            attachment status
	 */
	void setBatteryLedsSpeakerAttachment(float batt, short led, boolean speak,
			boolean attach) {
		this.batteryLevel = batt;
		this.leds = led;
		this.isSpeakerEnabled = speak;
		this.isThereAttachment = attach;
	}

	/**
	 * Get the status of rumble.
	 * 
	 * @return true if the rumble is active false otherwise
	 */
	public boolean isRumbleActive() {
		return isRumbleActive;
	}

	/**
	 * Set Rumble flag to Inactive.
	 */
	void setRumbleInactive() {
		this.isRumbleActive = false;
	}

	/**
	 * Get orientation threshold.
	 * 
	 * @return the orientationThreshold
	 */
	public float getOrientationThreshold() {
		return orientationThreshold;
	}

	/**
	 * Tell if the CONTINUOUS option is activated.
	 * 
	 * @return the isContinuousActive
	 */
	public boolean isContinuousActive() {
		return isContinuousActive;
	}

	/**
	 * Tell if the option SMOOTHING is activated.
	 * 
	 * @return the isSmoothingActive
	 */
	public boolean isSmoothingActive() {
		return isSmoothingActive;
	}
	
	/**
	 * Get the short storing the buttons just pressed
	 * 
	 * @return the short storing the buttons just pressed
	 */
	public short getButtonsJustPressed() {
		return buttonsJustPressed;
	}

	/**
	 * Get the short storing the buttons just released
	 * 
	 * @return the short storing the buttons just released
	 */
	public short getButtonsJustReleased() {
		return buttonsJustReleased;
	}

	/**
	 * get the short storing the buttons held
	 * 
	 * @return the short storing the buttons held
	 */
	public short getButtonsHeld() {
		return buttonsHeld;
	}

	/**
	 * Set all buttons in one method.
	 * 
	 * @param buttonsJustPressed
	 * @param buttonsJustReleased
	 * @param buttonsHeld
	 */
	void setAllButtons(short buttonsJustPressed, short buttonsJustReleased,
			short buttonsHeld) {
		this.buttonsJustPressed = buttonsJustPressed;
		this.buttonsJustReleased = buttonsJustReleased;
		this.buttonsHeld = buttonsHeld;
	}

	/**
	 * Tell if the IR Tracking is active.
	 * 
	 * @return TRUE if it is active or false otherwise.
	 */
	public boolean isIrActive() {
		return isIrActive;
	}

	/**
	 * Get list of IR points.
	 * 
	 * @return the list of 2D points
	 */
	public Point2DInteger[] getIRPoints() {
		return IRPoints;
	}

	/**
	 * Add IR Point in the list (Max 4 points)
	 * 
	 * @param x
	 *            x value
	 * @param y
	 *            y value
	 */
	void addIRpoint(int x, int y) {
		for (int i = 0; i < IRPoints.length; i++) {
			if (IRPoints[i] == null) {
				IRPoints[i] = new Point2DInteger(x, y);
				return;
			}
		}
		return;
	}

	/**
	 * Clear IR points.
	 */
	void EmptyIRPoints() {
		for (int i = 0; i < IRPoints.length; i++) {
			IRPoints[i] = null;
		}
	}

	/**
	 * Get the flag indicating if the motion sensing is active.
	 * 
	 * @return true if the motion sensing is active false otherwise
	 */
	public boolean isMotionSensingActive() {
		return isMotionSensingActive;
	}

	/**
	 * Set status variables always filled during an event.
	 * 
	 * @param id
	 *            id of the wiimote
	 * @param connect
	 *            true if the wiimote is connected
	 * @param irState
	 *            true if ir is active
	 * @param rumbleState
	 *            true if rumble is active
	 * @param motionSensingState
	 *            true if accelerometer is active
	 * @param orientationThreshold
	 *            value of the minimum angle between two events with the
	 *            accelerometer
	 * @param continuousState
	 *            true if continuous flag is activated
	 * @param smoothingState
	 *            true if smoothing flag is activated
	 */
	void setPermanentStatus(int id, boolean connect, boolean irState,
			boolean rumbleState, boolean motionSensingState,
			float orientationThreshold, boolean continuousState,
			boolean smoothingState) {
		wiimoteId = id;
		connected = connect;
		isIrActive = irState;
		isRumbleActive = rumbleState;
		isMotionSensingActive = motionSensingState;
		this.orientationThreshold = orientationThreshold;
		isContinuousActive = continuousState;
		isSmoothingActive = smoothingState;
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
	void setOrientationAndGforce(float r, float p, float ya, float x, float y,
			float z) {
		this.orientation = new Orientation(r, p, ya);
		this.gforce = new GForce(x, y, z);
	}

	/** **************** BUTTONS Methods ***************** */
	/* generic button functions */

	private boolean buttonTest(short buttonBitsDefinition, short buttons) {
		return (buttons & buttonBitsDefinition) == buttonBitsDefinition;
	}

	private boolean isButtonJustPressed(short buttonBitsDefinition) {
		return buttonTest(buttonBitsDefinition, buttonsJustPressed)
				&& !isButtonHeld(buttonBitsDefinition);
	}

	private boolean isButtonJustReleased(short buttonBitsDefinition) {
		return buttonTest(buttonBitsDefinition, buttonsJustReleased);
	}

	private boolean isButtonHeld(short buttonBitsDefinition) {
		return buttonTest(buttonBitsDefinition, buttonsHeld);
	}

	/* Button ONE */

	public boolean isButtonOneJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_ONE);
	}

	public boolean isButtonOneJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_ONE);
	}

	public boolean isButtonOneHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_ONE);
	}

	/* Button TWO */

	public boolean isButtonTwoJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_TWO);
	}

	public boolean isButtonTwoJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_TWO);
	}

	public boolean isButtonTwoHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_TWO);
	}

	/* Button A */

	public boolean isButtonAJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_A);
	}

	public boolean isButtonAJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_A);
	}

	public boolean isButtonAHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_A);
	}

	/* Button B */

	public boolean isButtonBJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_B);
	}

	public boolean isButtonBJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_B);
	}

	public boolean isButtonBHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_B);
	}

	/* Button LEFT */

	public boolean isButtonLeftJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_LEFT);
	}

	public boolean isButtonLeftJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_LEFT);
	}

	public boolean isButtonLeftHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_LEFT);
	}

	/* Button RIGHT */

	public boolean isButtonRightJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_RIGHT);
	}

	public boolean isButtonRightJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_RIGHT);
	}

	public boolean isButtonRightHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_RIGHT);
	}

	/* Button UP */

	public boolean isButtonUpJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_UP);
	}

	public boolean isButtonUpJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_UP);
	}

	public boolean isButtonUpHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_UP);
	}

	/* Button DOWN */

	public boolean isButtonDownJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_DOWN);
	}

	public boolean isButtonDownJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_DOWN);
	}

	public boolean isButtonDownHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_DOWN);
	}

	/* Button - */

	public boolean isButtonMinusJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_MINUS);
	}

	public boolean isButtonMinusJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_MINUS);
	}

	public boolean isButtonMinusHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_MINUS);
	}

	/* Button + */

	public boolean isButtonPlusJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_PLUS);
	}

	public boolean isButtonPlusJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_PLUS);
	}

	public boolean isButtonPlusHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_PLUS);
	}

	/* Button HOME */

	public boolean isButtonHomeJustPressed() {
		return isButtonJustPressed(WIIMOTE_BUTTON_HOME);
	}

	public boolean isButtonHomeJustReleased() {
		return isButtonJustReleased(WIIMOTE_BUTTON_HOME);
	}

	public boolean isButtonHomeHeld() {
		return isButtonHeld(WIIMOTE_BUTTON_HOME);
	}

	@Override
	public String toString() {
		super.toString();
		String out = "";
		/* Status */
		out += "/*********** WIIMOTE   ID :" + wiimoteId + " ********/\n";
		out += "--- connected : " + connected + "\n";
		out += "--- Battery level : " + batteryLevel + "\n";
		out += "--- Leds : " + leds + "\n";
		out += "--- Speaker enabled : " + isSpeakerEnabled + "\n";
		out += "--- Attachment ? : " + isThereAttachment + "\n";
		out += "--- Rumble ? : " + isRumbleActive + "\n";
		out += "--- Orientation threshold value ? : " + orientationThreshold
				+ "\n";
		out += "--- Continuous ? : " + isContinuousActive + "\n";
		out += "--- Smoothing ? : " + isSmoothingActive + "\n";
		/* Display buttons */
		out += "/******** Buttons ********/\n";
		out += "--- Buttons just pressed : " + buttonsJustPressed + "\n";
		out += "--- Buttons just released : " + buttonsJustReleased + "\n";
		out += "--- Buttons held : " + buttonsHeld + "\n";

		/* Display IR Tracking */
		out += "/******** IR Tracking ********/\n";
		out += "--- Active : " + isIrActive + "\n";
		if (isIrActive) {
			out += "--- Seen points\n";
			for (int i = 0; i < IRPoints.length; i++) {
				if (IRPoints[i] != null) {
					out += IRPoints[i].toString();
				}
			}
		}

		/* Motion sensing */
		out += "/******** Motion sensing ********/\n";
		out += "--- Motion sensing : " + isMotionSensingActive + "\n";
		if (isMotionSensingActive) {
			out += "--- " + orientation + "\n";
			out += "--- " + gforce + "\n";
		}
		return out;
	}

}
