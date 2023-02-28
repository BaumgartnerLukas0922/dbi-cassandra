package at.htl.cassandra.medicalstaff;

import at.htl.cassandra.entity.MedicalStaff;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QueryMedicalStaffDao {
    @Inject
    CqlSession cqlSession;

    public List<MedicalStaff> findMedicalStaffById(Long id) {
        List<MedicalStaff> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.medical_staff WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", id);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(MedicalStaff.builder()
                .id(c.getLong("id"))
                .firstName(c.getString("first_name"))
                .lastName(c.getString("last_name"))
                .salary(c.getFloat("salary"))
                .dob(c.getString("dob"))
                .hireDate(c.getString("hire_date"))
                .stationId(c.getLong("station_id"))
                .build())
        );
        return result;
    }

    public void delete(MedicalStaff staff) {
        PreparedStatement query = cqlSession.prepare(
                "DELETE FROM dbi.medical_staff WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", staff.getId());
        cqlSession.execute(completeStatement);
    }
}
