package org.gmalandrakis;

import org.gmalandrakis.model.Customer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.HeaderParam;

import javax.ws.rs.core.MediaType;

@Path("/test2")
public class TestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object hello() {
        //@HeaderParam("excludeData") String secretHandshake
        Customer customer = new Customer();
        customer.setName("George");
        customer.setEmail("malandrakisgeo@gmail.com");

        //if(secretHandshake.equalsIgnoreCase("email")) {
       //     customer.setEmail(null);
      //  }
        return customer;
    }



}
