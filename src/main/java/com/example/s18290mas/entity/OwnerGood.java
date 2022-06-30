package com.example.s18290mas.entity;

import com.example.s18290mas.Embeddable.GoodReceiptKey;
import com.example.s18290mas.Embeddable.OwnerGoodKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "owner_good")
public class OwnerGood {
    @EmbeddedId
    OwnerGoodKey id;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("ownerId")
    @JoinColumn(name = "owner_id")
    Owner owner;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("goodId")
    @JoinColumn(name = "good_id")
    Good good;

    @Getter
    @Setter
    int quantity;

    public OwnerGood(Owner owner, Good good, int quantity) {
        this.owner = owner;
        this.good = good;
        this.quantity = quantity;
    }
}