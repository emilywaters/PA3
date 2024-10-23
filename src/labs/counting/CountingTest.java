/**
 * Submission tests for the CS 240 Counting lab.
 */
package labs.counting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CountingTest {

  @Test
  public void testPredictMaxTripleSteps() {
    assertEquals(294, Counting.predictMaxTripleSteps(100));
    assertEquals(594, Counting.predictMaxTripleSteps(200));
  }

  @Test
  public void testPredictCountDoublesSteps() {
    assertEquals(1024, Counting.predictCountDoublesSteps(128));
    assertEquals(80, Counting.predictCountDoublesSteps(16));
    assertEquals(11264, Counting.predictCountDoublesSteps(1024));
  }

  @Test
  public void testPredictTweakNumbersSteps() {
    assertEquals(135, Counting.predictTweakNumbersSteps(128));
    assertEquals(20, Counting.predictTweakNumbersSteps(16));
    assertEquals(1034, Counting.predictTweakNumbersSteps(1024));
  }

  @Test
  public void testDoQuadraticWork() {
    Counter counter = new Counter();
    Counting.doQuadraticWork(100, counter);
    assertEquals(200007, counter.getCount());

    counter = new Counter();
    Counting.doQuadraticWork(11, counter);
    assertEquals(2427, counter.getCount());
  }

  @Test
  public void testPredictDoQuadraticWorkSteps() {
    assertEquals(200007, Counting.predictDoQuadraticWorkSteps(100));
    assertEquals(2427, Counting.predictDoQuadraticWorkSteps(11));
  }

  @Test
  public void testDoLogCubicWork() {
    Counter counter = new Counter();
    Counting.doLogCubicWork(16, counter);
    assertEquals(16544, counter.getCount());

    counter = new Counter();
    Counting.doLogCubicWork(128, counter);
    assertEquals(14681344, counter.getCount());
  }

  @Test
  public void testPredictDoLogCubicWorkSteps() {
    assertEquals(16544, Counting.predictDoLogCubicWorkSteps(16));
    assertEquals(14681344, Counting.predictDoLogCubicWorkSteps(128));
  }

  @Test
  public void testDoHalfQuadraticWork() {
    Counter counter = new Counter();
    Counting.doHalfQuadraticWork(100, counter);
    assertEquals(5150, counter.getCount());

    counter = new Counter();
    Counting.doHalfQuadraticWork(200, counter);
    assertEquals(20300, counter.getCount());
  }

  @Test
  public void testPredictDoHalfQuadraticWorkSteps() {
    assertEquals(5150, Counting.predictDoHalfQuadraticSteps(100));
    assertEquals(20300, Counting.predictDoHalfQuadraticSteps(200));
  }


}
