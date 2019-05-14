package com.example.muji.multithreading;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SynchronizedKeywordTest {
  private SynchronizedKeyword synchronizedKeyword;

  @Before
  public void setup() {
    this.synchronizedKeyword = new SynchronizedKeyword();
  }

  @Test
  public void non_thread_safe_increment_test() throws InterruptedException {
    // Given
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    List<Callable<Integer>> callables = new ArrayList<>();
    IntStream.range(0, 1000).forEach(i -> callables.add(() -> synchronizedKeyword.increment()));

    // When
    executorService.invokeAll(callables);
    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.SECONDS);

    // Then
    assertNotEquals(1000, synchronizedKeyword.getCount());
  }

  @Test
  public void thread_safe_increment_test() throws InterruptedException {
    // Given
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    List<Callable<Integer>> callables = new ArrayList<>();
    IntStream.range(0, 1000).forEach(i -> callables.add(() -> synchronizedKeyword.safeIncrement()));

    // When
    executorService.invokeAll(callables);
    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.SECONDS);

    // Then
    assertEquals(1000, synchronizedKeyword.getCount());
  }
}
