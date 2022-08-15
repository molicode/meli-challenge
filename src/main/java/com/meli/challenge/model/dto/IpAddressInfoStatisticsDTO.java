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
public class IpAddressInfoStatisticsDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Double average;

  private Double maxDistanceToBsAs;

  private Double minDistanceToBsAs;

  private Integer quantity;

}
