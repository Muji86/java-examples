package com.example.muji.date;

import org.junit.Test;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class DateFormatUtilTest {

  @Test
  public void xmlGregorianCalendar_localDate_format() {
    XMLGregorianCalendar xmlGregorianCalendar =
        CustomDateUtil.convert(LocalDate.of(2019, 4, 25), XMLGregorianCalendar.class);

    String formatted = DateFormatUtil.format(xmlGregorianCalendar, DateTimeFormatter.ISO_LOCAL_DATE);
    String formatted2 = DateFormatUtil.formatLocalDate(xmlGregorianCalendar);
    assertEquals("2019-04-25", formatted);
    assertEquals(formatted, formatted2);
  }

  @Test
  public void parse_to_xmlGregorianCalendar() {
    XMLGregorianCalendar xmlGregorianCalendar =
        DateFormatUtil.parseToXMLGregorianCalendar("2019-04-25");
    assertEquals(2019, xmlGregorianCalendar.getYear());
    assertEquals(4, xmlGregorianCalendar.getMonth());
    assertEquals(25, xmlGregorianCalendar.getDay());

  }

}
