package com.mycompany.ql.pham.nhan.trai.giam.v1.src.models;

public class Prisoner {
    private static int idCounter = 1; 
    private int id;
    private String name;
    private String date;
    private String crime;
    private String status;

    public Prisoner(String name, String date, String crime, String status) {
        this.id = idCounter++;  
        this.name = name;
        this.date = date;
        this.crime = crime;
        this.status = status;
    }

    // Getters và Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getCrime() { return crime; }
    public void setCrime(String crime) { this.crime = crime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Prisoner{id=" + id + ", name='" + name + "', date='" + date + 
               "', crime='" + crime + "', status='" + status + "'}";
    }
}
