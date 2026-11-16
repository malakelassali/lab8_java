package ex2;

import java.util.Iterator;

/**
 * Test driver for the Pair class.
 *
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version V3, 8/2021
 *
 */
public class PairDriver {

  /**
   * Create several stadium pairs then print the name of the stadium with the largest capacity.
   *
   * @param args Not used.
   */
  public static void main(String[] args) {

    @SuppressWarnings("unchecked")
    Pair<String, Integer>[] stadiums = new Pair[3];
    stadiums[0] = new Pair<>("Bridgeforth Stadium", 25000);
    stadiums[1] = new Pair<>("Michigan Stadium", 109901);
    stadiums[2] = new Pair<>("Lane Stadium", 66233);

    System.out.println(largestStadium(stadiums));

  }

  /**
   * Returns the name of the stadium with the largest capacity.
   *
   * @param stadiums An array of Pairs where each pair contains a stadium name and an integer
   *        capacity
   * @return The name of the stadium with the largest capacity
   */
  public static String largestStadium(Pair<String, Integer>[] stadiums) {

    int maxSize = 0;
    String largest = null;

    for (Pair<String, Integer> stadium : stadiums) {
      Integer size = stadium.getSecond();
      if (size > maxSize) {
        largest = stadium.getFirst();
        maxSize = size;
      }
    }

    return largest;
  }

  /**
   * Iterable collection of Pair objects.
   *
   * @author Michael S. Kirkpatrick and Nathan Sprague
   * @version V2, 8/2021
   */
  public static class PairList<F, S> implements Iterable<Pair<F, S>> {

    public static int CAPACITY = 10;

    private Pair<F, S>[] pairs;
    private int size;

    /**
     * Create a collection that will store items added as pairs.
     */
    public PairList() {
      // TODO: Initialize the array of pairs.
      // TODO: Initialize the size instance variable to 0.
    }

    /**
     * Create a new Pair and add it to the collection if there's space.
     *
     * @param first The first object.
     * @param second The second object.
     * @return True if the pair was added to the array, False otherwise.
     */
    public boolean addPair(F first, S second) {
      // UNFINISHED
      return true;
    }


    @Override
    public Iterator<Pair<F, S>> iterator() {
      return new PairListIterator();
    }

    /*
     * Implement an Iterator here based on the API documentation at <a
     * https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/
     * Iterator.html Throw the exceptions as specified.
     *
     * IMPLEMENTATION ADVICE:
     *
     * You will need two instance variables: one to keep track of the current index,
     * and one to keep track of whether or not a removal should be allowed. (The
     * remove method removes the element most recently returned by next. If remove
     * is called before next has been called, or if it is called twice in a row, the
     * remove method must throw an IllegalStateException.)
     *
     * I recommend drawing a picture of an array to help you reason through the
     * behavior of the required methods.
     *
     */
    private class PairListIterator implements Iterator<Pair<F, S>> {

      // TODO: Declare any necessary instance variables for maintaining iterator
      // state.

      @Override
      public boolean hasNext() {

        // Compare your iterator index variable to the size variable of the ex2.PairDriver.PairList.

        throw new UnsupportedOperationException();
      }

      /**
       * Return the next Pair in the iterator.
       */
      @Override
      public Pair<F, S> next() {

        // This method should call your hasNext method to determine whether there are
        // any remaining items. If not, the appropriate exception must be thrown.

        throw new UnsupportedOperationException();
      }

      /**
       * Remove the previous Pair returned by next().
       */
      @Override
      public void remove() {

        // The remove method should shift all pairs one position to the left to prevent
        // a gap in the array.

        throw new UnsupportedOperationException();
      }
    }

  }
}