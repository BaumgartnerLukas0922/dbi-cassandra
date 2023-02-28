package at.htl.cassandra.patient;

import at.htl.cassandra.entity.Patient;
import at.htl.cassandra.entity.Station;
import at.htl.cassandra.station.QueryStationDao;
import at.htl.cassandra.station.StationDao;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Patient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    @Inject
    PatientDao dao;

    @Inject
    QueryPatientDao extendedDao;

    @GET
    @Path("getAll")
    public List<Patient> getAllPatients() {
        return dao.findAll().all();
    }
}
