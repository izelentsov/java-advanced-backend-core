package com.epam.jmp.mod2.homework.task3;


import java.util.LinkedList;
import java.util.Queue;


public class Bus {

    private final Queue<String> messages = new LinkedList<>();
    private final int maxMessages;

    public Bus(int maxMessages) {
        this.maxMessages = maxMessages;
    }

    public void send(String message) throws InterruptedException {
        synchronized (messages) {
            while (messages.size() >= maxMessages) {
                System.out.println("Bus is full, producer is waiting...");
                messages.wait();
            }
            messages.offer(message);
            messages.notifyAll();
        }
    }

    public String receive() throws InterruptedException {
        synchronized (messages) {
            while (messages.isEmpty()) {
                System.out.println("Bus is empty, consumer is waiting...");
                messages.wait();
            }
            String msg = messages.remove();
            messages.notifyAll();
            return msg;
        }
    }
}
