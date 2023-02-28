package at.htl.cassandra.patient;

import at.htl.cassandra.entity.Patient;
import at.htl.cassandra.entity.Station;
import at.htl.cassandra.entity.dto.PatientWithStationDTO;
import at.htl.cassandra.station.QueryStationDao;
import at.htl.cassandra.station.StationDao;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/Patient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    @Inject
    PatientDao dao;
    @Inject
    StationDao stationDao;
    @Inject
    QueryPatientDao extendedDao;

    @GET
    @Path("getAll")
    public List<Patient> getAllPatients() {
        return dao.findAll().all();
    }

    @GET
    @Path("getShortPatient")
    public List<PatientWithStationDTO> getShortPatients(){
        List<Patient> patients = dao.findAll().all();
        List<PatientWithStationDTO> shortPatients = new LinkedList<>();
        for (Patient patient:patients) {
            shortPatients.add(new PatientWithStationDTO(
                    patient.getId(),
                    patient.getSsn(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    stationDao.findAll().all()
                            .stream()
                            .filter(
                                    s -> s.getId() == patient.getStationId()
                            ).findFirst().get().getName())
            );
        }
        return shortPatients;
    }

    @GET
    @Path("getAllDiagnosed")
    public List<Patient> GetAllPatientsDiagnosed(@QueryParam("filter") boolean filter){
        return dao.findAll().all().stream().filter(p -> p.isDiagnosed() == filter).collect(Collectors.toList());
    }

    @GET
    @Path("getById")
    public Patient GetById(@QueryParam("id") Long id){
        return extendedDao.findPatientById(id).stream().findFirst().get();
    }

    @GET
    @Path("getByStation")
    public List<Patient> GetPatientByStation(@QueryParam("stationId") Long id){
        return extendedDao.findPatientByStation(id);
    }
    @GET
    @Path("getByLastName")
    public List<Patient> GetPatientByLastName(@QueryParam("lastName") String lastName){
        return extendedDao.findPatientByLastName(lastName);
    }

    @GET
    @Path("getBySSN")
    public List<Patient> GetPatientBySSN(@QueryParam("ssn") String ssn){
        return extendedDao.findPatientBySsn(ssn);
    }

    @GET
    @Path("checkSSN")
    public boolean CheckSsn(@QueryParam("ssn") String ssn){
        if(ssn.equals("")){
            return false;
        }
        return extendedDao.checkSSN(ssn);
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CreateNewPatient(Patient patient){
        dao.update(patient);
        return Response.ok().build();
    }

    @POST
    @Path("assignStation")
    public Response AssignToStation(@QueryParam("patientId") Long patientId, @QueryParam("stationId") Long stationId){

        Patient patient = dao.findAll().all().stream().filter(p -> Objects.equals(p.getId(), patientId)).findFirst().get();

        patient.setStationId(stationId);

        dao.update(patient);

        return Response.ok(patient).build();
    }

    @POST
    @Path("releasePatient")
    public Response ReleasePatient(@QueryParam("patientId") Long patientId){
        Patient patient = dao.findAll().all().stream().filter(p -> Objects.equals(p.getId(), patientId)).findFirst().get();
        patient.setCurrentlyInHospital(false);
        patient.setDiagnosed(false);
        patient.setStationId(000000000000000000000000l);
        dao.update(patient);
        return Response.ok(patient).build();
    }
}
