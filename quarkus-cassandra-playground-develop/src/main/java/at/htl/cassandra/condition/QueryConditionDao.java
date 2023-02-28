package at.htl.cassandra.condition;

import at.htl.cassandra.entity.Symptom;
import at.htl.cassandra.entity.dto.ConditionSymptomDto;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import at.htl.cassandra.entity.Condition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

@ApplicationScoped
public class QueryConditionDao {

    @Inject
    CqlSession cqlSession;

    @Inject
    ConditionDao dao;

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
        List<Condition> conditions = dao.findAll().all();
        List<ConditionSymptomDto> result = new ArrayList<>();

        for (Condition cond: conditions) {
            int symptomCount = 0;
            List<Symptom> symptomsList = getSymptomsForConditions(cond.getId());
            for (Symptom symptomFromCondition: symptomsList) {
                for (String symptomInList: symptoms) {
                    if(Objects.equals(symptomFromCondition.getSymptomName().toLowerCase(), symptomInList.toLowerCase())){
                        symptomCount ++;
                    }
                }
            }

            if(symptomCount > 0){
                result.add(ConditionSymptomDto
                        .builder()
                        .conditionName(cond.getConditionName())
                                .id(cond.getId())
                                .symptomCount(symptomCount)
                        .build());
            }
        }
        Collections.sort(result);
        return result;
    }

    public List<Symptom> getSymptomsForConditions(Long conditionId){
        List<Symptom> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.symptom WHERE condition_id = :id ALLOW FILTERING");
        BoundStatement completeStatement = query.bind().setLong("id", conditionId);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Symptom.builder()
                .symptomName(c.getString("symptom_name"))
                .id(c.getLong("id"))
                .build())
        );
        return result;
    }
}
