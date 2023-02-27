package at.htl.cassandra.condition;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface ConditionDaoMapper {
    @DaoFactory
    ConditionDao conditionDao();
}
