package at.htl.cassandra;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.quarkus.test.CassandraTestResource;
import at.htl.cassandra.customer.CustomerDao;
import at.htl.cassandra.entity.Customer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
@QuarkusTestResource(CassandraTestResource.class)
public class CustomerDaoTest {

    @Inject
    CustomerDao dao;

    @Test
    @Disabled("This test is not working yet, because cassandra sux")
    public void testInsertAndSelectOperations() {
        // Given
        Customer givenCustomer = Customer.builder()
                .customerNumber("123")
                .description("LeistungsbezeichnungTest")
                .state("IN_BEARBEITUNG")
                .build();
        //WHEN
        dao.update(givenCustomer);
        PagingIterable<Customer> pagingIterable = dao.findAll();
        //THEN
        List<Customer> allCustomers = pagingIterable.all();
        Assertions.assertThat(allCustomers).hasSize(1);
    }

}
