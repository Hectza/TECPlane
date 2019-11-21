/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * Graph class
 * @author hecto
 */

import java.io.Serializable;
import java.util.List;

public class Graph implements Serializable{
     // private attributes, public methods
    private final List<Vertex> vertexes;
    private final List<Edge> edges;
    
    /**
     * Constructor for a new graph. It requires both class attributes
     * @param vertexes
     * @param edges 
     */
    public Graph(List<Vertex> vertexes, List<Edge> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    /**
     * Gets the vertexes of the graph
     * @return 
     */
    public List<Vertex> getVertexes() {
        return vertexes;
    }

    /**
     * Gets the edges of the graph
     * @return 
     */
    public List<Edge> getEdges() {
        return edges;
    }
}