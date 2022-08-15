package com.meli.challenge.service.rest.exchange.rates.dto;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

@Data
public class ERExchangeDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private boolean success;

  private int timestamp;

  private String base;

  private String date;

  private Map<String, Double> rates;

}
