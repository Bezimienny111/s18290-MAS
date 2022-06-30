package com.example.s18290mas.model;

public enum State {
    GOOD("Sprawne"),
    BROKEN("Do Naprawy");

    private final String state;

    State(String State) {
        this.state = State;
    }

    public String getState(){
        if(this.state != null)
            return this.state;
        else return "";
    }
}
