package demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * Unit test for HelloWorld.
 *
 * @author Kent Beck
 * @version 09/10/2017
 */
public class HelloWorldTest {

  /**
   * Test the main method.
   */
  @Test
  public void testMain() {
    // redirect system output and run main
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    System.setOut(new PrintStream(output));
    HelloWorld.main(new String[] {});
    // compare expected with actual output
    String expect = "Hello, World!" + System.lineSeparator();
    String actual = output.toString();
    assertEquals(expect, actual);
  }

}
