package com.example.camel_labs.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class WriteToSmbRoute extends RouteBuilder {

  @Override
  public void configure() {

    from("direct:writeToSMBServer") // Executa a cada 60 segundos
        .routeId("write-to-smb")
        .setBody(constant("nome,email\nJoão,j@exemplo.com\nMaria,m@exemplo.com"))
        .setHeader(Exchange.FILE_NAME, constant("export-" + System.currentTimeMillis() + ".csv"))
        .to("smb:localhost:4445/Recebidos?username=integration&password=123&domain=WORKGROUP&disconnect=true")
        .log("Arquivo enviado para SMB com sucesso");
  }
}
