package at.htl.cassandra.customer;

import at.htl.cassandra.entity.Customer;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.paging.OffsetPager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PageableCustomerDaoImpl implements PageableCustomerDao {

    @Inject
    CqlSession cqlSession;

    @Override
    public List<Customer> findPagedCustomerByCustomerNumber(String customerNumber, int pageNumber, int pageSize) {
        List<Customer> result = new ArrayList<>();
        PreparedStatement query = cqlSession.prepare(
                "SELECT * FROM dbi.customer WHERE customer_number = :customerNumber ORDER BY  creation_date");
        BoundStatement completeStatement = query.bind().setString("customerNumber", customerNumber);
        OffsetPager pager = new OffsetPager(pageSize);
        ResultSet resultSet = cqlSession.execute(completeStatement);
        OffsetPager.Page<Row> page = pager.getPage(resultSet, pageNumber);
        List<Row> pageElements = page.getElements();
        pageElements.forEach(c -> result.add(Customer.builder()
                            .customerNumber(c.getString("customer_number"))
                            .description(c.getString("description"))
                            .state(c.getString("state"))
                            .build())
        );
        return result;
    }
}
