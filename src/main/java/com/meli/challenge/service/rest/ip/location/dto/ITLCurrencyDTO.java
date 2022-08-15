package com.meli.challenge.service.rest.ip.location.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ITLCurrencyDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String name;

  private String Symbol;
}
