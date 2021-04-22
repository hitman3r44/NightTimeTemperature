package com.yorkdigital.NightTimeTemperature;

import com.yorkdigital.NightTimeTemperature.config.WiremockServerInit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NightTimeTemperatureServiceIntegrationTest {
  private WiremockServerInit wiremockServerInit;

  @BeforeEach
  public void setUp() {
    wiremockServerInit = new WiremockServerInit();
  }

  @Test
  public void testMockNightTimeTemperatureService() {
    assertThat(wiremockServerInit.mockNightTimeTemperature()).contains("Welcome to WireMock!");
  }
}
