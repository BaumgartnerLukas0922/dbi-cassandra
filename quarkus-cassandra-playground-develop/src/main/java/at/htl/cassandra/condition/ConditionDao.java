package at.htl.cassandra.condition;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import at.htl.cassandra.entity.Condition;

@Dao
public interface ConditionDao {
    @Select
    PagingIterable<Condition> findAll();

}
