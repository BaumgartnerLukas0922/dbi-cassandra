package at.htl.cassandra.diagnosis;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface DiagnosisDaoMapper {
    @DaoFactory
    DiagnosisDao diagnosisDao();
}
