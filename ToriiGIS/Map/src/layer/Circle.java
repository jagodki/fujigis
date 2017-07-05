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
 * This class represents a geometric circle.
 * A circle is defined by its centre point and its radius.
 * The radius is used for painting on the map too.
 * @author Christoph
 */
public class Circle extends Surface {
    
    private Point centrePoint;
    private double radius;
    
    /**
     * The empty constructor of this class.
     */
    public Circle() {
        this.centrePoint = new Point();
        this.radius = 0.0;
        super.setRgb(0, 0, 0);
    }

    /**
     * The constructor of this class.
     * The circle is builded by using a point as centre and a radius.
     * @param centrePoint a Point-object representing the centre of the circle
     * @param radius the radius of the circle as double value
     */
    public Circle(Point centrePoint, double radius) {
        this.centrePoint = centrePoint;
        this.radius = radius;
        super.setRgb(0, 0, 0);
    }
    
    /**
     * The constructor of this class.
     * The circle is builded by using a point with X-, Y- and Z-coordinates
     * as centre and a radius.
     * @param x the easting of the centre point as double value
     * @param y the northing of the centre point as double value
     * @param z the height of the centre point as double value
     * @param radius the radius of the circle as double value
     */
    public Circle(double x, double y, double z, double radius) {
        this.centrePoint = new Point(x, y, z);
        this.radius = radius;
    }

    /**
     * This function returns the area of the circle.
     * The unit of the result depends on the CRS of the geometry, i.e.
     * geographic coordinates (e.g. EPSG:4326) should be projected to another
     * coordinate system.
     * @return the area in square-units as double-value
     */
    @Override
    public double getArea() {
        double area = this.calculateArea();
        return area;
    }
    
    /**
     * This function returns the perimeter of the circle.
     * The unit of the result depends on the CRS of the circle, i.e.
     * geographic coordinates (e.g. EPSG:4326) should be projected to another
     * coordinate system.
     * @return the perimeter as double value
     */
    @Override
    public double getPerimeter() {
        double perimeter = this.calculatePerimeter();
        return perimeter;
    }
    
    /**
     * This function calculates the area of a circle.
     * @return the area of the circle
     */
    protected double calculateArea() {
        double area = Math.PI * this.radius * this.radius;
        return area;
    }
    
    /**
     * This function calculates the perimeter of a polygon.
     * @return the perimeter as double value
     */
    @Override
    protected double calculatePerimeter() {
        double perimeter = 2 * Math.PI * this.radius;
        return perimeter;
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
        super.graphicObject = new Ellipse2D.Double(this.centrePoint.getXCoord(),
                                                   this.centrePoint.getYCoord(),
                                                   this.radius,
                                                   this.radius);
        g2.draw(super.graphicObject);
    }

    /**
     * This function returns all coordinates of the centre point and the radius as a String.
     * @return a String containing the type of the geometry and its components
     */
    @Override
    public String toString() {
        return "Circle{" + "centrePoint=" + centrePoint.toString() + ", radius=" + radius + '}';
    }

    /**
     * This function returns the centre point of the current circle.
     * @return the centre as Point-object
     */
    public Point getCentrePoint() {
        return centrePoint;
    }

    /**
     * This function sets a new centre point of the circle.
     * The old one will be replaced.
     * @param centrePoint the new centre as Point-object
     */
    public void setCentrePoint(Point centrePoint) {
        this.centrePoint = centrePoint;
    }

    /**
     * This function returns the radius of the circle.
     * @return the radius as double value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This function sets a new radius of the circle.
     * The old one will be replaced.
     * @param radius the new radius as double value
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }
    
}
