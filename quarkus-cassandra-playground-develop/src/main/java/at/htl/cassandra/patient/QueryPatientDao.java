package at.htl.cassandra.patient;

import at.htl.cassandra.entity.Patient;
import at.htl.cassandra.entity.Station;
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

    public boolean checkSSN(String ssn){
        if (ssn.length() != 10)
            return false;

        int checkNumber = ssn.charAt(3) - '0';
        int[] checkArray = new int[]{3, 7, 9, 0, 5, 8, 4, 2, 1, 6};
        int checkSum = 0;

        for (int i = 0; i < checkArray.length; i++) {
            int actNumber = ssn.charAt(i) - '0';
            checkSum += actNumber * checkArray[i];
        }
        if(checkSum % 11 == checkNumber){
            return true;
        }
        return false;
    }

    public List<Patient> findPatientById(Long stationId) {
        List<Patient> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.patient WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", stationId);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Patient.builder().stationId(c.getLong("station_id"))
                .id(c.getLong("id")).firstName(c.getString("first_name"))
                .lastName(c.getString("last_name")).ssn(c.getString("ssn")).weight(c.getDouble("weight"))
                .height(c.getDouble("height")).currentlyInHospital(c.getBoolean("currently_in_hospital"))
                .diagnosed(c.getBoolean("diagnosed"))
                .build())
        );
        return result;
    }

    public List<Patient> findPatientByStation(Long stationId) {
        List<Patient> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.patient WHERE station_id = :station_id ALLOW FILTERING");
        BoundStatement completeStatement = query.bind().setLong("station_id", stationId);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Patient.builder().stationId(c.getLong("station_id"))
                .id(c.getLong("id")).firstName(c.getString("first_name"))
                .lastName(c.getString("last_name")).ssn(c.getString("ssn")).weight(c.getDouble("weight"))
                .height(c.getDouble("height")).currentlyInHospital(c.getBoolean("currently_in_hospital"))
                        .diagnosed(c.getBoolean("diagnosed"))
                .build())
        );
        return result;
    }

    public List<Patient> findPatientByLastName(String ln) {
        List<Patient> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.patient WHERE last_name = :ln ALLOW FILTERING");
        BoundStatement completeStatement = query.bind().setString("ln", ln);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Patient.builder().stationId(c.getLong("station_id"))
                .id(c.getLong("id")).firstName(c.getString("first_name"))
                .lastName(c.getString("last_name")).ssn(c.getString("ssn")).weight(c.getDouble("weight"))
                .height(c.getDouble("height")).currentlyInHospital(c.getBoolean("currently_in_hospital"))
                .diagnosed(c.getBoolean("diagnosed"))
                .build())
        );
        return result;
    }

    public List<Patient> findPatientBySsn(String ssn) {
        List<Patient> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.patient WHERE ssn = :ssn ALLOW FILTERING");
        BoundStatement completeStatement = query.bind().setString("ssn", ssn);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Patient.builder().stationId(c.getLong("station_id"))
                .id(c.getLong("id")).firstName(c.getString("first_name"))
                .lastName(c.getString("last_name")).ssn(c.getString("ssn")).weight(c.getDouble("weight"))
                .height(c.getDouble("height")).currentlyInHospital(c.getBoolean("currently_in_hospital"))
                .diagnosed(c.getBoolean("diagnosed"))
                .build())
        );
        return result;
    }
}
