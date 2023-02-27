package at.htl.boundary;

import at.htl.control.PatientRepository;
import at.htl.control.StationRepository;
import at.htl.entity.Dto.PatientDto;
import at.htl.entity.Patient;
import at.htl.entity.Station;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("Patient")
public class PatientResource {

    @Inject
    PatientRepository patientRepository;

    @Inject
    StationRepository stationRepository;
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getAllPatients(){
        return patientRepository.listAll();
    }
    @Path("getById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatientById(@QueryParam("id") String id){
        Long tempId = Long.parseLong(id);
        return patientRepository.findById(tempId);
    }
    @Path("create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPatient(PatientDto patientDto){
        Patient patient = new Patient();
        patient.FirstName = patientDto.firstName();
        patient.LastName = patientDto.lastName();
        patient.SSN = patientDto.ssn();
        patient.Height = patientDto.height();
        patient.Weight = patientDto.weight();
        patient.IsCurrentlyInHospital = true;
        patient.IsDiagnosed = patientDto.isDiagnosed();
        patientRepository.persist(patient);
        return Response.ok().build();
    }
    @Path("checkSsn")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean checkSSN(@QueryParam("ssn") String ssn) throws Exception {
        return patientRepository.checkSsn(ssn);
    }

    @Path("assignStation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response assignStation(@QueryParam("patientId") String patientId, @QueryParam("stationId") String stationId){
        Patient patient = patientRepository.findById(Long.parseLong(patientId));
        Station station = stationRepository.findById(Long.parseLong(stationId));
        patient.station = station;
        patientRepository.persist(patient);
        return Response.ok().build();
    }

    @Path("releasePatient")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response releasePatien(@QueryParam("patientId") String patientId){
        Patient patient = patientRepository.findById(Long.parseLong(patientId));
        patient.IsCurrentlyInHospital = false;
        patient.station = null;
        patientRepository.persist(patient);
        return Response.ok().build();
    }

    @Path("getByStation")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getPatientsByStation(@QueryParam("stationId") String stationId) throws Exception {
        return patientRepository.getAllByStation(Long.parseLong(stationId));
    }

}
