package at.htl.cassandra.medicalstaff;

import at.htl.cassandra.patient.PatientDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface MedicalStaffDaoMapper {
    @DaoFactory
    MedicalStaffDao medicalStaffDao();
}
