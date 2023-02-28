package at.htl.cassandra.station;

import at.htl.cassandra.condition.ConditionDao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface StationDaoMapper {
    @DaoFactory
    StationDao stationDao();
}
