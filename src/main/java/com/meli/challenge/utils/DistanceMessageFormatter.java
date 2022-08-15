package com.meli.challenge.utils;

import static com.meli.challenge.utils.DistanceCalculator.LATITUDE_BSAS;
import static com.meli.challenge.utils.DistanceCalculator.LONGITUDE_BSAS;

import java.text.DecimalFormat;

public class DistanceMessageFormatter {

  public static String format(double distance, double latitude, double longitude) {
    final DecimalFormat decimalFormat = new DecimalFormat("#");

    final String message = " km. (" + decimalFormat.format(LATITUDE_BSAS) + "," + decimalFormat.format(LONGITUDE_BSAS) + ")" + " to " + "("
        + decimalFormat.format(latitude) + "," + decimalFormat.format(longitude) + ")";

    return "Estimated distance: " + decimalFormat.format(distance) + message;
  }

}
