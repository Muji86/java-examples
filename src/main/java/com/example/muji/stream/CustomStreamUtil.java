package com.example.muji.stream;

import java.util.Collection;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class CustomStreamUtil {

  /**
   * Find object T considered maximum by the provided comparator
   * @param collection
   * @param comparator
   * @param <T>
   * @return T considered maximum by the provided comparator
   */
  public static <T> T findMax(Collection<T> collection, Comparator<T> comparator) {
    return collection.stream().max(comparator).get();
  }

  /**
   * @param collection
   * @param comparator
   * @param <T>
   * @return T considered minimum by the provided comparator
   */
  public static <T> T findMin(Collection<T> collection, Comparator<T> comparator) {
    return collection.stream().min(comparator).get();
  }

  /**
   * Get IntSummaryStats of a collection
   * @param collection
   * @param intFunction
   * @param <T>
   * @return Int Summary statistics of the int attribute returned by intFunction
   */
  public static <T> IntSummaryStatistics getStats(Collection<T> collection, ToIntFunction<T> intFunction) {
    return collection.stream()
        .collect(Collectors.summarizingInt(intFunction));
  }

}
