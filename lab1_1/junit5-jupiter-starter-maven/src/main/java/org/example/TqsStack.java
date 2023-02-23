package org.example;

import java.util.Stack;

public class TqsStack {

    private ArrayList<T> stack;
    private int maxSize;

    public TqsStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new ArrayList<T>();
    }

    public void push(T element) {
        if (this.stack.size() < this.maxSize) {
            this.stack.add(element);
        }
    }

    public T pop() {
        if (this.stack.size() > 0) {
            return this.stack.remove(this.stack.size() - 1);
        }
        return null;
    }

    public T peek() {
        if (this.stack.size() > 0) {
            return this.stack.get(this.stack.size() - 1);
        }
        return null;
    }

    public int size() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        return this.stack.size() == 0;
    }




}
