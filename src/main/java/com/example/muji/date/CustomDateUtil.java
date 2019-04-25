package com.example.muji.date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

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

  /**
   * Convert java.time.LocalDateTime to T
   * @param localDate
   * @return T
   * @throws DatatypeConfigurationException
   */
  public static <T> T convert(LocalDate localDate, Class<T> type) {
    Object convertedDate = null;

    if(XMLGregorianCalendar.class.isAssignableFrom(type)) {
        GregorianCalendar gcal = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        convertedDate =getXMLGregorianCalendar(gcal);
    }

    return type.cast(convertedDate);
  }

  /**
   * Convert java.time.LocalDateTime to T
   * @param zonedDateTime
   * @return T
   * @throws DatatypeConfigurationException
   */
  public static <T> T convert(ZonedDateTime zonedDateTime, Class<T> type) {
    Object convertedDate = null;

    if(XMLGregorianCalendar.class.isAssignableFrom(type)) {
       convertedDate = getXMLGregorianCalendar(GregorianCalendar.from(zonedDateTime));
    }

    return type.cast(convertedDate);
  }

  /**
   * Construct XMLGregorianCalendar from GregorianCalendar
   * @param gcal
   * @return XMLGregorianCalendar
   */
  private static XMLGregorianCalendar getXMLGregorianCalendar(GregorianCalendar gcal) {
    XMLGregorianCalendar xmlGregorianCalendar = null;
    try {
      xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
    } catch (DatatypeConfigurationException e) {
      e.printStackTrace();
    }

    return xmlGregorianCalendar;
  }
}
