package com.yorkdigital.NightTimeTemperature.service;

import com.yorkdigital.NightTimeTemperature.model.Temperature;

public interface NightTimeTemperatureService {
  String getData(String lat, String lng);
  Temperature getResult(String lat, String lng);
}
