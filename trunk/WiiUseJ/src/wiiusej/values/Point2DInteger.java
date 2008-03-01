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

import java.awt.geom.Point2D;

/**
 * Class used for IR sources.
 * @author guiguito
 */
public class Point2DInteger extends Point2D {
	private int x;
	private int y;
	
	public Point2DInteger(int xx, int yy) {
		super();
		setLocation(xx,yy);
	}

	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double xx, double yy) {
		this.x = (int)xx;
		this.y = (int)yy;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

}
