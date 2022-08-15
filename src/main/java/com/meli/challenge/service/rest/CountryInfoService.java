package com.meli.challenge.service.rest;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import static com.meli.challenge.utils.CurrencyMessageFormatter.format;
import static com.meli.challenge.utils.DistanceCalculator.distance;
import static com.meli.challenge.utils.DistanceMessageFormatter.format;
import static com.meli.challenge.utils.IpAddressValidator.validate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.meli.challenge.converters.IpStatisticsConverter;
import com.meli.challenge.converters.LanguageToRestConverter;
import com.meli.challenge.converters.TimezonesToRestConverter;
import com.meli.challenge.exception.NotValidIPException;
import com.meli.challenge.model.Currency;
import com.meli.challenge.model.FullCountryInfo;
import com.meli.challenge.model.IpAddressInfo;
import com.meli.challenge.model.dto.IpAddressInfoDTO;
import com.meli.challenge.model.dto.IpAddressInfoStatisticsDTO;
import com.meli.challenge.repository.IpAddressInfoRepository;
import com.meli.challenge.service.rest.exchange.rates.ExchangeRatesRestClient;
import com.meli.challenge.service.rest.exchange.rates.dto.ERCountryCodesDTO;
import com.meli.challenge.service.rest.exchange.rates.dto.ERExchangeDTO;
import com.meli.challenge.service.rest.ip.location.IpToLocationRestClient;
import com.meli.challenge.service.rest.ip.location.dto.ITLCountryDTO;
import com.meli.challenge.service.rest.ip.location.dto.ITLCurrencyDTO;
import com.meli.challenge.service.rest.ipapi.IpApiRestClient;
import com.meli.challenge.service.rest.ipapi.dto.IpApiCountryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CountryInfoService {

  private static final Logger logger = LoggerFactory.getLogger(CountryInfoService.class);

  private final IpApiRestClient ipapiRestClient;

  private final IpToLocationRestClient countryLayerClient;

  private final ExchangeRatesRestClient exchangeRatesClient;

  private final LanguageToRestConverter languageConverter;

  private final TimezonesToRestConverter timezonesConverter;

  private final IpAddressInfoRepository ipAddressInfoRepository;

  public CountryInfoService(final IpApiRestClient ipapiRestClient, final LanguageToRestConverter languageConverter,
      final IpToLocationRestClient countryLayerClient, final ExchangeRatesRestClient exchangeRatesClient,
      final TimezonesToRestConverter timezonesConverter, final IpAddressInfoRepository ipAddressInfoRepository) {
    this.ipapiRestClient = ipapiRestClient;
    this.languageConverter = languageConverter;
    this.countryLayerClient = countryLayerClient;
    this.exchangeRatesClient = exchangeRatesClient;
    this.timezonesConverter = timezonesConverter;
    this.ipAddressInfoRepository = ipAddressInfoRepository;
  }

  public FullCountryInfo countryInfoByIP(final String ip) {

    if (Boolean.FALSE.equals(validate(ip))) {
      logger.info("The IPv4: {}  entered it is invalid", ip);
      throw new NotValidIPException("The IPv4: " + ip + " entered it is invalid");
    }

    final LocalDateTime localDateTimeInUTC = LocalDateTime.now();
    final IpApiCountryDTO ipInfo = ipapiRestClient.info(ip);
    final ITLCountryDTO itlCountryDTO = countryLayerClient.getAll(ip);
    final ERCountryCodesDTO countryCodes = exchangeRatesClient.getAllCountryCodes();
    final ERExchangeDTO exchangeRates = exchangeRatesClient.getCountryCodeExchangeRates(getAllCountryCodes(countryCodes));
    final double distanceBetweenBsAsToTargetCountry = distance(itlCountryDTO.getLatitude(), itlCountryDTO.getLongitude());
    final String formattedDistance = format(distanceBetweenBsAsToTargetCountry, itlCountryDTO.getLatitude(), itlCountryDTO.getLongitude());
    final List<Currency> currencies = getCountryCurrencies(itlCountryDTO.getCurrencies(), exchangeRates.getRates());
    final List<String> formattedCurrency = format(currencies);

    final FullCountryInfo fullCountryInfo = FullCountryInfo.builder()
        .ip(ip)
        .currentTimeInUTC(localDateTimeInUTC)
        .name(itlCountryDTO.getCountryName())
        .iSOCode(itlCountryDTO.getCountryCode())
        .languages(languageConverter.convertAll(ipInfo.getLocation().getLanguages()))
        .currencies(formattedCurrency)
        .currentTimes(timezonesConverter.convertAll(itlCountryDTO.getTimezones(), localDateTimeInUTC))
        .distanceBetweenBsAsToTargetCountry(formattedDistance)
        .build();

    ipAddressInfoRepository.save(com.meli.challenge.model.IpAddressInfo.builder()
        .iPAddress(ip)
        .countryIsoCode(fullCountryInfo.getISOCode())
        .countryName(fullCountryInfo.getName())
        .distanceBetweenBsAsToTargetCountry(distanceBetweenBsAsToTargetCountry)
        .build());

    return fullCountryInfo;
  }

  private String getAllCountryCodes(final ERCountryCodesDTO countryCodes) {
    return countryCodes.getSymbols()
        .keySet()
        .stream()
        .collect(joining(","));
  }

  private List<Currency> getCountryCurrencies(final List<ITLCurrencyDTO> countryInfoCurrency, final Map<String, Double> rates) {

    return countryInfoCurrency.stream()
        .map(currency -> Currency.builder()
            .code(currency.getCode())
            .name(currency.getName())
            .symbol(currency.getSymbol())
            .rate(String.valueOf(rates.getOrDefault("USD", -1D)))
            .build())
        .collect(toList());
  }

  public List<com.meli.challenge.model.IpAddressInfo> getAllIpConsulted() {
    return (List<com.meli.challenge.model.IpAddressInfo>) ipAddressInfoRepository.findAll();
  }

  public IpAddressInfoDTO getFurthestDistanceToBsAs() {
    try {
      final IpAddressInfo info = ipAddressInfoRepository.furthest()
          .orElseThrow(() -> new NoSuchElementException("No furthest distance found"));
      return IpAddressInfoDTO.builder()
          .iPAddress(info.getIPAddress())
          .countryName(info.getCountryName())
          .distanceBetweenBsAsToTargetCountry(info.getDistanceBetweenBsAsToTargetCountry())
          .build();
    } catch (NoSuchElementException ex) {
      logger.info("Some problem occurred trying to execute the furthest distance query");
      return null;
    }
  }

  public IpAddressInfoDTO getClosestDistanceToBsAs() {
    try {
      final IpAddressInfo info = ipAddressInfoRepository.closest()
          .orElseThrow(() -> new NoSuchElementException("No closet distance found"));
      return IpAddressInfoDTO.builder()
          .iPAddress(info.getIPAddress())
          .countryName(info.getCountryName())
          .distanceBetweenBsAsToTargetCountry(info.getDistanceBetweenBsAsToTargetCountry())
          .build();
    } catch (NoSuchElementException ex) {
      logger.info("Some problem occurred trying to execute the closest distance query");
      return null;
    }
  }

  public IpAddressInfoStatisticsDTO getDistanceAverage() {
    return Optional.ofNullable(ipAddressInfoRepository.averageDistanceToBsAs().stream()
            .map(IpStatisticsConverter::convertToItem)
            .collect(toList()).get(0))
        .orElseThrow(() -> new NoSuchElementException("No furthest distance found"));
  }

}
