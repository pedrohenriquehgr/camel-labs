package com.example.camel_labs.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomersRoute extends RouteBuilder {
  @Override
  public void configure() {
    from("direct:getById")
        .log("Calling getById ${headers.id}")
        .toD("http://localhost:8080/customers/${headers.id}?bridgeEndpoint=true")
        .log("${body}");

    from("direct:customerRegister")
        .log("Calling customerRegister ${headers} - ${body}")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .to("http://localhost:8080/customers?bridgeEndpoint=true")
        .convertBodyTo(String.class);
  }
}
