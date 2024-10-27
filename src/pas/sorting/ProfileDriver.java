package pas.sorting;

import java.util.List;

public class ProfileDriver {
  public static void main(String[] args) {

    // Create a SortProfiler object.
    // See the JavaDoc for an explanation of the parameters.
    //SortProfiler(sorts, sortName, start, interval, max, trials, gen)
    // SortProfiler sp = new SortProfiler(List.of(MergeSort::mergeSort), List.of("mergesort"),
    //           0, 1, 50, 10000,
    //           Generators::generateRandom);

    // //Run the profiler and send the output to stdout.
    // sp.run(System.out);

    SortProfiler sp1 = new SortProfiler(List.of(MergeSortImproved::mergeSortAdaptive), List.of("adaptive"),
              1000, 1000, 10000, 1000,
              Generators::generateRandom);

    // Run the profiler and send the output to stdout.
    sp1.run(System.out);
  }

}
