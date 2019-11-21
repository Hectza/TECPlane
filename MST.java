/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import com.google.common.collect.Sets;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;


/**
 * Class Minimum Spanning Tree
 * @author hecto
 */
public class MST implements Serializable{
    // Private attributes, public methods, except for the algorithm's code, 
    // which is a public static integer
    public static int MSTA = 0;
    
    // Graph nodes set
    private HashSet<HashSet<String>> universalNodes = new HashSet<>();

    // Kruskal graph nodes set
    private HashSet<HashSet<String>> kUniversal = new HashSet<>();
    // Kruskal graph edges set
    private ArrayList<MSTEdge> kruskalEdges = new ArrayList<>();
    private HashSet<String> subSet1, subSet2;
    
    // Prim graph node set || THIS CLASSED USED TO HAVE A PRIM ALGORITHM. IT DOESN'T ANYMORE.
    private HashSet<HashSet<String>> pUniversal = new HashSet<>();
    private ArrayList<MSTEdge> tempEdges = new ArrayList<>();

    /**
     * Adds an edge to the graph, and requires a source and destination, plus a weight of the edge
     * @param src
     * @param dest
     * @param cost 
     */
    public void addEdge(GraphNode src, GraphNode dest, Integer cost) {
        // Adds both nodes to the set of all nodes in the graph
        universalNodes.add(Sets.newHashSet(src.alias));
        universalNodes.add(Sets.newHashSet(dest.alias));
        // Checks wich algorithm is to be used
        switch (MSTA) {
            // Adds the edge to the corresponding set of edges
            case 0:
                kruskalEdges.add(new MSTEdge(src, dest, cost));
                break;
            case 1:
                // THIS CLASSED USED TO HAVE A PRIM ALGORITHM. IT DOESN'T ANYMORE.
                break;
        }
    }

    /**
     * Executes the Kruskal minimum spanning tree algorithm
     * @return 
     */
    public ArrayList<MSTEdge> kruskalMST() {
        // copies the set of nodes of the graph into a varaible called kUniversal.
        kUniversal = new HashSet<>(universalNodes);
        // sets the maximum amount of edges to be the amount of nodes - 1
        int edgesLimit = kUniversal.size() - 1;
        // creates a new array of edges and initiates it empty
        ArrayList<MSTEdge> MSTEdges = new ArrayList<>();
        // sorts the edges to be operated according to their cost
        kruskalEdges.sort(Comparator.comparing(edge -> edge.cost));
        // iterates over all the edges 
        for (MSTEdge edge : kruskalEdges) {
            // When the limit of edges has been met, it stops the loop
            if (MSTEdges.size() == edgesLimit) break;
            // Checks if the edge makes a cycle
            if (isAcyclic(edge.src.alias, edge.dest.alias, true)) {
                // if it doesn't, it removes the edges from the universal set of edges
                // and adds the edge to the edges of the actual tree.
                kUniversal.add(Sets.newHashSet(Sets.union(subSet1, subSet2)));
                kUniversal.remove(subSet1);
                kUniversal.remove(subSet2);
                MSTEdges.add(edge);
            }
        }
        // returns the list of edges of the MST
        return MSTEdges;
    }

    private boolean isAcyclic(String srcAlias, String destAlias, boolean mode) {
        subSet1 = new HashSet<>();
        subSet2 = new HashSet<>();
        HashSet<HashSet<String>> targetSet;
        targetSet = mode ? kUniversal : pUniversal;
        for (HashSet<String> subSet : targetSet) {
            if (subSet.contains(srcAlias)) subSet1 = subSet;
            if (subSet.contains(destAlias)) subSet2 = subSet;
            if (subSet1 == subSet2) return false;
        }
        return true;
    }
}