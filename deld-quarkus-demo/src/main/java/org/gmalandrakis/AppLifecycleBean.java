package org.gmalandrakis;

import io.quarkus.runtime.StartupEvent;
import org.gmalandrakis.model.Customer;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class AppLifecycleBean {
    @Inject
    TestService testService;
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        try {
            //TimeUnit.SECONDS.sleep(2);
             var a = this.testService.getUpdatedCustomer(null);
            LOGGER.info(a.getBody().getEmail());
            LOGGER.info(a.getHttpStatus());

            var b = this.testService.getUpdatedCustomerWithParams("email", new Customer());
            LOGGER.info(b.getBody().getEmail());

            var d = this.testService.getFile();
            LOGGER.info(d.getHeaders().get("Content-Length"));

            var e = this.testService.getFileAsByteArray();
            LOGGER.info(e.getBody().toString());
            LOGGER.info(e.getHeaders().get("Content-Length"));

            Customer customer = new Customer();
            customer.setName("Test Testsson");
            customer.setEmail("tester@gmail.com");
            customer.setExtraInfo("TEST!");
            var f = this.testService.postCustomer(customer);
            LOGGER.info("posted as json: ");
            LOGGER.info(f.getHttpStatus());

            var ff = this.testService.postToXmlEndpointAsJson(customer);
            LOGGER.info("posted to xml endpoint as json: ");
            LOGGER.info(ff.getHttpStatus()); //correctly returns HTTP 415 (Unsupported media type)

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}