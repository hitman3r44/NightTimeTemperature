package com.yorkdigital.NightTimeTemperature.controller;

import com.yorkdigital.NightTimeTemperature.model.Temperature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.yorkdigital.NightTimeTemperature.service.NightTimeTemperatureService;

@RestController
public class NightTimeTemperatureController {

  @Autowired private NightTimeTemperatureService nightTimeTemperatureService;

  @RequestMapping("/night-time-temperature-verbose")
  public String getNightTimeTemperatureData(String lat, String lng) {
    return nightTimeTemperatureService.getData(lat, lng);
  }

  @RequestMapping("/night-time-temperature")
  @GetMapping(path = "", produces = "application/json")
  public Temperature getTemperature(
      @RequestParam(name = "lat", required = false, defaultValue = "43.66258321585993") String lat,
      @RequestParam(name = "lng", required = false, defaultValue = "-79.39152689466948")
          String lng) {
    return nightTimeTemperatureService.getResult(lat, lng);
  }
}
