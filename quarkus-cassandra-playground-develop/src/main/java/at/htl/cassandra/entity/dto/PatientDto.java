package at.htl.cassandra.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientDto {
    private String id;
    private String firstName;
    private String lastName;
    private double height;
    private double weight;
    private String ssn;
    private boolean isDiagnosed;
    private boolean isCurrentlyInHospital;
    private String stationId;
}
