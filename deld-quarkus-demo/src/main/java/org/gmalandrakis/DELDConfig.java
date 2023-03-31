package org.gmalandrakis;

import org.gmalandrakis.deld.core.DELDBuilder;

import javax.enterprise.inject.Produces;

public class DELDConfig {
    @Produces
    TestService testServiceProducer(){
        return (TestService)  new DELDBuilder().forService(TestService.class);
    }

}
