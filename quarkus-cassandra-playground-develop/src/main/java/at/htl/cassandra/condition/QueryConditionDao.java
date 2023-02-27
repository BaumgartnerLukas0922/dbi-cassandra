package at.htl.cassandra.condition;

import at.htl.cassandra.entity.dto.ConditionSymptomDto;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import at.htl.cassandra.entity.Condition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QueryConditionDao {

    @Inject
    CqlSession cqlSession;

    public List<Condition> findConditionById(Long id) {
        List<Condition> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.condition WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", id);
       ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Condition.builder()
                .conditionName(c.getString("condition_name"))
                .id(c.getLong("id"))
                .build())
        );
        return result;
    }

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
}
