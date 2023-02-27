package at.htl.cassandra.customer;

import at.htl.cassandra.entity.Customer;

import java.util.List;

public interface PageableCustomerDao {

    List<Customer> findPagedCustomerByCustomerNumber(String customerNumber, int pageNumber, int pageSize);
}
