package at.htl.cassandra.station;

import com.datastax.oss.driver.api.core.CqlSession;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class QueryStationDao {
    @Inject
    CqlSession cqlSession;

}
