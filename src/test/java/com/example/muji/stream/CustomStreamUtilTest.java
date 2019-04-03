package com.example.muji.stream;

import lombok.Builder;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomStreamUtilTest {
  List<Person> people;
  @Before
  public void setup() {
    people = new ArrayList();
    people.add(Person.builder().name("Hajar").age(4).build());
    people.add(Person.builder().name("Sarah").age(27).build());
    people.add(Person.builder().name("Mujahid").age(33).build());
    people.add(Person.builder().name("Kais").age(2).build());
    people.add(Person.builder().name("Sidrah").age(9).build());
  }

  @Test
  public void findOldestPerson_test() {
    Person person = CustomStreamUtil.findMax(people, Comparator.comparingInt(Person::getAge));
    assertNotNull(person);
    assertEquals("Mujahid", person.getName());
  }

  @Test
  public void findYoungestPerson_test() {
    Person person = CustomStreamUtil.findMin(people, Comparator.comparingInt(Person::getAge));
    assertNotNull(person);
    assertEquals("Kais", person.getName());
  }

  @Test
  public void checkPeopleStats_test() {
    IntSummaryStatistics stats = CustomStreamUtil.getStats(people, Person::getAge);
    assertEquals(33, stats.getMax());
    assertEquals(2, stats.getMin());
    assertEquals(5, stats.getCount());
    assertEquals(75, stats.getSum());
    assertEquals(15.0, stats.getAverage(), 0);
  }
}

@Data
@Builder
class Person {
  private String name;
  private int age;
}
