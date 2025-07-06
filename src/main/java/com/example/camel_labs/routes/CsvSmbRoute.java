package com.example.camel_labs.routes;

import java.util.List;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CsvSmbRoute extends RouteBuilder {

  @Override
  public void configure() {

    from("direct:readOnDemandFromSMBServer")
        .pollEnrich(
            "smb:localhost:4445/Recebidos?username=integration&password=123&domain=WORKGROUP&download=true&noop=true")
        .routeId("smb-csv-reader")
        .unmarshal()
        .csv()
        .split(body())
        .streaming()
        .process(
            exchange -> {
              List<?> row = exchange.getIn().getBody(List.class);
              System.out.println("Linha CSV: " + row);
            })
        .end();

    from("direct:copyOnDemandFromSMBServerToLocalDir")
        .pollEnrich(
            "smb:localhost:4445/Recebidos?username=integration&password=123&domain=WORKGROUP&download=true&noop=true")
        .routeId("smb-csv-copy")
        .log("Reading file: ${file:name}")
        .to("file:data/batch/inbox")
        .log("File copied ${header.CamelFileNameProduced}");
  }
}
