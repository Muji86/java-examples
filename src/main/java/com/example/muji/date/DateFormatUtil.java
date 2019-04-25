package com.example.muji.date;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {

  /**
   *
   * @param calendar
   * @param formatter
   * @return formatted string of the XMLGregorianCalendar arg
   */
  public static String format(XMLGregorianCalendar calendar, DateTimeFormatter formatter) {
    return calendar.toGregorianCalendar().toZonedDateTime().format(formatter);
  }

  public static XMLGregorianCalendar parseToXMLGregorianCalendar(String stringDate) {
    LocalDate localDate = LocalDate.parse(stringDate, DateTimeFormatter.ISO_LOCAL_DATE);
    return CustomDateUtil.convert(localDate, XMLGregorianCalendar.class);
  }

  /**
   *
   * @param calendar
   * @return string format in LOCAL_DATE (yyyy-MM-dd) of the XMLGregorianCalendar
   */
  public static String formatLocalDate(XMLGregorianCalendar calendar) {
    return format(calendar, DateTimeFormatter.ISO_LOCAL_DATE);
  }
}
