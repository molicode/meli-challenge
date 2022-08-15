package com.meli.challenge.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.meli.challenge.model.Currency;

public class CurrencyMessageFormatter {

  public static List<String> format(List<Currency> currencies) {
    return currencies.stream()
        .map(CurrencyMessageFormatter::format)
        .collect(Collectors.toList());
  }

  private static String format(Currency currency) {
    return "Currency: " + currency.getCode() + "(1" + currency.getCode() + " = " + currency.getRate() + " U$S)";
  }

}
