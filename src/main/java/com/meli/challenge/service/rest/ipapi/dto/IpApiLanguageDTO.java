package com.meli.challenge.service.rest.ipapi.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpApiLanguageDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String name;

  @JsonProperty("native")
  private String myNative;

}
