package at.htl.boundary;

import at.htl.entity.Diagnosis;
import at.htl.control.DiagnosisRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Diagnosis")
public class DiagnosisResource {

    @Inject
    DiagnosisRepository diagnosisRepository;

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diagnosis> getAllDiagnosis(){
        return diagnosisRepository.listAll();
    }

    @Path("getById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Diagnosis getDiagnosisById(@QueryParam("id") Long id){
        return diagnosisRepository.findById(id);
    }

    @Path("getForPatient")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diagnosis> getDiagnosisByPatient(@QueryParam("id") Long id){
        //return diagnosisRepository.findById(id);
        return null;
    }

    /*
    @Path("create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Diagnosis> getDiagnosisByPatient(@QueryParam("id") Long id){
        //return diagnosisRepository.findById(id);
        return null;
    }
    */


    @Path("delete")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteDiagnosisById(@QueryParam("id") Long id){
        return diagnosisRepository.deleteById(id);
    }

}
