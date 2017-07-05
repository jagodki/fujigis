/*
 * Copyright (C) 2017 Christoph/Jagódki
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
 * This class stores the information about one geometric Line.
 * A line is builded up by multiple vertices, at minimum 2.
 * The order of the vertices corresponds to the direction of the line.
 * @author Christoph
 */
public class Line extends Geometry {
    
    private ArrayList<Point> vertices;
    private float lineWidth;

    /**
     * The empty constructor of the class.
     */
    public Line() {
        this.vertices = new ArrayList<>();
        super.setRgb(0, 0, 0);
    }

    /**
     * The constructor of this class.
     * The size of the given ArrayList has to be 2 or more,
     * otherwise an empty line will be constructed.
     * @param vertices an ArrayList of points representing the vertices of the line
     */
    public Line(ArrayList<Point> vertices) {
        if(vertices.size() >= 2) {
            this.vertices = vertices;
        } else {
            this.vertices = new ArrayList<>();
        }
        this.lineWidth = 2.0f;
        super.setRgb(0, 0, 0);
    }
    
    /**
     * This function calculates the length of the whole line of the current object.<br>
     * The function calculates the euklidian distance between 2 following vertices
     * and adds the result to the given parametre <code>length</code>.
     * The function is self-calling for calculating the length of the whole line.
     * @param i an int-value for iterating over the ArrayList of vertices
     * @param length variable where the result (and the temporary results) will be stored
     * @return the length of the current and all previous parts of the line
     */
    private double calculateLength(int i, double length) {
        if ((i + 1) < vertices.size()) {
            double deltaX = vertices.get(i + 1).getXCoord()- vertices.get(i).getXCoord();
            double deltaY = vertices.get(i + 1).getYCoord()- vertices.get(i).getYCoord();
            double deltaZ = vertices.get(i + 1).getZCoord()- vertices.get(i).getZCoord();
            length += Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
            length = this.calculateLength(i + 1, length);
        }
        return length;
    }
    
    /**
     * This function returns the length of the line.
     * The unit of the result depends on the CRS of the geometry, i.e.
     * geographic coordinates (e.g. EPSG:4326) should be projected to another
     * coordinate system.
     * @return the length as double value
     */
    public double getLength() {
        double length = this.calculateLength(0, 0.0);
        return length;
    }

    /**
     * This function returns all the vertices of the line as a String-object.
     * @return a String containing the type of the geometry and the vertices with their coordinates
     */
    @Override
    public String toString() {
        String lineAsString = "Line{vertices=";
        for(int i = 0; i < vertices.size(); i++) {
            lineAsString += vertices.get(i).toString();
            if(i != (vertices.size() - 1)) {
                lineAsString += ", ";
            }
        }
        lineAsString += "}";
        return lineAsString;
    }
    
    /**
     * This function adds a new vertice at the given position (zero-based index).
     * @param p the new vertice as Point-object
     * @param position the zero-based position for inserting
     */
    public void addVertice(Point p, int position) {
        this.vertices.add(position, p);
    }
    
    /**
     * This function inserts a new vertice at the end of the current line.
     * @param p the new vertice as Point-object
     */
    public void addVertice(Point p) {
        this.vertices.add(p);
    }
    
    /**
     * This function removes a vertice from the line at the given position.
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
     * This function paints the geometry to a JPanel.
     * <br>Antialiasing is activated.
     * <br>The colour of the geometry is defined as
     * attributes of the parent class.
     * <br>The line end is CAP_BUTT and the joins are round.
     * @param g a Graphics-object for drawing the geometry
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(super.getRed(), super.getGreen(), super.getBlue(), super.getOpacity()));
        g2.setStroke(new BasicStroke(this.lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        
        Path2D line = new Path2D.Double();
        boolean isNotFirst = false;
        for(Point vertice : this.vertices) {
            if (isNotFirst) {
                line.lineTo(vertice.getXCoord(), vertice.getYCoord());
            } else {
                line.moveTo(vertice.getXCoord(), vertice.getYCoord());
            }
        }
        
        super.graphicObject = line;
        g2.draw(super.graphicObject);
    }

    /**
     * This function returns the width of the line.
     * @return the width as float value
     */
    public float getLineWidth() {
        return lineWidth;
    }

    /**
     * This function sets the width of the current line.
     * @param lineWidth the new width of the line as float value
     */
    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }
    
}
