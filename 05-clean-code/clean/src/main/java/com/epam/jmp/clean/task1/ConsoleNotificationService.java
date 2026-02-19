package com.epam.jmp.clean.task1;



public class ConsoleNotificationService implements NotificationService {
    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}

