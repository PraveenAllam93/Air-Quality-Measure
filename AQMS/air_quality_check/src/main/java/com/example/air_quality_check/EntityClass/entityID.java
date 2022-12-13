package com.example.air_quality_check.EntityClass;

public class entityID {
    /* entity Id is used to identify each roq uniquely.  */
    private int id;

    public entityID() {}
    
    /* getter and setters for id */
    public entityID(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }
}
