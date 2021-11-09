package HashMap;

public interface MySet {

    boolean add(String e); // Returns true if this set did not already contain the specified element

    void clear();

    boolean contains(String o);

    boolean isEmpty();

    boolean remove(String o); // Returns true if this set contained the specified element

    int size();

    String[] toArray();

}
