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
	 * Set level battery.
	 * 
	 * @param batteryLevel
	 *            must be between 0 and 1
	 */
	void setBatteryLevel(float batteryLevel) {
		this.batteryLevel = batteryLevel;
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
	 * Set Status of the leds.
	 * 
	 * @param leds
	 */
	void setLeds(short leds) {
		this.leds = leds;
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
	 * Set the flag for the speaker to "enabled"
	 */
	void setSpeakerEnabled() {
		this.isSpeakerEnabled = true;
	}

	/**
	 * Set the flag for the speaker to "disabled"
	 */
	void setSpeakerDisabled() {
		this.isSpeakerEnabled = false;
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
	 * Set the flag for the attachment to true
	 */
	void setThereIsAnAttachment() {
		this.isThereAttachment = true;
	}

	/**
	 * Set the flag for the attachment to false
	 */
	void setThereIsNoAttachment() {
		this.isThereAttachment = false;
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
	 * Set Rumble flag to Active.
	 */
	void setRumbleActive() {
		this.isRumbleActive = true;
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
	 * Set the orientation threshold.
	 * 
	 * @param orientationThreshold
	 *            the orientationThreshold to set
	 */
	void setOrientationThreshold(float orientationThreshold) {
		this.orientationThreshold = orientationThreshold;
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
	 * Set the CONTINUOUS option active.
	 */
	void setContinuousActive() {
		this.isContinuousActive = true;
	}

	/**
	 * Set the CONTINUOUS option inactive.
	 */
	void setContinuousInactive() {
		this.isContinuousActive = false;
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
	 * Set SMOOTHING option to active.
	 */
	void setSmoothingActive() {
		this.isSmoothingActive = true;
	}

	/**
	 * Set SMOOTHING option to inactive.
	 */
	void setSmoothingInactive() {
		this.isSmoothingActive = false;
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
	 * set the short storing the buttons just pressed
	 * 
	 * @param buttonsJustPressed
	 */
	void setButtonsJustPressed(short buttonsJustPressed) {
		this.buttonsJustPressed = buttonsJustPressed;
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
	 * set the short storing the buttons just released
	 * 
	 * @param buttonsJustReleased
	 */
	void setButtonsJustReleased(short buttonsJustReleased) {
		this.buttonsJustReleased = buttonsJustReleased;
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
	 * set the short storing the buttons held
	 * 
	 * @param buttonsHeld
	 */
	void setButtonsHeld(short buttonsHeld) {
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
	 * Set the value isIrActive to true
	 */
	void setIrActive() {
		this.isIrActive = true;
	}

	/**
	 * Set the value isIrActive to true
	 */
	void setIrInactive() {
		this.isIrActive = false;
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
	 * Set the motion sensing flag to active.
	 */
	void setMotionSensingActive() {
		this.isMotionSensingActive = true;
	}

	/**
	 * Set the motion sensing flag to inactive.
	 */
	void setMotionSensingInactive() {
		this.isMotionSensingActive = false;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Set orientation of the wiimote.
	 * 
	 * @param r
	 *            roll
	 * @param p
	 *            pitch
	 * @param y
	 *            yaw
	 */
	void setOrientation(float r, float p, float y) {
		this.orientation = new Orientation(r, p, y);
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
	 * Set the gravity force.
	 * 
	 * @param x
	 *            gravity force on x axis
	 * @param y
	 *            gravity force on y axis
	 * @param z
	 *            gravity force on z axis
	 */
	void setGforce(float x, float y, float z) {
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
		out += "= --- Leds : " + leds + "\n";
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
