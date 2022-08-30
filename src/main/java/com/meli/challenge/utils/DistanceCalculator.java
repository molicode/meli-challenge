package com.meli.challenge.utils;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

public class DistanceCalculator {

  public static final double LATITUDE_BSAS = -34.613152;

  public static final double LONGITUDE_BSAS = -58.377232;

// Point Lon Lat

  public static double distance(final double latitude, final double longitude) {
    if ((LATITUDE_BSAS == latitude) && (LONGITUDE_BSAS == longitude)) {
      return 0;
    } else {
      final double theta = LONGITUDE_BSAS - longitude;
      double distance = sin(toRadians(LATITUDE_BSAS)) * sin(toRadians(latitude)) +
          (cos(toRadians(LATITUDE_BSAS)) * cos(toRadians(latitude)) * cos(toRadians(theta)));
      distance = acos(distance);
      distance = toDegrees(distance);
      return distance * 60 * 1.85316;
    }
  }
}
