package com.meli.challenge.service.rest.exchange.rates.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class ERCountryCodesDTO implements Serializable {

  private static final long serialVersionUID = -1L;

  private Boolean success;

  private Map<String, String> symbols;

}
