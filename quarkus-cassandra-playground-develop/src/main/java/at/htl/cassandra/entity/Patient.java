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
public class Patient {

    @PartitionKey
    private Long id;

    private Long stationId;

    private boolean isCurrentlyInHospital;
    private boolean isDiagnosed;

    private double weight;
    private double height;

    private String ssn;

    private String firstName;
    private String lastName;
}
