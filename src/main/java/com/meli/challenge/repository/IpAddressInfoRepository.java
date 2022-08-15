package com.meli.challenge.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.meli.challenge.model.IpAddressInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ipAddressInfoRepository")
public interface IpAddressInfoRepository extends CrudRepository<IpAddressInfo, Long> {

  @Query(nativeQuery = true,
      value = "SELECT "
          + "* "
          + "FROM IPCONSULTED "
          + "ORDER BY DISTANCE_BETWEEN_BS_AS_TO_TARGET_COUNTRY ASC "
          + "LIMIT 1")
  Optional<IpAddressInfo> closest();

  @Query(nativeQuery = true,
      value = "SELECT "
          + "* "
          + "FROM IPCONSULTED "
          + "ORDER BY DISTANCE_BETWEEN_BS_AS_TO_TARGET_COUNTRY DESC "
          + "LIMIT 1")
  Optional<IpAddressInfo> furthest();

  @Query(nativeQuery = true, value =
      "SELECT AVG(DISTANCE_BETWEEN_BS_AS_TO_TARGET_COUNTRY) AS average, " +
          "MIN(DISTANCE_BETWEEN_BS_AS_TO_TARGET_COUNTRY) AS min, " +
          "MAX(DISTANCE_BETWEEN_BS_AS_TO_TARGET_COUNTRY) AS max, " +
          "SUM(DISTANCE_BETWEEN_BS_AS_TO_TARGET_COUNTRY) AS sum, " +
          "count(*) AS quantity FROM IPCONSULTED")
  List<Map<String, ?>> averageDistanceToBsAs();
}