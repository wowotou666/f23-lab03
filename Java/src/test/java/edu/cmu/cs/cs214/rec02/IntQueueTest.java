package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: Write more unit tests to test the implementation of ArrayIntQueue
 * for the {@link LinkedIntQueue} and
 * {@link ArrayIntQueue} classes, as described in the handout. The
 * {@link ArrayIntQueue} class contains a few bugs. Use the tests you wrote for
 * the {@link LinkedIntQueue} class to test the {@link ArrayIntQueue}
 *
 * @author Alex Lockwood, George Guo
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
   //     mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        mQueue.enqueue(1);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        Integer input = 1;
        mQueue.enqueue(input);
        assertEquals(input, mQueue.peek());
    }

    @Test
    public void testEnqueue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        testList.forEach(n -> mQueue.enqueue(n));
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i), mQueue.dequeue());
            assertEquals(testList.size() - i - 1, mQueue.size());
        }
    }

    @Test
    public void testContent() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testCapacityExpansion() {
        for (int i = 0; i < 15; i++) { // 15 > INITIAL_SIZE
            mQueue.enqueue(i);
        }
        assertEquals(15, mQueue.size());
        for (int i = 0; i < 15; i++) {
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
        }
    }

    @Test
    public void testClear() {
        testList.forEach(n -> mQueue.enqueue(n));
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
        assertNull(mQueue.peek());
    }

    @Test
    public void testContinuousEnqueueDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals(Integer.valueOf(1), mQueue.dequeue());
        mQueue.enqueue(3);
        assertEquals(Integer.valueOf(2), mQueue.dequeue());
        assertEquals(Integer.valueOf(3), mQueue.peek());
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testSizeAfterMultipleOperations() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.dequeue();
        mQueue.enqueue(3);
        mQueue.enqueue(4);
        mQueue.dequeue();
        assertEquals(2, mQueue.size());
    }

    @Test
    public void testWrapAroundAndExpansion() {
        for (int i = 0; i < 10; i++) { 
            mQueue.enqueue(i);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(Integer.valueOf(i), mQueue.dequeue());
        }
        for (int i = 10; i < 20; i++) { 
            mQueue.enqueue(i);
        }
        assertEquals(Integer.valueOf(5), mQueue.peek());
    }


}