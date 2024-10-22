package pas.sorting;

import java.util.List;

public class ProfileDriver {
  public static void main(String[] args) {

    // Create a SortProfiler object.
    // See the JavaDoc for an explanation of the parameters.
    //SortProfiler(sorts, sortName, start, interval, max, trials, gen)
    SortProfiler sp = new SortProfiler(List.of(MergeSort::mergeSort), List.of("mergesort"),
              0, 1, 50, 1000,
              Generators::generateRandom);

    // Run the profiler and send the output to stdout.
    sp.run(System.out);





















    //INSERTION SORT
    // SortProfiler insertion = new SortProfiler(List.of(BasicSorts::insertionSort), List.of("insertion sort"),
    // 0, 1, 50, 1000,
    // Generators::generateRandom);


    // insertion.run(System.out);
  }

}
