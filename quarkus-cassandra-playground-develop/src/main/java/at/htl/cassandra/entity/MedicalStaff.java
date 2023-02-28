package at.htl.cassandra.entity;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(defaultKeyspace = "dbi")
@PropertyStrategy(mutable = false)
public class MedicalStaff {
    @PartitionKey
    private Long id;
    private String firstName;
    private String lastName;

    private String dob;
    private float salary;
    private String hireDate;
    private String staffDesignation;
    private Long stationId;
    //public List<Seminar>? Seminars { get; set; }
}
