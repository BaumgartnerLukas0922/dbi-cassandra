package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Diagnosis extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //@ManyToOne
    //public MedicalStaff medicalStaff;

    public int daysInHospital;

    public LocalDateTime diagnosedOn;

    @ManyToOne
    public Condition condition;

    @ManyToOne
    public Patient patient;
}