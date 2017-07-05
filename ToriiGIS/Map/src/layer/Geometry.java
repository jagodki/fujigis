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

import java.awt.Graphics;
import java.awt.Shape;
import java.util.logging.Logger;
import javax.swing.JPanel;
//import javafx.scene.shape.Shape;

/**
 * This class is an abstract implementation of a geometry.
 * All other geometry-classes extends this class and will succeed some variables 
 * (e.g. colour, logger and a Shape-object).
 * @author Christoph
 */
public abstract class Geometry extends JPanel {
    
    protected Shape graphicObject;
    private static final Logger LOG = Logger.getLogger(Geometry.class.getName());
    private int[] rgb = new int[3];
    private int opacity = 0;
    
    /**
     * This function sets a new colour in RGB-format.
     * @param red the red value between 0 and 255
     * @param green the green value between 0 and 255
     * @param blue the blue value between 0 and 255
     */
    public void setRgb(int red, int green, int blue) {
        this.rgb[0] = red;
        this.rgb[1] = green;
        this.rgb[2] = blue;
    }
    
    /**
     * This function returns the red value of the colour.
     * @return an int value between 0 and 255
     */
    public int getRed() {
        return this.rgb[0];
    }
    
    /**
     * This function returns the green value of the colour.
     * @return an int value between 0 and 255
     */
    public int getGreen() {
        return this.rgb[1];
    }
    
    /**
     * This function returns the blue value of the colour.
     * @return an int value between 0 and 255
     */
    public int getBlue() {
        return this.rgb[2];
    }

    /**
     * This function returns the opacity value of the colour.
     * @return an int value between 0 and 255
     */
    public int getOpacity() {
        return opacity;
    }

    /**
     * This function sets the opacity value.
     * @param opacity an int value between 0 and 255
     */
    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }
    
    /**
     * This function overrides the paintComponent-value from the JPanel-class.
     * @param g a Graphics-object for drawing the geometry
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}
