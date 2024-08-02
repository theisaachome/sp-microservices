package com.isaachome.ecom.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        value = "",
        url = "${application.config.customer-url"
)
public interface CustomerClient {
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> getCustomerById(@PathVariable("customer-id") String customerId);
}
