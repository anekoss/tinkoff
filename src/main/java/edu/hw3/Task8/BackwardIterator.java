package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BackwardIterator<T> implements Iterator<T> {

    private final T[] collection;
    private int pointer;

    public BackwardIterator(List<T> collection) {
        this.collection = (T[]) collection.toArray();
        pointer = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        if (pointer < 0) {
            log.info("collection is ended");
        }
        return pointer >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return collection[pointer--];
    }

}
