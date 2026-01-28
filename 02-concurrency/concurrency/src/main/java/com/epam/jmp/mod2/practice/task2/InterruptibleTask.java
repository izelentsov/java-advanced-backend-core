package com.epam.jmp.mod2.practice.task2;



public class InterruptibleTask {


    static void main() {
        try {
            Thread counterThread = new Thread(new InterruptibleCounter());
            counterThread.start();
            Thread.sleep(100);
            counterThread.interrupt();
            counterThread.join();
            System.out.println("Counter thread interrupted: " + counterThread.isInterrupted());

            Thread sleeperThread = new Thread(new InterruptibleSleeper());
            sleeperThread.start();
            Thread.sleep(100);
            sleeperThread.interrupt();
            sleeperThread.join();
            System.out.println("Sleeper thread interrupted: " + sleeperThread.isInterrupted());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }



    private static class InterruptibleCounter implements Runnable {

        @Override
        public void run() {
            // count in a loop until big numbers
            // check for thread interruption an exit if so
            long count = 0;
            System.out.println("Counter thread started counting...");
            while (count < Long.MAX_VALUE) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Counter thread was interrupted, stopping...");
                    return;
                }
                count++;
            }
            System.out.println("Counting completed: " + count);
        }
    }

    private static class InterruptibleSleeper implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Sleeper thread going to sleep for 10 seconds...");
                Thread.sleep(10_000);
                System.out.println("Sleeper thread woke up naturally.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Sleeper thread was interrupted during sleep.");
            }
        }
    }
}
