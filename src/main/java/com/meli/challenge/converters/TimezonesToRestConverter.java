package com.meli.challenge.converters;

import static java.time.ZoneId.of;
import static java.time.ZonedDateTime.of;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TimezonesToRestConverter {

  private static final DateTimeFormatter dateTimeFormatter = ofPattern("HH:mm:ss");

  private static String calculate(final LocalDateTime localDateTimeInUTC, String timezone) {
    final Timestamp tiempoUTC = new Timestamp(of(localDateTimeInUTC, of(timezone)).toInstant().toEpochMilli());
    return tiempoUTC.toLocalDateTime().toLocalTime().format(dateTimeFormatter) + "(" + timezone + ")";
  }

  public List<String> convertAll(final List<String> timezones, final LocalDateTime localDateTimeInUTC) {
    return timezones.stream()
        .map(timezone -> calculate(localDateTimeInUTC, timezone))
        .collect(toList());
  }

}