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
    public Diagnosis getById(@QueryParam("id") Long diagnosisId) {
        return extendedDao.findDiagnosisById(diagnosisId);
    }


    @GET
    @Path("getForPatient")
    public List<Diagnosis> getDiagnosisByPatient(@QueryParam("id") Long patientId) {
        return extendedDao.getDiagnosisByPatient(patientId);
    }

    /*

    @DELETE
    public boolean deleteDiagnosis(@QueryParam("id") Long diagnosisId){
        return extendedDao.deleteDiagnosis(diagnosisId);
    }*/

    /*
    @POST
    @Path("create")
    public Response deleteDiagnosis(){
        return null;
    }
*/



}
