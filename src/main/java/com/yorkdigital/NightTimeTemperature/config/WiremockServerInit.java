package com.yorkdigital.NightTimeTemperature.config;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.yorkdigital.NightTimeTemperature.model.Temperature;
import com.yorkdigital.NightTimeTemperature.service.NightTimeTemperatureService;
import com.yorkdigital.NightTimeTemperature.service.NightTimeTemperatureServiceImpl;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WiremockServerInit {

  private NightTimeTemperatureService nightTimeTemperatureService;

  private WireMockServer wireMockServer;

  public String mockNightTimeTemperature() {
    wireMockServer = new WireMockServer(WireMockConfiguration.options().port(9090));
    wireMockServer.start();

    String lat = "43.66258321585993";
    String lng = "-79.39152689466948";
    nightTimeTemperatureService = new NightTimeTemperatureServiceImpl();

    Temperature temperature = nightTimeTemperatureService.getResult(lat, lng);

    String bodyMessage =
        "Welcome to WireMock!\n" + "Temperature: " + temperature.getTemperature() + "K";
    wireMockServer.stubFor(
        get(urlEqualTo("/night-time-temperature"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", "text/plain")
                    .withStatus(200)
                    .withBody(bodyMessage)));
    System.out.println("Server started");

    try {
      Thread.sleep(1 * 60000);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    wireMockServer.stop();

    return bodyMessage;
  }
}
