package com.yorkdigital.NightTimeTemperature.service;

import com.yorkdigital.NightTimeTemperature.model.ResultsOutput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TemperatureService {

  public long getTemperature(ResultsOutput request) {
    long twilightBegin =
        formatMillisecondsToMinutes(formatDates(request.getResults().getCivil_twilight_begin()));
    long twilightEnd =
        formatMillisecondsToMinutes(formatDates(request.getResults().getCivil_twilight_end()));
    long sunRise = formatMillisecondsToMinutes(formatDates(request.getResults().getSunrise()));
    long sunSet = formatMillisecondsToMinutes(formatDates(request.getResults().getSunset()));

    long localTime = formatMillisecondsToMinutes(formatDates(getLocalTime()));

    long temperature = 0;

    // Algorithms
    if (localTime > twilightBegin && localTime < (2 * sunRise - twilightBegin)) {
      temperature = findtempforRise(localTime, twilightBegin, sunRise);
    } else if ((2 * sunSet - twilightEnd) > localTime
        && localTime > (2 * sunRise - twilightBegin)) {
      temperature = 6000;
    } else if ((2 * sunSet - twilightEnd) <= localTime && localTime < twilightEnd) {
      temperature = findtempforDawn(localTime, twilightEnd, sunSet);
    } else if (localTime <= twilightBegin || twilightEnd <= localTime) {
      temperature = 2700;
    }

    return temperature;
  }

  public String getLocalTime() {

    LocalTime localTime1 = LocalTime.now();

    int hour = localTime1.getHour();
    int min = localTime1.getMinute();
    int sec = localTime1.getSecond();
    String dayNight;

    if (hour > 12) {
      dayNight = "PM";
      hour = hour - 12;

    } else if (hour == 12) {
      dayNight = "PM";

    } else {
      dayNight = "AM";
    }
    return hour + ":" + min + ":" + sec + " " + dayNight;
  }

  public long formatMillisecondsToMinutes(long time) {
    return TimeUnit.MILLISECONDS.toMinutes(time);
  }

  public long formatDates(String time) {
    SimpleDateFormat format = new SimpleDateFormat("h:mm:ss a");
    Date formattedTime = null;
    try {
      formattedTime = format.parse(time.toString());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return formattedTime.getTime();
  }

  public double calculateTempForDawn(long twilightEnd, long sunSet) {
    long timeDifference = twilightEnd - sunSet;
    long tempDifference = 6000 - 2700;

    return tempDifference / (2 * timeDifference) * 1.0;
  }

  public double calculateTempForRise(long twilightBegin, long sunRise) {
    long timeDifference = sunRise - twilightBegin;
    long tempDifference = 2700 - 6000;

    return tempDifference / (2 * timeDifference) * 1.0;
  }

  public long findtempforDawn(long localTime, long twilightEnd, long sunSet) {

    return (long)
        (2700
            + (twilightEnd - localTime)
                * calculateTempForDawn(twilightEnd, sunSet));
  }

  public long findtempforRise(long localTime, long twilightBegin, long sunRise) {

    return (long)
        (2700
            + (localTime - twilightBegin)
                * calculateTempForRise(twilightBegin, sunRise));
  }
}
