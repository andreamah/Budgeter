package com.example.andre.budget;

import java.util.Date;

public class CostEntry {
    Date date;
    double cost;
    String name;
    String description;
    public CostEntry(String name) {
        this.name = name;
    }
    public CostEntry(Date date, int cost, String name, String description) {
        this.date = date;
        this.cost = cost;
        this.name = name;
        this.description = description;
    }

    public String getDate() {
        return date.toString();
    }

    public double getCost() {
        return cost;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }



}
