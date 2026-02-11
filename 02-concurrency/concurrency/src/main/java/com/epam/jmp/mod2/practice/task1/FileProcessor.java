package com.epam.jmp.mod2.practice.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;



record FileProcessor(String filePath) implements Runnable {

    @Override
    public void run() {
        try {
            try (Stream<String> lines = Files.lines(Path.of(filePath))) {
                lines.map(String::toUpperCase)
                        .forEach(l -> System.out.println(
                                "[" + Thread.currentThread().getName() + "] " + filePath + ": " + l));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
