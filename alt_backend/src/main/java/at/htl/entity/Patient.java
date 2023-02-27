package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Patient extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    public Station station;

    public boolean IsCurrentlyInHospital;
    public boolean IsDiagnosed;

    public double Weight;
    public double Height;

    public String SSN;

    public String FirstName;
    public String LastName;

    public LocalDateTime DOB;
}
