package com.meli.challenge.converters;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import com.meli.challenge.model.Language;
import com.meli.challenge.service.rest.ipapi.dto.IpApiLanguageDTO;
import org.springframework.stereotype.Component;

@Component
public class LanguageToRestConverter {

  public List<Language> convertAll(final ArrayList<IpApiLanguageDTO> languagesDTO) {
    return languagesDTO.stream()
        .map(this::convert)
        .collect(toList());

  }

  public Language convert(final IpApiLanguageDTO ipApiLanguageDTO) {
    return Language.builder()
        .code(ipApiLanguageDTO.getCode())
        .name(ipApiLanguageDTO.getName())
        .build();
  }
}

