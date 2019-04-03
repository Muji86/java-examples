package com.example.muji.date;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class CustomDateUtilTest {
  private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


  @Before
  public void setup() {
    sdf.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
  }

  @Test
  public void convertLocalDateToDate_test() {
    // Given
    LocalDateTime now = LocalDateTime.of(2019, 4, 3, 14, 23, 12);

    // When
    Date convert = CustomDateUtil.convert(now);
    String formatted = sdf.format(convert);

    // Then
    assertEquals("2019-04-03 13:23:12+0000", formatted);
  }

  @Test
  public void convertDateToLocalDate_test() {
    // Given
    Calendar calendar = Calendar.getInstance();
    calendar.set(2019, 1, 13, 12, 23, 12);
    Date date = calendar.getTime();


    // When
    LocalDateTime localDateTime = CustomDateUtil.convert(date);
    String format = localDateTime.format(formatter);

    // Then
    assertEquals("2019-02-13 12:23:12", format);
  }

}
