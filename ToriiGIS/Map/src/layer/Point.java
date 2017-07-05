/*
 * Copyright (C) 2017 Christoph
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package layer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

/**
 * This class stores the information about one geometric Point.
 * A point contains 3 coordinates (x, y, z).
 * In 2D the z-value will be zero.
 * @author Christoph
 */
public class Point extends Geometry {
    
    private double x;
    private double y;
    private double z;
    private double radius;

    /**
     * The empty constructor of this class.
     */
    public Point() {
        super.setRgb(0, 0, 0);
    }

    /**
     * The constructor of this class.
     * @param x the x-component of the coordinate as double
     * @param y the y-component of the coordinate as double
     * @param z the z-component of the coordinate as double
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = 2.0;
        super.setRgb(0, 0, 0);
    }
    
    /**
     * This function paints the geometry to a JPanel.
     * <br>Antialiasing is activated.
     * <br>The colour of the geometry is defined as
     * attributes of the parent class.
     * @param g a Graphics-object for drawing the geometry
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(super.getRed(), super.getGreen(), super.getBlue(), super.getOpacity()));
        super.graphicObject = new Ellipse2D.Double(this.x,
                                                   this.y,
                                                   this.radius,
                                                   this.radius);
        g2.draw(super.graphicObject);
    }

    /**
     * This functions returns the X-coordinate of the current point.
     * @return the x-coordinate as double value
     */
    public double getXCoord() {
        return x;
    }

    /**
     * This function sets the X-coordinate of the current point.
     * @param x the new x-coordinate as double value
     */
    public void setXCoord(double x) {
        this.x = x;
    }

    /**
     * This functions returns the Z-coordinate of the current point.
     * @return the y-coordinate as double value
     */
    public double getYCoord() {
        return y;
    }

    /**
     * This function sets the Y-coordinate of the current point.
     * @param y the new y-coordinate as double value
     */
    public void setYCoord(double y) {
        this.y = y;
    }

    /**
     * This functions returns the Z-coordinate of the current point.
     * @return the z-coordinate as double value
     */
    public double getZCoord() {
        return z;
    }

    /**
     * This function sets the Z-coordinate of the current point.
     * @param z the new z-coordinate as double value
     */
    public void setZCoord(double z) {
        this.z = z;
    }

    /**
     * This functions returns the radius of the current point.
     * The radius is necessary to define the size of the point in the map.
     * @return the radius as double value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This function sets the radius of the current point.
     * The radius is necessary to define the size of the point in the map.
     * @param x the new radius as double value
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * This function returns the coordinates of the current point as a String-object
     * @return a String containing the type of geometry and the three coordinates
     */
    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
}
