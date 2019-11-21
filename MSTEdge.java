/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 * Class MST Edge
 * @author hecto
 */
public class MSTEdge implements Serializable{
    // private attributes, public methods
    public GraphNode src;
    public GraphNode dest;
    public Integer cost;

    /**
     * Constructor of the edge. Requires all class attributes
     * @param src
     * @param dest
     * @param cost 
     */
    MSTEdge(GraphNode src, GraphNode dest, Integer cost) {
        this.src = src;
        this.dest = dest;
        this.cost = cost;
    }
}