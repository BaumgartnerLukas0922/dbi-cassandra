package at.htl.cassandra.station;

import at.htl.cassandra.condition.ConditionDao;
import at.htl.cassandra.condition.QueryConditionDao;
import at.htl.cassandra.entity.Condition;
import at.htl.cassandra.entity.Station;

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
}
