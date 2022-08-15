package com.meli.challenge.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpAddressInfoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String iPAddress;

  private String countryName;

  private Double distanceBetweenBsAsToTargetCountry;

}
