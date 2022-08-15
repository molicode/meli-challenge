package com.meli.challenge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IPConsulted")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpAddressInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String iPAddress;

  private String countryIsoCode;

  private String countryName;

  private Double distanceBetweenBsAsToTargetCountry;

}
