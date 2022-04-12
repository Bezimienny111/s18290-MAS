package com.example.s18290mas.model;

public enum JobPosition {
    SERVICE("Serwisant"),
    SALE("Sprzedawca");

    private final String position;

    JobPosition(String Position) {
        this.position = Position;
    }

    public String getJobPosition(){
        if(this.position != null)
            return this.position;
        else return "";
    }
}
