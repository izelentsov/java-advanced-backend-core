package com.epam.jmp.mod2.practice.task1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class ExServices {


    static void main() {
        String dir = "files";
        initFiles(dir);

        try (Stream<Path> paths = Files.list(Path.of(dir))) {
            List<FileProcessor> processors = paths
                    .map(Path::toString)
                    .map(FileProcessor::new)
                    .toList();

             try (ExecutorService ex = Executors.newFixedThreadPool(processors.size())) {
                 processors.forEach(ex::submit);

                 ex.shutdown();
                 ex.awaitTermination(10, TimeUnit.SECONDS);
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
