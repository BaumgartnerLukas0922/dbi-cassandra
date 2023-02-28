package at.htl.cassandra.diagnosis;

import at.htl.cassandra.entity.Diagnosis;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface DiagnosisDao {
    @Select
    PagingIterable<Diagnosis> findAll();

}
