package com.example.camel_labs.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GetFileFromSMBServerSchedule {

  private final ProducerTemplate producerTemplate;

  @Scheduled(cron = "0 47 * * * ?")
  void start() {
    log.info("starting job");

//    producerTemplate.sendBody("direct:writeToSMBServer", null);
    producerTemplate.sendBody("direct:copyOnDemandFromSMBServerToLocalDir", null);
  }
}
