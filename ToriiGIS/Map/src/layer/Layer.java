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

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This class contains all geometries, which are connected to one layer.
 * @author Christoph
 */
public class Layer {
    
    private ArrayList<Geometry> geometryList;
    private ArrayList<ArrayList<Geometry>> multiGeometryList;
    private ArrayList<Attributes> attributeList;
    private Polygon boundingBox;
    private boolean point;
    private boolean line;
    private boolean circle;
    private boolean polygon;
    private String crs;
    private static final Logger LOG = Logger.getLogger(Layer.class.getName());

    /**
     * The empty constructor of this class.
     */
    public Layer() {
        this.geometryList = new ArrayList<>();
        this.attributeList = new ArrayList<>();
        this.multiGeometryList = new ArrayList<>();
    }

    /**
     * The constructor of this class to initialise the list of geometries.
     * The bounding box of the given geometries will be calculated at the end.
     * @param geometryList an ArrayList of Geometry-objects
     * @param attributeList an ArrayList of Attributes-objects
     * @param multiGeometryList an ArrayList of Multi-Geometries, i.e. a collection of ArrayLists
     */
    public Layer(ArrayList<Geometry> geometryList,
                 ArrayList<Attributes> attributeList,
                 ArrayList<ArrayList<Geometry>> multiGeometryList) {
        this.geometryList = geometryList;
        this.attributeList = attributeList;
        this.multiGeometryList = multiGeometryList;
        this.calculateBoundingBox();
    }
    
    /**
     * This function adds a new geometry to the layer.
     * The bounding box of the layer will be updated.
     * @param geom an object of type Geometry
     */
    public void addGeometry(Geometry geom) {
        this.geometryList.add(geom);
        this.calculateBoundingBox();
    }
    
    /**
     * This function adds a new multi-geometry to the layer.
     * The bounding box of the layer will be updated.
     * @param multiGeom an ArrayList of Geometries
     */
    public void addMultiGeometry(ArrayList<Geometry> multiGeom) {
        this.multiGeometryList.add(multiGeom);
        this.calculateBoundingBox();
    }
    
    /**
     * This function adds a new multi-geometry to the layer.
     * The bounding box of the layer will be updated.
     * @param attributes an object of type Attributes
     */
    public void addAttribute(Attributes attributes) {
        this.attributeList.add(attributes);
    }
    
    /**
     * This function calculates the bounding box of all geometries in the current object.
     */
    private void calculateBoundingBox() {
        if(this.geometryList.size() > 0) {
            
        } else if(this.multiGeometryList.size() > 0) {
            
        }
    }
    
    /**
     * This function removes all geometries of the current layer.
     */
    public void clearLayer() {
        this.geometryList.clear();
        this.multiGeometryList.clear();
        this.attributeList.clear();
    }

    /**
     * This function returns the bounding box of the current layer.
     * @return 
     */
    public Polygon getBoundingBox() {
        return boundingBox;
    }
    
    /**
     * This function checks all the geometries and multi-geometries of the current
     * layer-object.
     */
    private void checkGeometryTypes() {
        this.geometryList.forEach((currentGeom) -> {
            this.checkGeometryType(currentGeom);
        });
        this.multiGeometryList.forEach((currentMultiGeom) -> {
            currentMultiGeom.forEach((currentGeom) -> {
                this.checkGeometryType(currentGeom);
            });
        });
    }
    
    /**
     * This function checks the type of the given geometry and sets the corresponding
     * member variable to TRUE.
     * @param geom an object of Type Geometry
     */
    private void checkGeometryType(Geometry geom) {
        if(geom.getClass().getName().equals("Point")) {
            this.point = true;
        }
        if(geom.getClass().getName().equals("Line")) {
            this.line = true;
        }
        if(geom.getClass().getName().equals("Polygon")) {
            this.polygon = true;
        }
        if(geom.getClass().getName().equals("Circle")) {
            this.circle = true;
        }
    }
    
    /**
     * This function returns the information, wether the layer has at minimum one geometry.
     * @return TRUE if the layer contains geometries, otherwise false
     */
    public boolean hasGeometry() {
        return (this.point || this.line || this.polygon || this.circle);
    }
    
    /**
     * This function returns the coordinate reference system (CRS) of the current
     * geometry.
     * @return the EPSG-code as String
     */
    public String getCrs() {
        return crs;
    }

    /**
     * This function sets the CRS as EPSG-Code of the geometry
     * @param crs a String representing the EPSG-code of the geometry
     */
    public void setCrs(String crs) {
        this.crs = crs;
    }
    
}
