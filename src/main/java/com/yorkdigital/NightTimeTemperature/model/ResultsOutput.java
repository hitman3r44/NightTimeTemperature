package com.yorkdigital.NightTimeTemperature.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ResultsOutput {

  private NightTimeTemperature results;
  private String status;

  public NightTimeTemperature getResults() {
    return results;
  }

  public void setResults(NightTimeTemperature results) {
    this.results = results;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
