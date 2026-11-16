package ex2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable collection of Pair objects.
 * 
 * @author Michael S. Kirkpatrick and Nathan Sprague
 * @version V2, 8/2021
 */
public class PairList<F, S> implements Iterable<Pair<F, S>> {

  public static int CAPACITY = 10;

  private Pair<F, S>[] pairs;
  private int size;

  /**
   * Create a collection that will store items added as pairs.
   */
  @SuppressWarnings("unchecked")
  public PairList() {
    // TODO: Initialize the array of pairs.
    // TODO: Initialize the size instance variable to 0.

    this.pairs= (Pair<F, S>[]) new Pair<?, ?>[CAPACITY];
    this.size= 0;
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
    if (size == pairs.length) {
      return false;
    }
    pairs[size]= new Pair<>(first, second);
    size++;
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
    private int cursor= 0;
    private int lastReturned= -1;

    @Override
    public boolean hasNext() {
      // Compare your iterator index variable to the size variable of the PairList.
      return cursor < size;
    }

    /**
     * Return the next Pair in the iterator.
     */
    @Override
    public Pair<F, S> next() {
      // This method should call your hasNext method to determine whether there are
      // any remaining items. If not, the appropriate exception must be thrown.
      if (!hasNext()) throw new NoSuchElementException();

      lastReturned = cursor;
      cursor++;
      return pairs[lastReturned];
    }

    /**
     * Remove the previous Pair returned by next().
     */
    @Override
    public void remove() {
      // The remove method should shift all pairs one position to the left to prevent
      // a gap in the array.
      if (lastReturned == -1) {
        throw new IllegalStateException();
      }

      for (int i=lastReturned; i<size-1; i++) {
        pairs[i]= pairs[i+1];
      }

      size--;
      pairs[size]= null;
      cursor= lastReturned;
      lastReturned= -1;
    }
  }

}