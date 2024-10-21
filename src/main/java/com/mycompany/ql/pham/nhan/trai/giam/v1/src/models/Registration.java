package com.mycompany.ql.pham.nhan.trai.giam.v1.src.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Registration {
    private int id;
    private String name;
    private int uid;
    private String relationship;
    private String identification;
    private String address;
    private String reason;
    private String date;

    // Formatter cho ngày
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Registration(int id, String name, int uid, String relationship, 
                        String identification, String address, String reason, String date) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.relationship = relationship;
        this.identification = identification;
        this.address = address;
        this.reason = reason;
        this.date = date;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getUid() { return uid; }
    public String getRelationship() { return relationship; }
    public String getIdentification() { return identification; }
    public String getAddress() { return address; }
    public String getReason() { return reason; }
    public String getDate() { return date; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUid(int uid) { this.uid = uid; }
    public void setRelationship(String relationship) { this.relationship = relationship; }
    public void setIdentification(String identification) { this.identification = identification; }
    public void setAddress(String address) { this.address = address; }
    public void setReason(String reason) { this.reason = reason; }
    public void setDate(String date) { this.date = date; }


    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Date: " +date;
    }
}
