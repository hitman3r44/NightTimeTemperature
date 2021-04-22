package com.yorkdigital.NightTimeTemperature.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.yorkdigital.NightTimeTemperature.service.TemperatureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  WireMockServer wireMockServerTemplate() {
    return new WireMockServer();
  }

  @Bean
  public TemperatureService service() {
    return new TemperatureService();
  }

  public static void mockNightTimeTemperatureInit() {
    WiremockServerInit wiremockServerInit = new WiremockServerInit();
    wiremockServerInit.mockNightTimeTemperature();
  }
}
