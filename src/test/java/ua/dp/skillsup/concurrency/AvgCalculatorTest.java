package ua.dp.skillsup.concurrency;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;


public class AvgCalculatorTest {

  private final AvgCalculator avgCalculator = new AvgCalculator();
  private final double delta = 0.00001;
  private int len = 100000000;
  private double[] arr = generateArray(len);
  private double expectedAvg = avgCalculator.getAvg(arr);

  private double[] generateArray(int len){
    double[] arr = new double[len];
    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextDouble();
    }
    return arr;
  }

  @Rule
  public Stopwatch stopwatch = new Stopwatch() {
    @Override
    protected void succeeded(long nanos, Description description) {
      System.out.println(description.getMethodName() + " took " + nanos + " nanoseconds to complete");
    }
  };

  @Test
  public void getAvg1() throws Exception {
    assertEquals(expectedAvg, avgCalculator.getAvg(arr), delta);
  }

  @Test
  public void getAvg2() throws Exception {
    assertEquals(expectedAvg, avgCalculator.getAvg(arr), delta);
  }

  @Test
  public void getAvgConcurrent() throws Exception {
    assertEquals(expectedAvg, avgCalculator.getAvgConcurrent(arr, 10), delta);
  }

  @Test
  public void getAvgThreadPool() throws Exception {
    assertEquals(expectedAvg, avgCalculator.getAvgThreadPool(arr, 5, 10), delta);
  }
}