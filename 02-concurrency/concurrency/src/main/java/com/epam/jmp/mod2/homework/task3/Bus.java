package com.epam.jmp.mod2.homework.task3;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;


public class Bus {

    private final Map<String, Queue<String>> messages = new HashMap<>();
    private final int maxMessages;
    private int currentMessages = 0;

    public Bus(int maxMessages) {
        this.maxMessages = maxMessages;
    }

    public void send(String topic, String message) throws InterruptedException {
        synchronized (messages) {
            while (currentMessages >= maxMessages) {
                System.out.println("Bus is full, producer is waiting...");
                messages.wait();
            }
            Queue<String> queue = getQueue(topic);
            queue.offer(message);
            currentMessages += 1;
            messages.notifyAll();
        }
    }

    public String receive(String topic) throws InterruptedException {
        synchronized (messages) {
            Queue<String> queue = getQueue(topic);
            while (queue.isEmpty()) {
                System.out.println("Topic " + topic + " is empty, consumer is waiting... " + queue);
                messages.wait();
                queue = getQueue(topic);
            }
            String msg = queue.remove();
            currentMessages -= 1;
            if (queue.isEmpty()) {
                messages.remove(topic);
            }
            messages.notifyAll();
            return msg;
        }
    }

    private Queue<String> getQueue(String topic) {
        return messages.computeIfAbsent(topic, _ -> {
            System.out.println("Creating a queue for topic " + topic);
            return new LinkedList<>();
        });
    }
}
