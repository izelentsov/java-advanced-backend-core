package com.epam.jmp.mod2.homework.task4;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class BlockingObjectPoolDemo {


    static void main() throws InterruptedException {
        final int producers = 3;
        final int consumers = 5;

        try (ExecutorService exProd = Executors.newFixedThreadPool(producers);
             ExecutorService exCons = Executors.newFixedThreadPool(consumers)) {

            final BlockingObjectPool pool = new BlockingObjectPool(10);

            IntStream.range(0, producers)
                    .forEach(i -> exProd.submit(() -> produce(i, pool)));
            IntStream.range(0, consumers)
                    .forEach(i -> exCons.submit(() -> consume(i, pool)));

            Thread.sleep(10_000);

            exProd.shutdownNow();
            exCons.shutdownNow();

            exProd.awaitTermination(5, TimeUnit.SECONDS);
            exCons.awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private static void produce(int i, BlockingObjectPool pool) {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = "Message from producer " + i + " (" + (count++) + ")";
                pool.put(message);
                System.out.println("Produced: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }


    private static void consume(int i, BlockingObjectPool pool) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Object message = pool.get();
                System.out.println("Consumer " + i + " received: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
