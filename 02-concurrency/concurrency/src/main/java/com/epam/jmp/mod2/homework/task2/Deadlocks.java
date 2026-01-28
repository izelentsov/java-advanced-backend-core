package com.epam.jmp.mod2.homework.task2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Deadlocks {


    private final Object lock = new Object();


    static void main() throws InterruptedException {
        new Deadlocks().run();
    }


    public void run() throws InterruptedException {
        List<Integer> numbers = new ArrayList<>();

        Thread producer = new Thread(() -> produce(numbers));
        Thread sum = new Thread(() -> calculateSum(numbers));
        Thread root = new Thread(() -> calculateRoot(numbers));

        System.out.println("Starting...");
        producer.start();
        sum.start();
        root.start();

        producer.join();
        sum.join();
        root.join();

        System.out.println("Finished...");
    }

    private void produce(List<Integer> numbers) {
        Random rand = new Random();
        while (true) {
            int i = rand.nextInt(10);
            synchronized (lock) {
                numbers.add(i);
            }
        }
    }

    private void calculateSum(List<Integer> numbers) {
        // get int stream out of numbers list, calculate the sum
        while (true) {
            int sum;
            int size;
            synchronized (lock) {
                sum = numbers.stream().mapToInt(Integer::intValue).sum();
                size = numbers.size();
            }
            System.out.println("Sum: " + sum + "; count: " + size);
        }
    }


    private void calculateRoot(List<Integer> numbers) {
        // get int stream out of numbers list, calculate the square root of sum of squares of all numbers
        while (true) {
            double root;
            int size;
            synchronized (lock) {
                root = Math.sqrt(numbers.stream().mapToInt(i -> i * i).sum());
                size = numbers.size();
            }
            System.out.println("Root: " + root + "; count: " + size);
        }
    }



}
