package com.yorkdigital.NightTimeTemperature;

import com.yorkdigital.NightTimeTemperature.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NightTimeTemperatureApplication {

  public static void main(String[] args) {
    if (args[0].endsWith("dev")) {
      AppConfig.mockNightTimeTemperatureInit();
    }

    SpringApplication.run(NightTimeTemperatureApplication.class, args);
  }
}
