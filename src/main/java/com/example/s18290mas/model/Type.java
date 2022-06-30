package com.example.s18290mas.model;

public enum Type {
    PRINTER("DRUKARKA"),
    NOTEBOOK("LAPTOP"),
    DESKTOP("PC"),
    ANOTHER("INNY");
    private final String type;

    Type(String Type) {
        this.type = Type;
    }

    public String getType(){
        if(this.type != null)
            return this.type;
        else return "";
    }
}
