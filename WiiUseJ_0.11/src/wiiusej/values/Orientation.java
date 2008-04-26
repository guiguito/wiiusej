/**
 * This file is part of WiiuseJ.
 *
 *  WiiuseJ is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  WiiuseJ is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with WiiuseJ.  If not, see <http://www.gnu.org/licenses/>.
 */
package wiiusej.values;

/**
 * Class that represents the orientation of the wiimote.
 * @author guiguito
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
		 * @return the pitch
		 */
		public float getPitch() {
			return pitch;
		}

		/**
		 * @return the yaw
		 */
		public float getYaw() {
			return yaw;
		}
		
		@Override
		public String toString() {
			return "Orientation : (roll: "+roll+", pitch: "+pitch+", yaw: "+yaw+")";			
		}
	
}
