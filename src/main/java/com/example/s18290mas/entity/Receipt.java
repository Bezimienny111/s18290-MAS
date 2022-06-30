package com.example.s18290mas.entity;

import com.example.s18290mas.model.Payment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Getter
    @Setter
    @OneToMany(mappedBy="receipt")
    private Set<Repair> repairSet;

    @Getter
    @Setter
    @OneToMany(mappedBy = "receipt")
    Set<GoodReceipt> goodReceiptSet;




    public void addRepair(Repair rep) throws Exception {
        if(getRepairSet().contains(rep))
            throw new Exception("Bład");
        else
        getRepairSet().add(rep);
    }

    public float summary(){
        Float sum = 0.0f;
        for (GoodReceipt gr: goodReceiptSet) {
            sum += (gr.quantity*gr.good.getPrice());
        }
        for (Repair rep: repairSet
             ) {
            sum += rep.getTotalPrice();
        }
        return sum;
    }
}