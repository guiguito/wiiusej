package wiiusej.values;

/**
 * Class that represents the orientation of the wiimote.
 * @author gduche
 *
 */
public class Orientation {
	
		private float roll;
		private float pitch;
		private float yaw;
		
		/**
		 * Default constructor.
		 */
		public Orientation(){
			roll = 0;
			pitch = 0;
			yaw = 0;
		}
		
		/**
		 * Contructor with raw, pitch , yaw.
		 * @param r raw
		 * @param p pitch
		 * @param y yaw
		 */
		public Orientation(float r, float p, float y){
			roll = r;
			pitch = p;
			yaw = y;
		}
		
		/**
		 * @return the roll
		 */
		public float getRoll() {
			return roll;
		}
		/**
		 * @param roll the roll to set
		 */
		public void setRoll(float roll) {
			this.roll = roll;
		}
		/**
		 * @return the pitch
		 */
		public float getPitch() {
			return pitch;
		}
		/**
		 * @param pitch the pitch to set
		 */
		public void setPitch(float pitch) {
			this.pitch = pitch;
		}
		/**
		 * @return the yaw
		 */
		public float getYaw() {
			return yaw;
		}
		/**
		 * @param yaw the yaw to set
		 */
		public void setYaw(float yaw) {
			this.yaw = yaw;
		}
		
		@Override
		public String toString() {
			return "Orientation : (roll: "+roll+", pitch: "+pitch+", yaw: "+yaw+")";			
		}
	
}
