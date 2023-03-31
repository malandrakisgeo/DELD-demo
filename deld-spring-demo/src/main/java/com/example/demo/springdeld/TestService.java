package com.example.demo.springdeld;

import com.example.demo.model.Customer;
import org.gmalandrakis.deld.annotations.*;
import org.gmalandrakis.deld.model.Response;

@BaseURL(url = "http://localhost:8080/test/")
public interface TestService {

    @GET(url = "Springtest")
    @DefaultHeader(headerName = "Accept", value = "application/json")
    @QueryParam(parameterName = "includeData", value = "all")
    public Response<Customer> getUpdatedCustomerWithParams(@QueryParam(parameterName = "excludeData") String toBeExcluded,
                                                           @Body Customer customer);

}
