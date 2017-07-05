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

/**
 * This class provides the attributes of a layer.
 * <br>Attributes can be described by their names, stored in an array of Strings,
 * and their values, stored in an ArrayList of String-arrays.
 * @author Christoph
 */
public class Attributes {
    
    private String[] attrNames;
    private ArrayList<String[]> attrs; 
    
    /**
     * The empty constructor of this class.
     */
    public Attributes() {
        this.attrNames = new String[0];
        this.attrs = new ArrayList<>();
    }

    /**
     * The constructor of this class.
     * @param attrNames an array of Strings containing the names of the columns
     * @param attrs an ArrayList of String-arrays containing the attribute data
     */
    public Attributes(String[] attrNames, ArrayList<String[]> attrs) {
        this.attrNames = attrNames;
        this.attrs = attrs;
    }

    /**
     * This function returns the names of all attributes
     * @return an array of Strings containing the column names
     */
    public String[] getAttrNames() {
        return this.attrNames;
    }

    /**
     * This function inserts a new set of attribute names. The old names will
     * be overwritten.
     * @param attrNames an array of Strings containing the new names of the attributes 
     */
    public void setAttrNames(String[] attrNames) {
        this.attrNames = attrNames;
    }

    /**
     * This function returns the name of one attribute given by its index.
     * @param i the zero-based index of the attribute
     * @return a String representing the attribute name
     */
    public String[] getAttrs(int i) {
        return this.attrs.get(i);
    }

    /**
     * This function adds a dataset of attributes at the end of the list of the
     * attribute data.
     * @param attrs an array of Strings containing the data of the new dataset
     */
    public void addAttrs(String[] attrs) {
        this.attrs.add(attrs);
    }
    
    /**
     * This function adds a new dataset of attributes at a given position
     * in the the list of the attributes.
     * @param attrs an array of Strings containing the data of the new dataset
     * @param i the zero-based index representing the position, where the new
     * dataset has to be interted
     */
    public void addAttrs(String[] attrs, int i) {
        this.attrs.add(i, attrs);
    }
    
    /**
     * This function returns the count of the datasets of attributes.
     * @return 
     */
    public int size() {
        return this.attrs.size();
    }
    
}
