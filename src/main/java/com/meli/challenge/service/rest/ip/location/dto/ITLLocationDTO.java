package com.meli.challenge.service.rest.ip.location.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ITLLocationDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("calling_codes")
  private ArrayList<String> callingCodes;

  private String capital;

  private String flag;

  @JsonProperty("native_name")
  private String nativeName;

  @JsonProperty("top_level_domains")
  private ArrayList<String> topLevelDomains;

}
