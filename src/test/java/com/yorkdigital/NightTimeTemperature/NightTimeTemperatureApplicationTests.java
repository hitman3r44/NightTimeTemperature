package com.yorkdigital.NightTimeTemperature;

import com.yorkdigital.NightTimeTemperature.config.WiremockServerInit;
import com.yorkdigital.NightTimeTemperature.service.NightTimeTemperatureService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NightTimeTemperatureApplicationTests {
  private NightTimeTemperatureService nightTimeTemperatureService;
  private WiremockServerInit wiremockServerInit;

  @Test
  public void testMockNightTimeTemperatureService() {
    wiremockServerInit = new WiremockServerInit();
    assertThat(wiremockServerInit.mockNightTimeTemperature()).contains("Welcome to WireMock!");
  }
}
