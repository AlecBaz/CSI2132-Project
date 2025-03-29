package hotelbackend.demo.Customer;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService = new CustomerService();

    @PostMapping("/add")
    public void addCustomer(@RequestBody Customer customer) {
        logger.info("Received request to add customer: {}", customer);
        System.out.println("Customer Info: " + customer);
        try {
            customerService.addCustomer(
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getIdType(),
                customer.getIdNumber()
            );
            logger.info("Customer added successfully: {}", customer);
        } catch (SQLException e) {
            logger.error("Error adding customer: {}", customer, e);
            // Optionally return a ResponseEntity with error status here
        }
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCustomer(@PathVariable int id) {
        try {
            return customerService.deleteCustomer(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/edit/{id}")
    public boolean editCustomer(@PathVariable int id, @RequestBody Customer customer) {
        try {
            return customerService.updateCustomer(
                id,
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getIdType(),
                customer.getIdNumber(),
                customer.getRegistrationDate()
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
