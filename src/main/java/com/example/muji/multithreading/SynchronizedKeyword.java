package com.example.muji.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * Example for use of synchronize keyword
 */
public class SynchronizedKeyword {

  private int count;

  public SynchronizedKeyword() {
    count = 0;
  }

  public int increment() throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(getRandomLongBetweenRange(0, 10));
    this.count = count + 1;
    return this.count;
  }

  public synchronized int safeIncrement() throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(getRandomLongBetweenRange(0, 10));
    this.count = count + 1;
    return this.count;
  }

  private static long getRandomLongBetweenRange(double min, double max){
    double x = (Math.random()*((max-min)+1))+min;
    return (long)x;
  }

  public int getCount() {
    return count;
  }
}
