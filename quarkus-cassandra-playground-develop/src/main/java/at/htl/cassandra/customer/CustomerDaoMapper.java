package at.htl.cassandra.customer;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface CustomerDaoMapper {
    @DaoFactory
    CustomerDao customerDao();
}
