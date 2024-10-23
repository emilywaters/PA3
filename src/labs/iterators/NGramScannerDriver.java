package labs.iterators;

import java.util.Iterator;

public class NGramScannerDriver {

  public static void main(String[] args) {
    String[] words = {"I", "love", "computer", "science"};

    // Example: 1-grams (unigrams)
    Iterator<String> it = new NGramScanner(words, 1).iterator();
    for (String word : words) {
      while (it.hasNext()) {
        System.out.println(it.next());
      }
    }
  }
}
