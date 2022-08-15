package com.meli.challenge.service.rest.ip.location.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ITLConnectionDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private int asn;

  private String isp;

}
