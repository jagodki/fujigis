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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.util.ArrayList;

/**
 * This class stores the information about one geometric Polygon.
 * A polygon is builded up by multiple vertices, at minimum 3.
 * The vertices have to be ordered, but the direction (clock-wise or not) is not important.
 * @author Christoph
 */
public class Polygon extends Surface {

    private ArrayList<Point> vertices;
    private float lineWidth;

    /**
     * The empty constructor of this class.
     */
    public Polygon() {
        this.vertices = new ArrayList<>();
        super.setRgb(0, 0, 0);
    }

    /**
     * The constructor of this class.
     * <br>If the given ArrayList has a size smaller than 3, an empty object
     * will be created.
     * @param vertices an ArrayList of Point-objects with size >= 3
     */
    public Polygon(ArrayList<Point> vertices) {
        if(vertices.size() >= 3) {
            this.vertices = vertices;
        } else {
            this.vertices = new ArrayList<>();
        }
        this.lineWidth = 2.0f;
        super.setRgb(0, 0, 0);
    }
    
    /**
     * This function inserts a new vertice at the end of the current polygon.
     * @param p the new vertice as Point-object
     */
    public void addVertice(Point vertice) {
        this.vertices.add(vertice);
    }
    
    /**
     * This function adds a new vertice at the given position (zero-based index).
     * @param p the new vertice as Point-object
     * @param position the zero-based position for inserting
     */
    public void addVertice(Point vertice, int position) {
        this.vertices.add(position, vertice);
    }
    
    /**
     * This function removes a vertice from the polygon at the given position.
     * @param position the zero-based index of the vertice, that has to be deleted
     */
    public void removeVertice(int position) {
        this.vertices.remove(position);
    }

    /**
     * This function returns the number of vertices of the current line.
     * @return the number of vertices
     */
    public int getCountOfVertices() {
        return this.vertices.size();
    }
    
    /**
     * This function returns all the vertices of the polygon as a String-object.
     * @return a String containing the type of the geometry and the vertices with their coordinates
     */
    @Override
    public String toString() {
        String polygonAsString = "Polygon{vertices=";
        for(int i = 0; i < vertices.size(); i++) {
            polygonAsString += vertices.get(i).toString();
            if(i != (vertices.size() - 1)) {
                polygonAsString += ", ";
            }
        }
        polygonAsString += "}";
        return polygonAsString;
    }

    /**
     * This function returns the area of the polygon.
     * The unit of the result depends on the CRS of the geometry, i.e.
     * geographic coordinates (e.g. EPSG:4326) should be projected to another
     * coordinate system.
     * @return the area in square-units as double-value
     */
    @Override
    public double getArea() {
        double area = this.calculateArea(0, 0.0);
        return area;
    }
    
    /**
     * This function returns the perimeter of the polygon.
     * The unit of the result depends on the CRS of the geometry, i.e.
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
     * This function calculates the area of a polygon with a functional
     * implementation of the Gauss's area formula/shoelace formula.
     * The area between a vertice (given by its index) and the following vertice
     * will be calculated. After this, the function is calling itself again
     * to loop over the whole list of vertices.
     * @param index an int-value for iterating over the ArrayList of vertices
     * @param area variable where the result (and the temporary results) will be stored
     * @return the area of the current and all previous parts of the polygon
     */
    protected double calculateArea(int index, double area) {
        if((index + 1) < this.vertices.size()) {
            Point pointA = this.vertices.get(index);
            Point pointB = this.vertices.get((index + 1) % this.vertices.size());
            area += (pointA.getXCoord()+ pointB.getXCoord()) * (pointA.getYCoord()- pointB.getYCoord());
            area = this.calculateArea(index + 1, area);
        }
        return Math.abs(0.5 * area);
    }
    
    /**
     * This function calculates the perimeter of a polygon.
     * With the vertices of the polygon a line will be created and
     * the length of the line will be returned.
     * @return the perimeter as double value
     */
    @Override
    protected double calculatePerimeter() {
        Line circularRing = new Line(vertices);
        return circularRing.getLength();
    }
    
    /**
     * This function paints the geometry to a JPanel.
     * <br>Antialiasing is activated.
     * <br>The colour of the geometry is defined as
     * attributes of the parent class.
     * <br>he joins of the boundary line are round.
     * <br>The filling of the polygon has a bigger value of opacity then the boundary line.
     * @param g a Graphics-object for drawing the geometry
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        //create a polygon by defining its boundary
        Path2D boundary = new Path2D.Double();
        boolean isNotFirst = false;
        for(Point vertice : this.vertices) {
            if (isNotFirst) {
                boundary.lineTo(vertice.getXCoord(), vertice.getYCoord());
            } else {
                boundary.moveTo(vertice.getXCoord(), vertice.getYCoord());
            }
        }
        boundary.closePath();
        super.graphicObject = boundary;
        
        //draw the boundary of the polygon
        g2.setColor(new Color(super.getRed(), super.getGreen(), super.getBlue(), super.getOpacity()));
        g2.setStroke(new BasicStroke(this.lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2.draw(super.graphicObject);
        
        //draw the inner of the polygon
        g2.setColor(new Color(super.getRed(), super.getGreen(), super.getBlue(), (int) 0.5 * super.getOpacity()));
        g2.fill(super.graphicObject);
    }

    /**
     * This function returns the width of the boundary line.
     * @return the width as float value
     */
    public float getLineWidth() {
        return lineWidth;
    }

    /**
     * This function sets the width of the current boundary line.
     * @param lineWidth the new width of the boundary line as float value
     */
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
    
}
