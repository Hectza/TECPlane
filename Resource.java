/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 * Resource class
 * @author hecto
 */
public class Resource implements Serializable{
    private int ID;
    private String name;
    private String type;
    private int capacity;
    private int amountAvailable;
    private String incharge;

    /**
     * Constructor. Requires all attributes
     * @param ID
     * @param name
     * @param type
     * @param capacity
     * @param amountAvailable
     * @param incharge 
     */
    public Resource(int ID, String name, String type, int capacity, int amountAvailable, String incharge) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.amountAvailable = amountAvailable;
        this.incharge = incharge;
    }
    
    /**
     * Returns the ID of the resource
     * @return 
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the name of the resource
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the resource
     * @return 
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the capacity of the resource
     * @return 
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the amount available of the resource
     * @return 
     */
    public int getAmountAvailable() {
        return amountAvailable;
    }

    /**
     * Returns the name of the person in charge of the resource
     * @return 
     */
    public String getIncharge() {
        return incharge;
    }
}
