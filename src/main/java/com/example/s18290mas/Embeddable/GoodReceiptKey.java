package com.example.s18290mas.Embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
public class GoodReceiptKey implements Serializable {

    @Column(name ="receipt_id")
    Long receiptId;

    @Column(name ="good_id")
    Long goodId;
}