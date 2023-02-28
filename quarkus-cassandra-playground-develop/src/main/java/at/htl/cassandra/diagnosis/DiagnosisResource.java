package at.htl.cassandra.diagnosis;

import at.htl.cassandra.entity.Condition;
import at.htl.cassandra.entity.Diagnosis;
import at.htl.cassandra.entity.dto.ConditionSymptomDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Diagnosis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DiagnosisResource {
    @Inject
    DiagnosisDao dao;

    @Inject
    QueryDiagnosisDao extendedDao;

    @GET
    @Path("getAll")
    public List<Diagnosis> getAllDiagnosis() {
        return dao.findAll().all();
    }

    @GET
    @Path("getById")
    public List<Diagnosis> getById(@QueryParam("id") Long diagnosisId) {
        return extendedDao.findDiagnosisById(diagnosisId);
    }

    /*
    @GET
    @Path("getForPatient")
    public List<Condition> getPagedCustomersByCustomerNumber(@QueryParam("id") Long conditionId) {
        return extendedDao.findConditionById(conditionId);
    }


    @DELETE
    public Response deleteDiagnosis(){
        return null;
    }

    @POST
    @Path("create")
    public Response deleteDiagnosis(){
        return null;
    }
    */
     

}
