package com.example.demo.springdeld;

import com.example.demo.model.Customer;
import org.gmalandrakis.deld.annotations.Body;
import org.gmalandrakis.deld.annotations.DefaultHeader;
import org.gmalandrakis.deld.annotations.GET;
import org.gmalandrakis.deld.annotations.QueryParam;
import org.gmalandrakis.deld.model.Response;

public interface TestService {

    @GET(url = "Springtest")
    @DefaultHeader(headerName = "Accept", value = "application/json")
    @QueryParam(parameterName = "includeData", value = "all")
    public Response<Customer> getUpdatedCustomerWithParams(@QueryParam(parameterName = "excludeData") String toBeExcluded,
                                                           @Body Customer customer);

}
