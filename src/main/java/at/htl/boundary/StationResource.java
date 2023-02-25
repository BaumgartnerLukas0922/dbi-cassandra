package at.htl.boundary;

import at.htl.control.StationRepository;
import at.htl.entity.Station;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Station")
public class StationResource {
    @Inject
    StationRepository stationRepository;

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Station> getAllStations(){
        return stationRepository.listAll();
    }

    @Path("getById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Station getAllStations(@QueryParam("id") Long id){
        return stationRepository.findById(id);
    }

}

