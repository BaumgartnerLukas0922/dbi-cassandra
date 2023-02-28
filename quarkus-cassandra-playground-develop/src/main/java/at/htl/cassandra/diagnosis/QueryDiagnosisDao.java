package at.htl.cassandra.diagnosis;

import at.htl.cassandra.condition.ConditionDao;
import at.htl.cassandra.entity.Diagnosis;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class QueryDiagnosisDao {

    @Inject
    CqlSession cqlSession;

    @Inject
    ConditionDao dao;

    public List<Diagnosis> findDiagnosisById(Long id) {
        List<Diagnosis> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.diagnosis WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", id);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Diagnosis.builder()
                .daysInHospital(c.getInt("daysInHospital"))
                .id(c.getLong("id"))
                .build())
        );
        return result;
    }

/*
    public List<ConditionSymptomDto> findConditionsBySymptom(List<String> symptoms) {
        List<ConditionSymptomDto> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT Count(*) FROM dbi.symptom WHERE symptom_name = :symptoms ALLOW FILTERING");
        BoundStatement completeStatement = query.bind().setString("symptoms", "Fever");
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(ConditionSymptomDto.builder()
                .conditionName("")
                .symptomCount(Integer.parseInt(String.valueOf(c.getLong(0))))
                .build())
        );
        return result;
    }

 */
}
