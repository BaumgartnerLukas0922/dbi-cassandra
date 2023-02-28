package at.htl.cassandra.entity;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Entity(defaultKeyspace = "dbi")
@PropertyStrategy(mutable = false)
public class Diagnosis {
    @PartitionKey
    private Long id;

    //public MedicalStaff medicalStaff;

    public int daysInHospital;

    public LocalDateTime diagnosedOn;

    private String conditionName;
    private String description;

    public Condition condition;

    public Customer customer;

}