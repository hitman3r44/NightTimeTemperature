package com.yorkdigital.NightTimeTemperature.service;

import com.yorkdigital.NightTimeTemperature.model.ResultsOutput;
import com.yorkdigital.NightTimeTemperature.model.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NightTimeTemperatureServiceImpl implements NightTimeTemperatureService {
  static String API_URL = "https://api.sunrise-sunset.org/json?formatted=0&lat=";

  @Bean
  public Temperature getTemperatureObj() {
    return new Temperature();
  }

  @Autowired private RestTemplate restTemplate = new RestTemplate();

  @Autowired private TemperatureService service = new TemperatureService();

  @Autowired
  public NightTimeTemperatureServiceImpl() {}

  @Override
  public Temperature getResult(String lat, String lng) {

    String url = API_URL + lat + "&lng=" + lng;
    ResultsOutput resultsOutput = restTemplate.getForObject(url, ResultsOutput.class);
    long temp = service.getTemperature(resultsOutput);
    Temperature temperatureObj = getTemperatureObj();
    temperatureObj.setTemperature(temp + "");
    return temperatureObj;
  }

  @Override
  public String getData(String lat, String lng) {
    String uri = API_URL + lat + "&lng=" + lng;

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

    return String.format("" + response);
  }
}
