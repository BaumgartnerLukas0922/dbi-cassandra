package at.htl.cassandra.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientWithStationDTO {

    private Long id;
    private String ssn;
    private String firstName;
    private String lastName;
    private String StationName;

    public PatientWithStationDTO(Long id, String ssn, String firstName, String lastName, String stationName) {
        this.id = id;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        StationName = stationName;
    }
}
