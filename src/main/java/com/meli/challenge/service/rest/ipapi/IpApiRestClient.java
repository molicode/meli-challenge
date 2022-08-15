package com.meli.challenge.service.rest.ipapi;

import static java.net.http.HttpResponse.BodyHandlers;
import static java.time.Duration.of;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.meli.challenge.service.rest.ipapi.dto.IpApiCountryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class IpApiRestClient {

  @Value("${ipapi.baseUrl}")
  private String baseUrl;

  @Value("${ipapi.key}")
  private String key;

  private HttpClient httpClient;

  private ObjectMapper mapper = new ObjectMapper();

  public IpApiRestClient() {
    this.httpClient = HttpClient.newBuilder()
        .connectTimeout(of(10, ChronoUnit.SECONDS))
        .build();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  public IpApiCountryDTO info(final String ip) {
    final URI uri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(ip)
        .queryParam("access_key", key)
        .build(true)
        .toUri();

    try {
      final HttpRequest request = HttpRequest.newBuilder(uri)
          .timeout(of(10, ChronoUnit.SECONDS))
          .GET()
          .build();

      final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

      if (response.statusCode() != 200) {
        throw new Exception("Connectivity failed for: " + baseUrl + " with response code: " + response.statusCode());
      }

      return new ObjectMapper().readValue(response.body(), IpApiCountryDTO.class);
    } catch (Exception e) {
      throw new RuntimeException("Error getting information from the ip: " + ip, e);
    }
  }


}
