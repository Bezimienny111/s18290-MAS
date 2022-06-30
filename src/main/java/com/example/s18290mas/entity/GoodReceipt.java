package com.example.s18290mas.entity;

import com.example.s18290mas.Embeddable.GoodReceiptKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "good_receipt")
public class GoodReceipt {

    @EmbeddedId
    GoodReceiptKey id;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("receiptId")
    @JoinColumn(name = "receipt_id")
    Receipt receipt;

    @Getter
    @Setter
    @ManyToOne
    @MapsId("goodId")
    @JoinColumn(name = "good_id")
    Good good;

    @Getter
    @Setter
    int quantity;
}