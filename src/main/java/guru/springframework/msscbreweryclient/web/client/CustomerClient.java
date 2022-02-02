package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@ConfigurationProperties(prefix="sfg.brewery", ignoreUnknownFields=false)
@Component
public class CustomerClient {
    public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost; // this comes from application.properties, per the class annotation

    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(getCustomerServiceUri() + uuid, CustomerDto.class);
    }

    public URI createCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(getCustomerServiceUri(), customerDto);
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto) {
        restTemplate.put(getCustomerServiceUri() + uuid, customerDto);
    }

    public void deleteCustomer(UUID uuid) {
        restTemplate.delete(getCustomerServiceUri() + uuid);
    }

    private String getCustomerServiceUri() {
        return apihost + CUSTOMER_PATH_V1;
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}

