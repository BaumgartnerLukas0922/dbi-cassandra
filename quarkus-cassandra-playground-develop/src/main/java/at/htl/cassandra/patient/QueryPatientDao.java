package at.htl.cassandra.patient;

import com.datastax.oss.driver.api.core.CqlSession;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class QueryPatientDao {
    @Inject
    CqlSession cqlSession;
}
