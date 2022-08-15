package com.meli.challenge.service.rest.ip.location.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ITLCountryDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String city;

  private ITLConnectionDTO connection;

  @JsonProperty("continent_code")
  private String continentCode;

  @JsonProperty("continent_name")
  private String continentName;

  @JsonProperty("country_code")
  private String countryCode;

  @JsonProperty("country_name")
  private String countryName;

  private ArrayList<ITLCurrencyDTO> currencies;

  private String ip;

  @JsonProperty("is_eu")
  private boolean isEU;

  private double latitude;

  private ITLLocationDTO location;

  private double longitude;

  @JsonProperty("region_name")
  private String regionName;

  private ArrayList<String> timezones;

  private String type;


}
