/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 * Activity class
 * @author hecto
 */
public class Activity implements Serializable{
    // private attributes, public methods
    private int ID;
    private String description;
    private int effort;
    private String incharge;
    private int type;
    private String name;

    /**
     * The constructor requires all class attributes
     * @param ID
     * @param descripcion
     * @param esfuerzo
     * @param encargado
     * @param tipo
     * @param name 
     */
    public Activity(int ID, String description, int effort, String incharge, int type, String name) {
        this.ID = ID;
        this.description = description;
        this.effort = effort;
        this.incharge = incharge;
        this.type = type;
        this.name = name;
    }

    /**
     * Gets the activity's ID
     * @return 
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the activity's ID
     * @param ID 
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Obtains the activity's description
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the activity's description 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtains the activity's effort
     * @return 
     */
    public int getEffort() {
        return effort;
    }

    /**
     * Sets the activity's effort
     * @param effort 
     */
    public void setEffort(int effort) {
        this.effort = effort;
    }

    /**
     * Obtains the name of the activity's person in charge
     * @return 
     */
    public String getInCharge() {
        return incharge;
    }

    /**
     * Sets the activity's person in charge
     * @param incharge 
     */
    public void setInCharge(String incharge) {
        this.incharge = incharge;
    }

    /**
     * Obtains the activity's type
     * @return 
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the activity's type
     * @param type 
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets the activity's name
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the activity's name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
