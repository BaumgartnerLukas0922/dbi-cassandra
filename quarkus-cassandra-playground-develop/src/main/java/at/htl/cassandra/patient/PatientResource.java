package at.htl.cassandra.patient;

import at.htl.cassandra.entity.Patient;
import at.htl.cassandra.entity.Station;
import at.htl.cassandra.entity.dto.PatientDto;
import at.htl.cassandra.station.QueryStationDao;
import at.htl.cassandra.station.StationDao;
import io.quarkus.logging.Log;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Patient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    @Inject
    PatientDao dao;

    @Inject
    QueryStationDao extendedStationDao;

    @Inject
    QueryPatientDao extendedDao;

    @GET
    @Path("getAll")
    public List<Patient> getAllPatients() {
        return dao.findAll().all();
    }

    @Path("getById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getPatientById(@QueryParam("id") String id){
        Long tempId = Long.parseLong(id);
        return extendedDao.findPatientById(tempId);
    }
    @Path("create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addPatient(PatientDto patientDto){
        extendedDao.save(patientDto);
        return Response.ok().build();
    }
    @Path("checkSsn")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean checkSSN(@QueryParam("ssn") String ssn) throws Exception {
        return extendedDao.checkSSN(ssn);
    }

    @Path("assignStation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response assignStation(@QueryParam("patientId") String patientId, @QueryParam("stationId") String stationId){
        Patient patient = extendedDao.findPatientById(Long.parseLong(patientId)).get(0);
        patient.setStationId(Long.parseLong(stationId));
        extendedDao.assignStation(patient);
        return Response.ok().build();
    }

    @Path("releasePatient")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response releasePatient(@QueryParam("patientId") String patientId){
        Patient patient = extendedDao.findPatientById(Long.parseLong(patientId)).get(0);
        patient.setCurrentlyInHospital(false);
        patient.setStationId(null);
        extendedDao.releasePatient(patient);
        return Response.ok().build();
    }

    @Path("getByStation")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Patient> getPatientsByStation(@QueryParam("stationId") String stationId) throws Exception {
        return extendedDao.getAllByStation(Long.parseLong(stationId));
    }
}
