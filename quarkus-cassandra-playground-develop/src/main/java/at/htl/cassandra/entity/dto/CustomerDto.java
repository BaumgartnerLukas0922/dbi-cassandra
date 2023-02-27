package at.htl.cassandra.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private String customerNumber;
    private String description;
    private String state;
}
