package com.epam.jmp.mod2.homework.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/**
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool {


    private final BlockingQueue<Object> pool;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        pool = new ArrayBlockingQueue<>(size);
        IntStream.range(0, size)
                .mapToObj(_ -> new Object())
                .forEach(pool::add);
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() throws InterruptedException {
        return pool.take();
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void put(Object object) throws InterruptedException {
        pool.put(object);
    }
}

