package com.meli.challenge.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullCountryInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String ip;

  private LocalDateTime currentTimeInUTC;

  private String name;

  private String iSOCode;

  private List<Language> languages;

  private List<String> currentTimes;

  private List<String> currencies;

  private String distanceBetweenBsAsToTargetCountry;

}
