package com.example.s18290mas.entity;

import com.example.s18290mas.model.JobPosition;
import com.example.s18290mas.model.State;
import com.example.s18290mas.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Getter
    @Setter
    private String serialNumber;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String Brand;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Type type;

    public Client getClient() {
        return owner;
    }

    public void setClient(Client client) {
        this.owner = client;
    }

    @OneToMany(mappedBy = "device")
    private Set<DeviceAccesorie> accesorieSet;

    @OneToMany(mappedBy="deviceRep")
    private Set<Repair> repairs;

    public Device(Client owner, String serialNumber, String description, String brand, Type type) {
        this.owner = owner;
        this.serialNumber = serialNumber;
        this.description = description;
        this.Brand = brand;
        this.type = type;
    }
}