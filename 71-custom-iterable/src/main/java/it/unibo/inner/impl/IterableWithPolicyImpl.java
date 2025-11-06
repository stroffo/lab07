package it.unibo.inner.impl;

import java.util.AbstractCollection;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> extends AbstractCollection<T> implements IterableWithPolicy<T> {
    private final T[] arr;
    private int size;
    private Predicate<T> filter;
    
    public IterableWithPolicyImpl(T[] arr, Predicate<T> filter) {
        this.arr = arr;
        
        setIterationPolicy(filter);
    }

    public IterableWithPolicyImpl(T[] arr) {
        this(arr, x -> true);
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
        this.size = 0;
        for (int i = 0; i < arr.length; i++) {
            if (filter.test(this.arr[i])) {
                this.size++;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    private class Iterator implements java.util.Iterator<T> {
        private int index;

        public Iterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            for (int i = index; i < arr.length; i++) {
                if (filter.test(arr[i])) return true;
            }
            return false;
        }

        @Override
        public T next() {
            while(!filter.test(arr[index])) index++;    

            return arr[index++];
        }
    }
}
