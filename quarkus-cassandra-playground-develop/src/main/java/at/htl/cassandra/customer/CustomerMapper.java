package at.htl.cassandra.customer;

import at.htl.cassandra.entity.Customer;
import at.htl.cassandra.entity.dto.CustomerDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerMapper {
    public CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
                .customerNumber(customer.getCustomerNumber())
                .description(customer.getDescription())
                .state(customer.getState())
                .build();
    }

    public Customer toEntity(CustomerDto customerDto) {
        return Customer.builder()
                .customerNumber(customerDto.getCustomerNumber())
                .description(customerDto.getDescription())
                .state(customerDto.getState())
                .build();
    }
}
