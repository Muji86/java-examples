package com.example.muji.stream;

import com.example.muji.validation.Person;
import lombok.Builder;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  @Test
  public void checkIterateIntStream_test() {
    // When
    Stream<Integer> integerStream = CustomStreamUtil.streamIterate(20, i -> i + 10, 10);
    List<Integer> collect = integerStream.collect(Collectors.toList());

    // Then
    assertEquals(10, collect.size());
    assertEquals(20, collect.get(0).intValue());
    assertEquals(30, collect.get(1).intValue());
    assertEquals(110, collect.get(9).intValue());
  }

  @Test
  public void checkIteratePersonStream_test() {
    // Given
    Person person = Person.builder().age(5).name("Person_1").build();

    // When
    UnaryOperator<Person> personUnaryOperator =
            person1 -> Person.builder().age(person1.getAge() + 5).name(person1.getName() + "1").build();
    Stream<Person> personStream = CustomStreamUtil.streamIterate(person, personUnaryOperator, 5);
    List<Person> collect = personStream.collect(Collectors.toList());

    // Then
    assertEquals(5, collect.size());
    assertEquals(5, collect.get(0).getAge());
    assertEquals("Person_1", collect.get(0).getName());
    assertEquals(10, collect.get(1).getAge());
    assertEquals("Person_11", collect.get(1).getName());
    assertEquals(25, collect.get(4).getAge());
    assertEquals("Person_11111", collect.get(4).getName());

  }
}
