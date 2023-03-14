package at.htl.cassandra.diagnosis;

import at.htl.cassandra.condition.ConditionDao;
import at.htl.cassandra.entity.Condition;
import at.htl.cassandra.entity.Diagnosis;
import at.htl.cassandra.entity.Symptom;
import at.htl.cassandra.entity.dto.ConditionSymptomDto;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class QueryDiagnosisDao {

    @Inject
    CqlSession cqlSession;

    @Inject
    DiagnosisDao dao;

    public Diagnosis findDiagnosisById(Long id) {
        List<Diagnosis> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.diagnosis WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", id);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Diagnosis.builder()
                .daysInHospital(c.getInt("daysInHospital"))
                .patientId(c.getLong("patientId"))
                .diagnosedOn(LocalDateTime.parse(c.getString("diagnosedOn")))
                .conditionId(c.getLong("conditionId"))
                .medicalStaffId(c.getLong("medicalStaffId"))
                .id(c.getLong("id"))
                .build())
        );
        return result.get(0);
    }

    /*
    public boolean deleteDiagnosis(Long id) {
        List<Diagnosis> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "DELETE FROM dbi.diagnosis WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", id);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Diagnosis.builder()
                .daysInHospital(c.getInt("daysInHospital"))
                .id(c.getLong("id"))
                .build())
        );
        return true;
    }*/


    public List<Diagnosis> getDiagnosisByPatient(Long id){
        List<Diagnosis> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.diagnosis WHERE patient_id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", id);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Diagnosis.builder()
                .daysInHospital(c.getInt("days_in_hospital"))
                //.patientId(c.getLong("patient_id"))
                .diagnosedOn(LocalDateTime.parse(c.getString("diagnosed_on")))
                //.conditionId(c.getLong("condition_id"))
                //.medicalStaffId(c.getLong("medical_staff_id"))
                .id(c.getLong("id"))
                .build())
        );
        return result;
    }


}
