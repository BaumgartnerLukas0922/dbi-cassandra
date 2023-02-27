package at.htl.cassandra.customer;

import at.htl.cassandra.entity.Customer;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface CustomerDao {
    @Select
    PagingIterable<Customer> findAll();

    @Update
    void update(Customer customer);

}
