package com.epam.jmp.mod2.homework.task3;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BusDemo {


    static void main() throws InterruptedException {
        final int producers = 3;
        final int consumers = 5;

        try (ExecutorService exProd = Executors.newFixedThreadPool(producers);
             ExecutorService exCons = Executors.newFixedThreadPool(consumers)) {

            final Bus bus = new Bus(10);

            IntStream.range(0, producers)
                    .forEach(i -> exProd.submit(() -> produce(i, bus)));
            IntStream.range(0, consumers)
                    .forEach(i -> exCons.submit(() -> consume(i, bus)));

            Thread.sleep(10_000);

            exProd.shutdownNow();
            exCons.shutdownNow();

            exProd.awaitTermination(5, TimeUnit.SECONDS);
            exCons.awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private static void produce(int i, Bus bus) {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = "Message from producer " + i + " (" + (count++) + ")";
                bus.send(message);
                System.out.println("Produced: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }


    private static void consume(int i, Bus bus) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = bus.receive();
                System.out.println("Consumer " + i + " received: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }


}
