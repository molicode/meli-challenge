package com.meli.challenge.service.rest.ipapi.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpApiLocationDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("geoname_id")
  private int geonameId;

  private String capital;

  @JsonProperty("languages")
  private ArrayList<IpApiLanguageDTO> languages;

  @JsonProperty("country_flag")
  private String countryFlag;

  @JsonProperty("country_flag_emoji")
  private String countryFlagEmoji;

  @JsonProperty("country_flag_emoji_unicode")
  private String countryFlagEmojiUnicode;

  @JsonProperty("calling_code")
  private String callingCode;

  @JsonProperty("is_eu")
  private boolean isEU;


}
