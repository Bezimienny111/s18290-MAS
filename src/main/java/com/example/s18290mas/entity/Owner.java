package com.example.s18290mas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "owner")
public class Owner extends Person {
    @Getter
    @Setter
    private  int NIP;
    @Getter
    @Setter
    private  int REGON;

    @Getter
    @Setter
    @OneToMany(mappedBy = "owner")
    Set<OwnerGood> ownerGoods;


    public OwnerGood order(Good good, int quantity){
        return new OwnerGood(this,good,quantity);
    }

    // metody
    public boolean logIn(String log, String pass){
        if(log.equals(this.getLogin()) && pass.equals(this.getPass()))
            return true;
        else
            return false;
    }
}