package at.htl.cassandra.patient;

import at.htl.cassandra.entity.Patient;
import at.htl.cassandra.entity.Station;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface PatientDao {
    @Select
    PagingIterable<Patient> findAll();

    @Update
    void update(Patient patient);
}
