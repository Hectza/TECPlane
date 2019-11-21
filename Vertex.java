/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 * Vertex class
 * @author hecto
 */
public class Vertex implements Serializable{
    // private attributes, public methods
    final private String id;
    final private String name;

    /**
     * Constructor of the class. Requires all attributes
     * @param id
     * @param name 
     */
    public Vertex(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * Returns the ID of the vertex
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the vertex
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Hash function of the vertex
     * @return 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Allows the vertex to be equated to objects
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * Returns the name of the vertex
     * @return 
     */
    @Override
    public String toString() {
        return name;
    }

}