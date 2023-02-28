package at.htl.cassandra.station;

import at.htl.cassandra.entity.Condition;
import at.htl.cassandra.entity.Station;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface StationDao {
    @Select
    PagingIterable<Station> findAll();
}
