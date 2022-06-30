package com.example.s18290mas.model;

public enum Payment {
    CARD("Karta"),
    CASH("Gotówka");

    private final String status;

    Payment(String Status) {
        this.status = Status;
    }

    public String getStatus(){
        if(this.status != null)
            return this.status;
        else return "";
    }
}
