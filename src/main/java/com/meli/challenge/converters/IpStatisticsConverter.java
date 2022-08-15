package com.meli.challenge.converters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import com.meli.challenge.model.dto.IpAddressInfoStatisticsDTO;

public class IpStatisticsConverter {

  public static IpAddressInfoStatisticsDTO convertToItem(Map<String, ?> item) {
    return IpAddressInfoStatisticsDTO.builder()
        .average(((BigDecimal) item.get("average")).doubleValue())
        .minDistanceToBsAs((Double) item.get("min"))
        .maxDistanceToBsAs((Double) item.get("max"))
        .quantity(((BigInteger) item.get("quantity")).intValue())
        .build();
  }

}
