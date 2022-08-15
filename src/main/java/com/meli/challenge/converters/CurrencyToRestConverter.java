package com.meli.challenge.converters;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.meli.challenge.model.Currency;
import com.meli.challenge.service.rest.ip.location.dto.ITLCurrencyDTO;
import org.springframework.stereotype.Component;

@Component
public class CurrencyToRestConverter {

  public List<Currency> convertAll(final List<ITLCurrencyDTO> currenciesDTO) {
    return currenciesDTO.stream().map(this::convert).collect(toList());
  }

  public Currency convert(final ITLCurrencyDTO currencyDTO) {
    return Currency.builder()
        .code(currencyDTO.getCode())
        .name(currencyDTO.getName())
        .symbol(currencyDTO.getSymbol())
        .build();
  }

  public String toString(final ArrayList<ITLCurrencyDTO> currenciesDTO) {
    return currenciesDTO.stream()
        .map(ITLCurrencyDTO::getCode)
        .map(String::valueOf)
        .collect(joining(","));
  }
}
