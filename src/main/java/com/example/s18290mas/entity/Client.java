package com.example.s18290mas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Person {

    @Getter
    @Setter
    @Nullable
    private int NIP;

    @Nullable
    @OneToMany(mappedBy = "owner")
    private Set<Device> deviceSet;

    public Client(String name, String surname, String telefone, String adress) {
        super(name, surname, telefone, adress);
    }

    public Client(String name, String surname, String telefone, String adress, int NIP) {
        super(name, surname, telefone, adress);
        this.NIP = NIP;
    }


}