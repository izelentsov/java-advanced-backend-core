package com.epam.jmp.mod2.practice.task1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;



public class PlainThreads {


    static void main() {
        String dir = "files";
        initFiles(dir);

        try (Stream<Path> paths = Files.list(Path.of(dir))) {
            List<Thread> threads = paths
                    .map(Path::toString)
                    .map(FileProcessor::new)
                    .map(Thread::new)
                    .toList();

            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void initFiles(String dir) {
        int nFiles = 10;
        int nLines = 10;

        try {
            Files.createDirectories(Path.of(dir));
            for (int i = 0; i < nFiles; i++) {
                Path filePath = Path.of(dir, "file" + i);
                if (!Files.exists(filePath)) {
                    try (var writer = Files.newBufferedWriter(filePath)) {
                        for (int j = 0; j < nLines; j++) {
                            writer.write(String.valueOf(j));
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
