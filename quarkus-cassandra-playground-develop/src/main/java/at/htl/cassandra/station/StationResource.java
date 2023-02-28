package at.htl.cassandra.station;

import at.htl.cassandra.condition.ConditionDao;
import at.htl.cassandra.condition.QueryConditionDao;
import at.htl.cassandra.entity.Condition;
import at.htl.cassandra.entity.Station;
import at.htl.cassandra.patient.QueryPatientDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Station")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StationResource {
    @Inject
    StationDao dao;

    @Inject
    QueryStationDao extendedDao;
    @Inject
    QueryPatientDao extendedPatientDao;

    @GET
    @Path("getAll")
    public List<Station> getAllStations() {
        return dao.findAll().all();
    }

    @GET
    @Path("getById")
    public List<Station> getStationById(@QueryParam("id") Long stationId) {
        return extendedDao.findStationById(stationId);
    }

    @GET
    @Path("getAmountOnStation")
    public int getAmountOnStation(@QueryParam("id") Long stationId){
        return extendedPatientDao.findPatientByStation(stationId).size();
    }
}
