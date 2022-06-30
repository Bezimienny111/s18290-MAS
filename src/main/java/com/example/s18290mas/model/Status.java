package com.example.s18290mas.model;

public enum Status {
    ONSERVICE("Przyjęte"),
    ONWORK("W naprawie"),
    DONE("Zrobione"),
    OUTOF("Wydano");

    private final String status;

    Status(String Status) {
        this.status = Status;
    }

    public String getStatus(){
        if(this.status != null)
            return this.status;
        else return "";
    }
}
