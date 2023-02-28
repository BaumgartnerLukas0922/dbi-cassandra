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
public class Station {
    @PartitionKey
    private Long id;
    private int totalNumberOfBeds;
    private String name;
}
