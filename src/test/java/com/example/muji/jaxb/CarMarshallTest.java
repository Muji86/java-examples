package com.example.muji.jaxb;

import com.example.muji.date.CustomDateUtil;
import com.example.muji.pojo.xml.CarType;
import com.example.muji.pojo.xml.ObjectFactory;
import com.example.muji.pojo.xml.RegType;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.StringWriter;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class CarMarshallTest {
  private final ObjectFactory objectFactory = new ObjectFactory();

  @Test
  public void marshal_car_produces_expectedString_with_correct_regDate() throws JAXBException {
    // Given
    RegType regType = objectFactory.createRegType();
    regType.setRegistrationNumber("KV61XUB");
    XMLGregorianCalendar xmlGregorianCalendar =
        CustomDateUtil.convert(LocalDate.of(2011, 8, 13), XMLGregorianCalendar.class);
    regType.setRegistrationDate(xmlGregorianCalendar);
    CarType carType = objectFactory.createCarType();
    carType.setReg(regType);
    carType.setMake("Peugeot");
    carType.setModel("5008");
    JAXBElement<CarType> car = objectFactory.createCar(carType);
    JAXBContext jaxbContext = JAXBContext.newInstance(CarType.class);

    // When
    Marshaller marshaller = jaxbContext.createMarshaller();
    StringWriter writer = new StringWriter();
    marshaller.marshal(car, writer);

    // Then
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Car><Reg><RegistrationNumber>KV61XUB</RegistrationNumber><RegistrationDate>2011-08-13</RegistrationDate></Reg><Make>Peugeot</Make><Model>5008</Model></Car>",
        writer.toString());
  }
}
