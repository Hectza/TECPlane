/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 * Edge class
 * @author hecto
 */
public class Edge implements Serializable{
     // private attributes, public methods
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    /**
     * Constructor for an edge. It requires all class attributes
     * @param id
     * @param source
     * @param destination
     * @param weight 
     */
    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Gets the edge's ID
     * @return 
     */
    public String getId() {
        return id;
    }
    
    /**
     * Gets the edge's destination
     * @return 
     */
    public Vertex getDestination() {
        return destination;
    }

    /**
     * Gets the source of the edge
     * @return 
     */
    public Vertex getSource() {
        return source;
    }
    
    /**
     * Gets the weight of the edge
     * @return 
     */
    public int getWeight() {
        return weight;
    }

    // Converts edge contents to string
    @Override
    public String toString() {
        return source + " " + destination;
    }


}