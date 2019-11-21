/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 * Dijkstra class
 * @author hecto
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra implements Serializable{
    // private attributes, public methods
    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    /**
     * Creates a new Dijkstra object with the nodes and vertexes of itself copied
     * @param graph 
     */
    public Dijkstra(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.getVertexes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /**
     * Executes the Dijkstra algorithm
     * @param source 
     */
    public void execute(Vertex source) {
        // Initiates the variables needed
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        // The stopping condition for the algorithm is when there are no nodes left to
        // mark as visited in the graph
        while (unSettledNodes.size() > 0) {
            // Obtains the smallest node in the list of unsettled nodes
            Vertex node = getMinimum(unSettledNodes);
            // adds the settled node to the list of settled nodes
            settledNodes.add(node);
            // removes the actual node from the unsettled nodes list
            unSettledNodes.remove(node);
            // finds the minimal distances from the actual node, which
            // updates the unsettled nodes list so this loop keeps 
            findMinimalDistances(node);
        }
    }

    /**
     * If an adjacent node has a greater distance than the one from the predecessor node to it, 
     * this method updates the distance of that adjacent node to be the sum of the distance of the predecessor node
     * plus the distance between the predecessor node and the adjacent node.
     * 
     * If a node is updated, it is added to the list of unsettled nodes.
     * @param node 
     */
    private void findMinimalDistances(Vertex node) {
        // obtains the adjacent nodes to the one passes as argument
        List<Vertex> adjacentNodes = getNeighbors(node);
        // iterates over the adjacent nodes
        for (Vertex target : adjacentNodes) {
            // checks if the old distance is greater than the distance from the predecessor node to it
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                // if it is, it updates the distance of that node in the HashMap
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                // creates a relationship of predecessor and successor in the predecessors hashmap
                predecessors.put(target, node);
                // adds the updates node to the unsettled nodes list, as it now ahs a new distance
                unSettledNodes.add(target);
            }
        }

    }

    /**
     * Gets the weight of the edge between two nodes
     * @param node
     * @param target
     * @return 
     */
    private int getDistance(Vertex node, Vertex target) {
        // iterates over all the edges in the graph
        for (Edge edge : edges) {
            // finds the edge which corresponds to the source and destination passed through as parameters
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                // returns the weight of the corresponding edge
                return edge.getWeight();
            }
        }
        // if an edge is requested which does not exist, the method throws an exception, altough it shouldn't happen
        throw new RuntimeException("Should not happen");
    }

    /**
     * Returns a list of the adjacent nodes to the one passed as argument that are not settled
     * @param node
     * @return 
     */
    private List<Vertex> getNeighbors(Vertex node) {
        // initiaites a new list to store the adjacents
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            // checks all the edges for the ones which source is the indicated node, and which are not settled already
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                // when it finds one, if it does, it adds it ot the list
                neighbors.add(edge.getDestination());
            }
        }
        // returns the list of adjacent nodes which are not settled
        return neighbors;
    }

    /**
     * Gets the minimum vertex in a set of vertexes
     * @param vertexes
     * @return 
     */
    private Vertex getMinimum(Set<Vertex> vertexes) {
        // initiates a new variable which will store the minimum vertex
        Vertex minimum = null;
        // iterates over all the vertexes in the set passed as argument
        for (Vertex vertex : vertexes) {
            // if the minimum is null (for example, during the first iteration), it assigns the current
            // vertex in the iteration to it
            if (minimum == null) {
                minimum = vertex;
            // if the minimum is not null, continues with the next check
            } else {
                // checks if the distance of the minimum is greater than the distance
                // of the current vertex of the iteration. If it is, assigns the current
                // vertex of the itteration to the minimum
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        // returns the minimum found
        return minimum;
    }

    /**
     * Returns true if the vertex is settled. Otherwise, it returns false
     * @param vertex
     * @return 
     */
    private boolean isSettled(Vertex vertex) {
        // checks if the sought after vertex exists in the set, and returns that boolean value
        return settledNodes.contains(vertex);
    }

    /**
     * Returns the minimum distance found in the hashmap of distances which
     * correspond to the vertex being requested.
     * @param destination
     * @return 
     */
    private int getShortestDistance(Vertex destination) {
        // initiates a new integer distance variable as the value stored in the hashmap of distances
        // which correspond to the vertex requested
        Integer d = distance.get(destination);
        // if it is null, which means it hasn't been updates, it assigns
        // the maximum possible value the variable can store. This with the intention of
        // making sure the next time it is checked in the "findMinimalDistances(...)" method,
        // it wil be updates and added to the unsettled nodes list for it to be revised.
        if (d == null) {
            return Integer.MAX_VALUE;
        }
        // if it isn't null, it just returns the value of the distance
        else {
            return d;
        }
    }

    /**
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     * @param target
     * @return 
     */
    public LinkedList<Vertex> getPath(Vertex target) {
        // initiates a path variable wich is a list of vertexes
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
