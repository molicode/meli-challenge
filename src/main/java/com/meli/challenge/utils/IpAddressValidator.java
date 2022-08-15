package com.meli.challenge.utils;

import java.util.regex.Pattern;

public class IpAddressValidator {

  /**
   * ^                       #  start of the line (                     #  start of group #1 [01]?[0-9][0-9]?    #  can be one or two
   * digits. If three digits appear, it must start either 0 or 1 |                   #    ...or 2[0-4][0-9]         #    start with 2,
   * follow by 0-4 and end with any digit (2[0-4][0-9]) |                   #    ...or 25[0-5]             #    start with 2, follow by 5
   * and ends with 0-5 (25[0-5]) )                     #  end of group  #1 \\.                   #  follow by a dot "." # repeat it 3 times
   * (3x) $                       # end of the line
   */
  private static final String IPV4_PATTERN_ALLOW_LEADING_ZERO =
      "^([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\." +
          "([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\." +
          "([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])\\." +
          "([01]?[0-9][0-9]?|2[0-4][0-9]|25[0-5])$";

  public static Boolean validate(final String ipAddress) {
    return Pattern.compile(IPV4_PATTERN_ALLOW_LEADING_ZERO)
        .matcher(ipAddress)
        .matches();
  }

}
