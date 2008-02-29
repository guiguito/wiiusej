package wiiusej.values;

/**
 * Represents gravity force on each axis.
 * @author gduche
 *
 */
public class GForce {

	private float x;
	private float y;
	private float z;
	
	/**
	 * Default constructor;
	 */
	public GForce(){
		x = 0;
		y = 0;
		z = 0;
	}
	
	/**
	 * Constructor with gravity force on each axis.
	 * @param xx x value
	 * @param yy x value
	 * @param zz x value
	 */
	public GForce(float xx, float yy, float zz){
		x = xx;
		y = yy;
		z = zz;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public float getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(float z) {
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "Gravity force : ("+x+", "+y+","+z+")";
	}
}
