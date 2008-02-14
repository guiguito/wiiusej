package wiiusej.wiiuseapievents;

import java.util.ArrayList;
import java.util.Iterator;

import wiiusej.GForce;
import wiiusej.Orientation;
import wiiusej.Point2DInteger;

/**
 * Class that is a bean to be filled by the wiiuse API.
 * 
 * @author gduche
 * 
 */
public class WiiMoteEvent extends WiiUseApiEvent {

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

	private static short NB_POINTS = 4;// number of points IR can track

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
	 * Construct the Wiimote setting up the id.
	 * 
	 * @param id
	 *            the Wiimote id
	 */
	public WiiMoteEvent(int id) {
		super(id, WiiUseApiEvent.GENERIC_EVENT);
		IRPoints = new Point2DInteger[NB_POINTS];
	}

	/**
	 * Construct the Wiimote setting up the id and the buttons.
	 * 
	 * @param id
	 *            the Wiimote id
	 * @param buttonsJustPressed
	 *            buttons just pressed
	 * @param buttonsJustReleased
	 *            buttons just released
	 * @param buttonsHeld
	 *            buttons held
	 */
	public WiiMoteEvent(int id, short buttonsJustPressed,
			short buttonsJustReleased, short buttonsHeld) {
		super(id, WiiUseApiEvent.GENERIC_EVENT);
		IRPoints = new Point2DInteger[NB_POINTS];
		setAllButtons(buttonsJustPressed, buttonsJustReleased, buttonsHeld);
	}

	/**
	 * Construct the Wiimote setting up the id, the buttons and the infared.
	 * 
	 */

	/**
	 * Construct the Wiimote setting up the id, the buttons, the accelerometer
	 * and the infrared.
	 * 
	 */

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
	private void setAllButtons(short buttonsJustPressed,
			short buttonsJustReleased, short buttonsHeld) {
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
	public void addIRpoint(int x, int y) {
		isIrActive = true;
		for (int i = 0; i < IRPoints.length; i++) {
			if (IRPoints[i] == null) {
				IRPoints[i] = new Point2DInteger(x, y);
				return;
			}
		}
		return;
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
	public void setOrientationAndGforce(float r, float p, float ya, float x,
			float y, float z) {
		this.isMotionSensingActive = true;
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
		String out = "";
		/* Status */
		out += "/*********** GENERIC EVENT : WIIMOTE   ID :"
				+ super.getWiimoteId() + " ********/\n";
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
