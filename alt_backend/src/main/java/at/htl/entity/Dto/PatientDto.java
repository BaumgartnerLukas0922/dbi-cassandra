package at.htl.entity.Dto;

public record PatientDto(
        String id,
        String firstName,
        String lastName,
        double height,
        double weight,
        String ssn,
        boolean isDiagnosed,
        boolean isCurrentlyInHospital,
        String stationId
) {
}
