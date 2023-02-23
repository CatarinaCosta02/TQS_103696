package tests;

import org.example.TqsStack;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TqsStackTests {
    private static TqsStack<Integer> testStack;

    @BeforeEach
    void setup() {
        testStack = new TqsStack<>(10);
    }

    @AfterEach
    void tearDown() {
        testStack = null;
    }


    @Test
    @DisplayName("A stack is empty on construction")
    public void stackIsEmptyOnConstruction() {
        TqsStack<Integer> stack = new TqsStack<>(10);
        assertEquals(true, stack.isEmpty());
    }


    @Test
    @DisplayName("A stack has size 0 on construction")
    public void stackHasSizeZeroOnConstruction() {
        TqsStack<Integer> stack = new TqsStack<>(10);
        assertEquals(0, stack.size());
    }


    @Test
    @DisplayName("After n pushes to an empty stack, n >0, the stack is not empty and its size is n")
    public void afterNPushesToAnEmptyStack() {
        testStack = new TqsStack<>(10);
        testStack.push(3);
        testStack.push(5);
        testStack.push(6);
        testStack.push(7);
        assertEquals(false, testStack.isEmpty());
        assertEquals(4, testStack.size());
    }


    @Test
    @DisplayName("If one pushes x then pops, the value popped is x")
    public void ifPushesXThenPops() {
        testStack.push(3);
        assertEquals(3, testStack.pop());
    }

    @Test
    @DisplayName("If one pushes x then pops, the stack is empty")
    public void ifPushesXThenPeeks() {
        testStack = new TqsStack<>(10);
        testStack.push(3);
        assertEquals(3, testStack.peek());
        assertEquals(1, testStack.size());
    }

    @Test
    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    public void poppingFromAnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {
            testStack.pop();
        });
    }

    @Test
    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    public void peekingIntoAnEmptyStack() {
        assertThrows(NoSuchElementException.class, () -> {

            testStack.peek();
        });
    }

    @Test
    @DisplayName("For bounded stacks only: pushing onto a full stack does throw\n" +
            "an IllegalStateException")
    public void pushingOntoAFullStack() {
        testStack = new TqsStack<>(2);
        testStack.push(3);
        testStack.push(5);
        assertThrows(IllegalStateException.class, () -> {
            testStack.push(6);
        });
    }

}
