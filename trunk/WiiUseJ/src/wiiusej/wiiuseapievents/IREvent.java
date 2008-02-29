package wiiusej.wiiuseapievents;

import wiiusej.values.Point2DInteger;

/**
 * Class which represents an IR event.
 * @author guiguito
 *
 */
public class IREvent extends WiimoteEvent{

	/* IR Tracking */
	private Point2DInteger[] IRPoints;
	private short indexPoints = 0;

	private static short NB_POINTS = 4;// number of points IR can track
	
	/**
	 * Constructor for an infrared event.
	 * @param id id of the wiimote concerned.
	 */
	public IREvent(int id) {
		super(id);
		IRPoints = new Point2DInteger[NB_POINTS];
	}

	/**
	 * Get list of IR points.
	 * 
	 * @return the list of 2D points
	 */
	public Point2DInteger[] getIRPoints() {
		return java.util.Arrays.copyOfRange(IRPoints, 0, indexPoints);
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
		IRPoints[indexPoints] = new Point2DInteger(x, y);
		indexPoints++;
		return;
	}

	@Override
	public String toString() {
		String out = "";
		/* Display IR Tracking */
		out += "/******** IR Tracking ********/\n";
		out += "--- Active : true\n";
		out += "--- Seen points\n";
		for (int i = 0; i < IRPoints.length; i++) {
			if (IRPoints[i] != null) {
				out += IRPoints[i].toString();
			}
		}
		return out;
	}

}
