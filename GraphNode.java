/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 * Class Graph Node
 * @author hecto
 */
public class GraphNode implements Serializable{
     // Public attributes for ease of use
    public String alias;
    public Object data;

    /**
     * Constructor requires both class attributes.
     * @param alias
     * @param data 
     */
    GraphNode(String alias, Object data) {
        this.alias = alias;
        this.data = data;
    }
}