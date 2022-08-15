package com.meli.challenge.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String name;

  private String symbol;

  private String rate;

}
