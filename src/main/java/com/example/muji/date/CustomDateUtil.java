package com.example.muji.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CustomDateUtil {

  /**
   * Convert java.time.LocalDateTime to java.util.Date
   * @param localDateTime
   * @return
   */
  public static Date convert(LocalDateTime localDateTime) {
    return java.util.Date
        .from(localDateTime.atZone(ZoneId.systemDefault())
            .toInstant());
  }

  /**
   * Convert java.util.Date to java.time.LocalDateTime
   * @param dateToConvert
   * @return
   */
  public static LocalDateTime convert(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
  }

//
}
