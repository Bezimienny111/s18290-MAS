package com.example.s18290mas.entity;

import com.example.s18290mas.model.JobPosition;
import com.example.s18290mas.model.State;
import com.example.s18290mas.model.Status;
import com.example.s18290mas.model.Type;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder

@DynamicUpdate
public class Employee  extends  Person{

    @Id
    private Long id;
    @Getter
    @Setter
    private float BonusSalary;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private JobPosition position;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true,
            mappedBy = "employee"
    )
    @Builder.Default
    private List<Contract> contracts = new ArrayList<>();


    @Getter
    @Setter
    @ManyToMany(mappedBy = "repairedB")
    Set<Repair> repairsMade = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        return getId().hashCode();
    }


    public List<Contract> getContracts() {
        Collections.sort(contracts, new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });
        return contracts;
    }

    public List<Repair> GetListFromSet() {
        return repairsMade.stream().toList();
    }

    public String getName() {
        return super.getName();
    }

    public int getTotalRepairs() {
        return GetListFromSet().size();
    }
    public Employee getThis() {
        return this;
    }



    public Contract getLastContact(){
        return getContracts().get(0);
    }


    @OneToMany(mappedBy="employeeIn")
    private Set<Repair> repairsIn;

    @OneToMany(mappedBy="employeeOut")
    private Set<Repair> repairsOut;


    //metody projektowe

    public void changeStatusNext(Repair repair){
        repair.nextStatus();
    }
    public void changeStatusBack(Repair repair){
        repair.backStatus();
    }

    public void changeStatusNext(Repair repair, Status status){
        repair.changeStatus(repair,status);
    }

    public boolean logIn(String log, String pass){
        if(log.equals(this.getLogin()) && pass.equals(this.getPass()))
            return true;
        else
            return false;
    }

    public boolean repairAccesorie(DeviceAccesorie accesorie){
        if(this.getPosition().equals(JobPosition.SERVICE)) {
            if (accesorie.getState().equals(State.BROKEN)) {
                accesorie.setState(State.GOOD);
                return true;
            }else
                return false;
        }else {
            return false;
        }
    }

    public boolean addFix(Repair repair, String desc, Float price){
        if(this.getPosition().equals(JobPosition.SERVICE)) {
            repair.addFix(desc, price);
            return true;
        }
        else
        return  false;
    }


    public Client addClient(String name, String surname, String tel, String address) throws Exception {
        if(this.getPosition().equals(JobPosition.SALE)) {
            Client client = new Client(name,surname,tel,address);
                    return client;
        }
        else throw new Exception("Bład");

    }
    public Client addClient(String name, String surname, String tel, String address, int NIP) throws Exception {
        if(this.getPosition().equals(JobPosition.SALE)) {
            Client client = new Client(name,surname,tel,address,NIP);
            return client;
        }
        else throw new Exception("Bład");
    }

    public Device addDevice(Client owner, String serialNumber, String description, String brand, Type type) throws Exception {
        if(this.getPosition().equals(JobPosition.SALE)) {
            Device device = new Device(owner,serialNumber,description,brand,type);
            return device;
        }
        else throw new Exception("Bład");
    }
    public Repair addRepair(String description, Employee employeeIn, Device deviceRep) throws Exception {
        if(this.getPosition().equals(JobPosition.SALE)) {
            Repair rep= new Repair(description,employeeIn,deviceRep);
            return rep;
        }
        else throw new Exception("Bład");
    }
    public boolean endRepair(Repair repair) throws Exception {
        if(this.getPosition().equals(JobPosition.SALE) && repair.getStatus().equals(Status.DONE)) {
            repair.setStatus(Status.OUTOF);
            repair.setEmployeeOut(this);
            return true;
        }
        else
            return  false;
    }

}


