package com.meli.challenge.controller;

import static com.meli.challenge.utils.ControllerUtils.millisToNow;

import java.time.Instant;
import java.util.List;

import com.meli.challenge.model.FullCountryInfo;
import com.meli.challenge.model.dto.IpAddressInfoDTO;
import com.meli.challenge.model.dto.IpAddressInfoStatisticsDTO;
import com.meli.challenge.service.rest.CountryInfoService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryInfoController {

  private static final Logger logger = LoggerFactory.getLogger(CountryInfoController.class);

  private final CountryInfoService countryInfoService;

  public CountryInfoController(CountryInfoService countryInfoService) {
    this.countryInfoService = countryInfoService;
  }

  @ApiOperation(value = "Returns full country info for an specific IP")
  @PostMapping(value = "/ip", produces = "application/json")
  @ResponseBody
  public FullCountryInfo getFullCountryInfo(@RequestParam String ip) {
    final Instant start = Instant.now();
    logger.info("The details from the country for the IP: {} will be consulted", ip);
    final FullCountryInfo response = countryInfoService.countryInfoByIP(ip);
    logger.info("Finished getting details from the country by IP: {}, took {}ms", ip, millisToNow(start));
    return response;
  }

  @ApiOperation(value = "Returns the furthest distance between Buenos Aires and the IP of the country consulted")
  @GetMapping(value = "/statistics/furthest", produces = "application/json")
  public IpAddressInfoDTO getFurthestDistanceToBsAs() {
    final Instant start = Instant.now();
    logger.info("Getting the furthest distance saved");
    IpAddressInfoDTO response = countryInfoService.getFurthestDistanceToBsAs();
    logger.info("Finished getting furthest distance saved, took {}ms", millisToNow(start));
    return response;
  }

  @ApiOperation(value = "Returns the closet distance between Buenos Aires and the IP of the country consulted")
  @GetMapping(value = "/statistics/closest", produces = "application/json")
  public IpAddressInfoDTO getClosestDistanceToBsAs() {
    final Instant start = Instant.now();
    logger.info("Getting the closet distance saved");
    IpAddressInfoDTO response = countryInfoService.getClosestDistanceToBsAs();
    logger.info("Finished getting  closet distance saved, took {}ms", millisToNow(start));
    return response;
  }

  @ApiOperation(value = "Returns the service usage averge")
  @GetMapping(value = "/statistics/average", produces = "application/json")
  public IpAddressInfoStatisticsDTO getDistanceAverage() {
    final Instant start = Instant.now();
    logger.info("Getting the average distance saved");
    IpAddressInfoStatisticsDTO response = countryInfoService.getDistanceAverage();
    logger.info("Finished getting average distance saved, took {}ms", millisToNow(start));
    return response;
  }

  @ApiOperation(value = "Return all the country info saved")
  @GetMapping(value = "/ips", produces = "application/json")
  public List<com.meli.challenge.model.IpAddressInfo> getAllPpsSaved() {
    final Instant start = Instant.now();
    logger.info("The details from all ip consulted");
    List<com.meli.challenge.model.IpAddressInfo> response = countryInfoService.getAllIpConsulted();
    logger.info("Finished getting details from all ip consulted, took {}ms", millisToNow(start));
    return response;
  }

}
