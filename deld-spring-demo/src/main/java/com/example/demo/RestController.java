package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.springdeld.TestService;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    TestService testService;

    @GetMapping("/testGetByteArray")
    public ResponseEntity<byte[]> getFile() {

        byte[] file;
        try {
            file =  new FileInputStream(ResourceUtils.getFile("classpath:sample.pdf")).readAllBytes();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length))
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .body(file);
    }

    @GetMapping("/deldTest")
    public ResponseEntity<Customer> deldTest(){
       var cust =   testService.getUpdatedCustomerWithParams("email", null).getBody();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(cust);
    }

    @GetMapping("/testParam")
    public ResponseEntity<Customer> getCustomerWithParamAsJson(@RequestParam(value = "excludeData", required = false) String exclude) {
        logger.info("Exlude data:" + exclude);

        Customer customer = new Customer();
        customer.setName("George");
        customer.setEmail("malandrakisgeo@gmail.com");
        if(exclude!=null && exclude.equalsIgnoreCase("email")) {
             customer.setEmail(null);
          }

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(customer);
    }

    @GetMapping("/testGetXml")
    public ResponseEntity<Customer> getCustomerAsXml() {
        Customer customer = new Customer();
        customer.setName("George");
        customer.setEmail("malandrakisgeo@gmail.com");

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "application/xml")
                .body(customer);
    }

    @GetMapping("/testGet")
    public ResponseEntity<Customer> getcustomerAsJson() {
        logger.info("gets");

        Customer customer = new Customer();
        customer.setName("George");
        customer.setEmail("malandrakisgeo@gmail.com");

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(customer);
    }

    @PostMapping(path="/testPost", consumes={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity postCustomerAsJson( @RequestBody @Validated Customer customer) {
        logger.info("json received: " + customer);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path="/testPostxml",consumes={MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity postCustomerAsXml( @RequestBody @Validated Customer customer) {
        logger.info("xml endpoint listening");

        return ResponseEntity.ok().build();
    }

}
