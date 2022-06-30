package com.example.s18290mas.entity;

import com.example.s18290mas.model.JobPosition;
import com.example.s18290mas.model.State;
import com.example.s18290mas.model.Status;
import lombok.*;

import javax.persistence.*;
import javax.swing.text.Position;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Repairs")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String clauzule = "Zgadzam się na...";

    @Getter
    @ManyToOne
    @JoinColumn(name = "employeeIn_id", nullable = false)
    private Employee employeeIn;

    public void setEmployeeIn(Employee emp) throws Exception {
        if (emp.getPosition() != JobPosition.SALE)
            throw new Exception("Błąd, tylko sprzedawca moze przyjmować sprzęt na serwis");
        else
            this.employeeIn = emp;

    }

    @ManyToOne
    @JoinColumn(name = "employeeOut_id", nullable = true)
    private Employee employeeOut;

    public void setEmployeeOut(Employee emp) throws Exception {
        if (emp.getPosition() != JobPosition.SALE)
            throw new Exception("Błąd, tylko sprzedawca moze wydawać sprzęt na serwis");
        else
            this.employeeOut = emp;

    }

    public String getEmployeeOut() {
        if (employeeOut != null)
            return employeeOut.getSurname();
        else return "Brak";
    }

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "device_id", nullable = true)
    private Device deviceRep;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "receipt_id", nullable = true)
    private Receipt receipt;

    @Getter
    @Setter
    @OneToMany(mappedBy = "repair")
    private Set<Fix> fixes;


    public float getTotalPrice() {
        float tmp = 0.0f;

        for (Fix fx : fixes) {
            tmp += fx.getPrice();
            ;
        }

        return tmp;
    }

    @Getter
    @Setter
    @ManyToMany(
            fetch = FetchType.LAZY, cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "task",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    Set<Employee> repairedB = new HashSet<>();
    ;

    public List<Employee> servisants() {
        return repairedB.stream().toList();
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

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
        Repair other = (Repair) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }


    //metody projetkowe

    public void addDescription(String desc) {
        this.description = desc;
    }

    public void changeStatus(Repair rep, Status status) {
        rep.setStatus(status);
    }

    public void nextStatus() {
        if (this.getStatus() == Status.ONSERVICE)
            this.setStatus(Status.ONWORK);
        if (this.getStatus() == Status.ONWORK)
            this.setStatus(Status.DONE);
        if (this.getStatus() == Status.DONE)
            this.setStatus(Status.OUTOF);
    }

    public void backStatus() {
        if (this.getStatus() == Status.ONWORK)
            this.setStatus(Status.ONSERVICE);
        if (this.getStatus() == Status.DONE)
            this.setStatus(Status.ONWORK);
        if (this.getStatus() == Status.OUTOF)
            this.setStatus(Status.DONE);
    }

    public void addFix(String description, float price){
        fixes.add(new Fix(price,description));
    }

    public Repair(String description, Employee employeeIn, Device deviceRep) {
        this.description = description;
        this.clauzule = "Zgadzam się na";
        this.employeeIn = employeeIn;
        this.status = Status.ONSERVICE;
        this.deviceRep = deviceRep;
    }
}
