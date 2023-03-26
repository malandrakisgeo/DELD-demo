package org.gmalandrakis;


import org.gmalandrakis.deld.annotations.*;
import org.gmalandrakis.deld.model.Response;
import org.gmalandrakis.model.Customer;

import java.io.InputStream;

public interface TestService {

    @GET(fullUrl = "localhost:8080")
    public boolean getTrue();

    @GET(url = "testGet")
    @DefaultHeader(headerName = "Accept", value = "application/json")
    public Response<Customer> getUpdatedCustomer(@Body Customer customer);

    @GET(url = "testParam")
    @DefaultHeader(headerName = "Accept", value = "application/json")
    @QueryParam(parameterName = "includeData", value = "all")
    public Response<Customer> getUpdatedCustomerWithParams(@QueryParam(parameterName = "excludeData") String toBeExcluded, @Body Customer customer);

    @GET(fullUrl = "http://localhost:1531/testGetXml")
    @DefaultHeader(headerName = "Accept", value = "application/xml")
    @QueryParam(parameterName = "includeData", value = "all")
    public Response<Customer> getUpdatedCustomerAsXml();

    @GET(fullUrl = "http://localhost:1531/testGetByteArray")
    @DefaultHeader(headerName = "Accept", value = "application/octet-stream") //TODO: Check for bugs
    public Response<InputStream> getFile();

    @GET(fullUrl = "http://localhost:1531/testGetByteArray")
    @DefaultHeader(headerName = "Accept", value = "application/octet-stream") //TODO: Check for bugs
    public Response<byte[]> getFileAsByteArray();

    @POST(fullUrl = "http://localhost:1531/testPost")
    @DefaultHeader(headerName = "Content-Type", value = "application/json")
    public Response<InputStream> postCustomer(@Body Customer customer);

    @POST(fullUrl = "http://localhost:1531/testPostxml")
    @DefaultHeader(headerName = "Content-Type", value = "application/xml")
    public Response<Customer> postCustomerAsXml(@Body Customer customer);

    @POST(fullUrl = "http://localhost:1531/testPostxml")
    @DefaultHeader(headerName = "Content-Type", value = "application/json")
    public Response<Customer> postToXmlEndpointAsJson(@Body Customer customer);


    @POST(fullUrl = "http://localhost:1531/testPostxml")
    @DefaultHeader(headerName = "Content-Type", value = "application/json")
    public Response response(@Body Customer customer);
}
