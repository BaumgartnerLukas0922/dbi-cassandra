package at.htl.cassandra.patient;

import at.htl.cassandra.station.StationDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface PatientDaoMapper {
    @DaoFactory
    PatientDao patientDao();
}
