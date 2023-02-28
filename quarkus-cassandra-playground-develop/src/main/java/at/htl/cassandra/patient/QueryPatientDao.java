package at.htl.cassandra.patient;

import at.htl.cassandra.entity.Patient;
import at.htl.cassandra.entity.Station;
import at.htl.cassandra.entity.dto.PatientDto;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QueryPatientDao {
    @Inject
    CqlSession cqlSession;

    @Inject
    PatientDao dao;

    public List<Patient> findPatientById(Long patientId) {
        List<Patient> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.patient WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", patientId);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Patient.builder()
                .id(c.getLong("id"))
                .firstName(c.getString("first_name"))
                .lastName(c.getString("last_name"))
                .ssn(c.getString("ssn"))
                .stationId(c.getLong("station_id"))
                .height(c.getDouble("height"))
                .weight(c.getDouble("weight"))
                .currentlyInHospital(c.getBoolean("currently_in_hospital"))
                .diagnosed(c.getBoolean("diagnosed"))
                .build())
        );
        return result;
    }

    public boolean checkSSN(String ssn){
        if (ssn.length() != 10)
            return false;

        int checkNumber = ssn.charAt(3)-'0';
        int[] checkArray = new int[] { 3,7,9, 0, 5, 8, 4, 2, 1, 6 };
        int checkSum = 0;

        for(int i =0; i < checkArray.length; i++)
        {
            int actNumber = ssn.charAt(i) - '0';
            checkSum += actNumber * checkArray[i];
        }

        return checkSum % 11 ==  checkNumber;
    }

    public List<Patient> getAllByStation(long stationId) {
        List<Patient> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.patient WHERE station_id = :id ALLOW FILTERING");
        BoundStatement completeStatement = query.bind().setLong("id", stationId);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Patient.builder()
                .id(c.getLong("id"))
                .firstName(c.getString("first_name"))
                .lastName(c.getString("last_name"))
                .ssn(c.getString("ssn"))
                .stationId(c.getLong("station_id"))
                .height(c.getDouble("height"))
                .weight(c.getDouble("weight"))
                .currentlyInHospital(c.getBoolean("currently_in_hospital"))
                .diagnosed(c.getBoolean("diagnosed"))
                .build())
        );
        return result;
    }

    public void save(PatientDto patientDto) {
        dao.update(
                Patient.builder()
                        .id(Long.valueOf(patientDto.getId()))
                        .firstName(patientDto.getFirstName())
                        .lastName(patientDto.getLastName())
                        .ssn(patientDto.getSsn())
                        .height(patientDto.getHeight())
                        .weight(patientDto.getWeight())
                        .currentlyInHospital(true)
                        .diagnosed(patientDto.isDiagnosed())
                        .build()
        );
    }

    public void assignStation(Patient patient) {
        dao.update(patient);
    }

    public void releasePatient(Patient patient) {
        dao.update(patient);
    }
}
