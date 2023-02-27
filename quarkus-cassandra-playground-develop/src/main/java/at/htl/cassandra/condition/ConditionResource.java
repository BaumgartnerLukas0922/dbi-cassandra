package at.htl.cassandra.condition;


import at.htl.cassandra.entity.Condition;
import at.htl.cassandra.entity.dto.ConditionSymptomDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/Condition")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConditionResource {
    @Inject
    ConditionDao dao;

    @Inject
    QueryConditionDao extendedDao;

    @GET
    @Path("getAll")
    public List<Condition> getAllCustomers() {
        return dao.findAll().all();
    }

    @GET
    @Path("getById")
    public List<Condition> getPagedCustomersByCustomerNumber(@QueryParam("id") Long conditionId) {
        return extendedDao.findConditionById(conditionId);
    }


    @GET
    @Path("getConditionsBySymptoms")
    public List<ConditionSymptomDto> getPagedCustomersByCustomerNumber(@QueryParam("symptoms") List<String> symptoms) {
        return extendedDao.findConditionsBySymptom(symptoms);
    }
}
