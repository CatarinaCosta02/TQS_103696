package org.example;

import java.util.ArrayList;
import java.util.Stack;

public class TqsStack<T> {

    private ArrayList<T> stack;
    private int maxSize;

    public TqsStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new ArrayList<T>();
    }

    // add an item on the top of the stack
    public void push(T element) {
        if (this.stack.size() < this.maxSize) {
            this.stack.add(element);
        }
    }

    // remove an item from the top of the stack
    public T pop() {
        if (this.stack.size() > 0) {
            return this.stack.remove(this.stack.size() - 1);
        }
        return null;
    }

    // return the top element of the stack
    public T peek() {
        if (this.stack.size() > 0) {
            return this.stack.get(this.stack.size() - 1);
        }
        return null;
    }

    // return the number of elements in the stack
    public int size() {
        return this.stack.size();
    }

    // return true if the stack is empty
    public boolean isEmpty() {
        return this.stack.size() == 0;
    }




}
