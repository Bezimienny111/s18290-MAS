package com.example.s18290mas.Embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
public class OwnerGoodKey implements Serializable {

    @Column(name ="owner_id")
    Long ownerId;

    @Column(name ="good_id")
    Long goodId;
}