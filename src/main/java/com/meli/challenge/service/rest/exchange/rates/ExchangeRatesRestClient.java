package com.meli.challenge.service.rest.exchange.rates;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.time.Duration.of;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.challenge.service.rest.exchange.rates.dto.ERCountryCodesDTO;
import com.meli.challenge.service.rest.exchange.rates.dto.ERExchangeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ExchangeRatesRestClient {

  @Value("${exchange-rates.baseUrl}")
  private String baseUrl;

  @Value("${exchange-rates.key}")
  private String key;

  private HttpClient httpClient;

  private ObjectMapper mapper = new ObjectMapper();

  public ExchangeRatesRestClient() {
    this.httpClient = HttpClient.newBuilder()
        .connectTimeout(of(10, ChronoUnit.SECONDS))
        .build();
    mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setPropertyNamingStrategy(SNAKE_CASE);
  }

  public ERCountryCodesDTO getAllCountryCodes() {
    final String path = "symbols";
    final URI uri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(path)
        .queryParam("apikey", key)
        .build(true)
        .toUri();

    try {
      final HttpRequest request = HttpRequest.newBuilder(uri)
          .timeout(Duration.of(10, ChronoUnit.SECONDS))
          .GET()
          .build();

      final HttpResponse<String> response = httpClient.send(request, ofString());

      if (response.statusCode() != 200) {
        throw new Exception("Connectivity failed for: " + baseUrl + " with response code: " + response.statusCode());
      }

      return new ObjectMapper().readValue(response.body(), ERCountryCodesDTO.class);
    } catch (Exception e) {
      throw new RuntimeException("Error getting information countries ", e);
    }
  }

  public ERExchangeDTO getCountryCodeExchangeRates(String countriesCodes) {
    final String path = "latest";
    final URI uri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(path)
        .queryParam("apikey", key)
        .queryParam("symbols", countriesCodes)
        .queryParam("base", "ARS")
        .build(true)
        .toUri();

    try {
      final HttpRequest request = HttpRequest.newBuilder(uri)
          .timeout(of(10, ChronoUnit.SECONDS))
          .GET()
          .build();

      final HttpResponse<String> response = httpClient.send(request, ofString());

      if (response.statusCode() != 200) {
        throw new Exception("Connectivity failed for: " + baseUrl + " with response code: " + response.statusCode());
      }

      return new ObjectMapper().readValue(response.body(), ERExchangeDTO.class);
    } catch (Exception e) {
      throw new RuntimeException("Error getting information from the ip: ", e);
    }
  }

}
