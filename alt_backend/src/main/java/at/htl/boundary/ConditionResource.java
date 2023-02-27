package at.htl.boundary;

import at.htl.control.ConditionRepository;
import at.htl.entity.Condition;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Condition")
public class ConditionResource {
    @Inject
    ConditionRepository conditionRepository;

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Condition> getAllConditions(){
        return conditionRepository.listAll();
    }

    @Path("getById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Condition getConditionById(@QueryParam("id") Long id){
        return conditionRepository.findById(id);
    }
    
/*    @Path("getConditionsBySymptoms/{symptoms}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Condition> getConditionBySymtoms(@QueryParam("symptoms") String[] symptoms){
        return null;
    }*/

}

