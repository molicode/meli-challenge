package com.meli.challenge.service.rest.ip.location;

import static java.time.Duration.of;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.meli.challenge.service.rest.ip.location.dto.ITLCountryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class IpToLocationRestClient {

  @Value("${ip-to-location.baseUrl}")
  private String baseUrl;

  @Value("${ip-to-location.key}")
  private String key;

  private HttpClient httpClient;

  private ObjectMapper mapper = new ObjectMapper();

  public IpToLocationRestClient() {
    this.httpClient = HttpClient.newBuilder()
        .connectTimeout(of(10, ChronoUnit.SECONDS))
        .build();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  public ITLCountryDTO getAll(String ip) {
    final Map<String, ITLCountryDTO> countriesInfoMap;
    final String path = "ip_to_location/";
    final URI uri = UriComponentsBuilder
        .fromHttpUrl(baseUrl)
        .path(path + ip)
        .queryParam("apikey", key)
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

      return new ObjectMapper().readValue(response.body(), ITLCountryDTO.class);
    } catch (Exception e) {
      throw new RuntimeException("Error getting information from the ip: " + ip, e);
    }
  }
}
