package org.gmalandrakis;

import org.gmalandrakis.model.Customer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

@Path("/test")
public class GreetingResource {

    @Inject
    TestService testService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object withoutQueryParams() {
        try {
            return this.testService.getUpdatedCustomer(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GET
    @Path("testparam")
    @Produces(MediaType.APPLICATION_JSON)
    public Object withQueryParams(@QueryParam("excludeData") String excludeData) {
        excludeData.hashCode();
        try {
            return this.testService.getUpdatedCustomerWithParams("email",null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GET
    @Path("Springtest")
    @Produces(MediaType.APPLICATION_JSON)
    public Object springTest(@QueryParam("excludeData") String excludeData) {
        excludeData.hashCode();
        var a = new Customer();
        try {
            a.setExtraInfo("Success!");
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GET
    @Path("testfile")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Object testFile() {
        try {
            var a = this.testService.getFile().getBody(); //return the inputstream itself
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}