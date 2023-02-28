package at.htl.cassandra.station;

import at.htl.cassandra.entity.Condition;
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
public class QueryStationDao {
    @Inject
    CqlSession cqlSession;

    public Station findStationById(Long stationId) {
        List<Station> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.station WHERE id = :id");
        BoundStatement completeStatement = query.bind().setLong("id", stationId);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        resultSet.all().forEach(c -> result.add(Station.builder()
                .totalNumberOfBeds(c.getInt("total_number_of_beds"))
                .id(c.getLong("id")).name(c.getString("name"))
                .build())
        );
        return result.get(0);
    }
}
