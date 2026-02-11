package com.epam.jmp.mod2.homework.task4;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class BlockingObjectPoolDemo {


    static void main() throws InterruptedException {
        final int workers = 10;

        try (ExecutorService ex = Executors.newFixedThreadPool(workers)) {
            final BlockingObjectPool pool = new BlockingObjectPool(5);

            IntStream.range(0, workers)
                    .forEach(i -> ex.submit(() -> work(i, pool)));

            Thread.sleep(15_000L);

            System.out.println("Shutting down");
            ex.shutdownNow();
            ex.awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private static void work(int i, BlockingObjectPool pool) {
        Random r = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Worker " + i + " is requesting");
                Object obj = pool.get();

                System.out.println("Worker " + i + " is working");
                Thread.sleep(100L + r.nextLong(5000L));

                System.out.println("Worker " + i + " is returning");
                pool.put(obj);

                System.out.println("Worker " + i + " is done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Worker " + i + " is interrupted");
            }
        }
    }

}
