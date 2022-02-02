package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * These tests just exercise things. They don't really test anything, per se.
 */
@SpringBootTest
public class CustomerClientTest {
    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        UUID uuid = UUID.randomUUID();
        CustomerDto customerDto = customerClient.getCustomerById(uuid);
        System.err.println(customerDto.getId());
        assertNotNull(customerDto);
    }

    @Test
    void createCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("Sam Smith").build();
        URI uri = customerClient.createCustomer(customerDto);
        System.err.println(uri);
        assertNotNull(uri);
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = CustomerDto.builder().name("Sam Smith").build();
        UUID uuid = UUID.randomUUID();
        customerClient.updateCustomer(uuid, customerDto);
    }

    @Test
    void deleteCustomer() {
        UUID uuid = UUID.randomUUID();
        customerClient.deleteCustomer(uuid);
    }
}
