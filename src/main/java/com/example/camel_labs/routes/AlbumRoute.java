package com.example.camel_labs.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class AlbumRoute extends RouteBuilder {
  @Override
  public void configure() {
    // Configure the REST API endpoint
    restConfiguration()
        .component("platform-http") // Or another REST component
        .bindingMode(RestBindingMode.json)
        .bindingPackageScan("com.example.camel-labs.model")
        .enableCORS(true);

    rest().openApi().specification("/openapi/customers.json").missingOperation("ignore");

    rest("/albums")
        .description("API para obter álbuns")
        .get()
        .produces("application/json")
        .to("direct:getAlbums");

    from("direct:getAlbums")
        .log("Calling albums REST API")
        .to("https://jsonplaceholder.typicode.com/albums?bridgeEndpoint=true");
  }
}
