package com.epam.jmp.mod2.homework.task3;


import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BusDemo {


    static void main() throws InterruptedException {
        final int producers = 3;
        final int topicsNum = 3;
        final List<String> topics = IntStream.range(0, topicsNum)
                .mapToObj(i -> "Topic-" + i)
                .toList();

        try (ExecutorService exProd = Executors.newFixedThreadPool(producers);
             ExecutorService exCons = Executors.newFixedThreadPool(topicsNum)) {

            final Bus bus = new Bus(100);

            IntStream.range(0, producers)
                    .forEach(i -> exProd.submit(() -> produce(i, bus, topics)));
            IntStream.range(0, topics.size())
                    .forEach(i -> exCons.submit(() -> consume(i, bus, topics.get(i))));

            Thread.sleep(10_000);

            exProd.shutdownNow();
            exCons.shutdownNow();

            exProd.awaitTermination(5, TimeUnit.SECONDS);
            exCons.awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    private static void produce(int i, Bus bus, List<String> topics) {
        int count = 0;
        Random r = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String topic = topics.get(r.nextInt(topics.size()));
                String message = "Message from producer " + i + " (" + (count++) + ")";
                bus.send(topic, message);
                System.out.println("Produced [" + topic +"]: " + message );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer " + i + " interrupted");
            }
        }
    }


    private static void consume(int i, Bus bus, String topic) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = bus.receive(topic);
                System.out.println("Consumer " + i + " received [" + topic + "]: " + message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer " + i + " interrupted");
            }
        }
    }


}
