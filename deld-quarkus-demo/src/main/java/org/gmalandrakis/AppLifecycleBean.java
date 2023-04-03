package org.gmalandrakis;

import io.quarkus.runtime.StartupEvent;
import org.gmalandrakis.model.Customer;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.*;

@ApplicationScoped
public class AppLifecycleBean {
    @Inject
    TestService testService;
    private final Executor threadsForAsync = Executors.newFixedThreadPool(3);

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        try {

            var a = this.testService.getUpdatedCustomer(null);
            LOGGER.warn("sync response: ");
            LOGGER.warn(a.getBody().getEmail());
            LOGGER.warn(a.getHttpStatus());

            threadsForAsync.execute(()->{
                this.testService.getUpdatedCustomerAsync(null)
                        .whenCompleteAsync((customer, arg) -> {
                            LOGGER.warn("Async response: ");
                            LOGGER.warn(customer.getEmail());
                        });
            });

            var b = this.testService.getUpdatedCustomerWithParams("email", new Customer());
            LOGGER.info(b.getBody());


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