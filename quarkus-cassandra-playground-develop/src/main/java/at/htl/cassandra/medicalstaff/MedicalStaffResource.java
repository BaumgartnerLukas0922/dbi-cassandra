package at.htl.cassandra.medicalstaff;

import at.htl.cassandra.entity.MedicalStaff;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/MedicalStaff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalStaffResource {

    @Inject
    MedicalStaffDao dao;
    @Inject
    QueryMedicalStaffDao extendedDao;

    @GET
    @Path("getAll")
    public List<MedicalStaff> getAll() {
        return dao.findAll().all();
    }

    @GET
    @Path("getById")
    public MedicalStaff GetById(@QueryParam("id") Long id){
        return extendedDao.findMedicalStaffById(id).get(0);
    }

    @DELETE
    public Response deleteById(@QueryParam("id") Long id){
        MedicalStaff staff = extendedDao.findMedicalStaffById(id).get(0);
        extendedDao.delete(staff);
        return Response.ok().build();
    }
    @POST
    @Path("create")
    public Response create(MedicalStaff staff){
        dao.update(staff);
        return Response.ok().build();
    }


}
