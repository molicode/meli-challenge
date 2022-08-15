package com.meli.challenge.service.rest.ipapi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpApiCountryDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  double longitude;

  private String ip;

  private String type;

  @JsonProperty("continent_code")
  private String continentCode;

  @JsonProperty("continent_name")
  private String continentName;

  @JsonProperty("country_code")
  private String countryCode;

  @JsonProperty("country_name")
  private String countryName;

  @JsonProperty("region_code")
  private String regionCode;

  @JsonProperty("region_name")
  private String regionName;

  private String city;

  @JsonProperty("zip")
  private String zipCode;

  private double latitude;

  @JsonProperty("location")
  private IpApiLocationDTO location;

}
