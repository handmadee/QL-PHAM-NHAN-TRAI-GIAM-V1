package com.mycompany.ql.pham.nhan.trai.giam.v1.src.models;

import java.util.ArrayList;
import java.util.List;

public class Prison {
    private static int idCounter = 1; 
    private int id;
    private String namePrison;
    private String location;
    private int capacity;
    private List<Prisoner> prisoners;

    public Prison(String namePrison, String location, int capacity) {
        this.id = idCounter++;  
        this.namePrison = namePrison;
        this.location = location;
        this.capacity = capacity;
        this.prisoners = new ArrayList<>();
    }

    // Getters và Setters
    public int getId() { return id; }
    public String getNamePrison() { return namePrison; }
    public void setNamePrison(String namePrison) { this.namePrison = namePrison; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public List<Prisoner> getPrisoners() { return prisoners; }

    public void addPrisoner(Prisoner prisoner) {
        prisoners.add(prisoner);
    }

    @Override
    public String toString() {
        return "Prison{id=" + id + ", namePrison='" + namePrison + "', location='" + location + 
               "', capacity=" + capacity + ", prisoners=" + prisoners + '}';
    }
}
