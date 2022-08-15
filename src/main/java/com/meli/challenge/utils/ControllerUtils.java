package com.meli.challenge.utils;

import java.time.Duration;
import java.time.Instant;

public class ControllerUtils {

  public static long millisToNow(Instant start) {
    return Duration.between(start, Instant.now()).toMillis();
  }

}
