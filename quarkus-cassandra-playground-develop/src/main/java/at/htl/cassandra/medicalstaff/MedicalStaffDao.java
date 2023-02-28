package at.htl.cassandra.medicalstaff;

import at.htl.cassandra.entity.MedicalStaff;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface MedicalStaffDao {
    @Select
    PagingIterable<MedicalStaff> findAll();

    @Update
    void update(MedicalStaff medicalStaff);
}
