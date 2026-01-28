package com.epam.jmp.mod2.practice.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class ThreadLocalUserProfileProcessorTest {


    @Test
    public void usesCachesConcurrently() {

        ThreadLocalUserProfileProcessor p = new ThreadLocalUserProfileProcessor();
        int nThreads = 10;
        int nProfiles = 1000;
        int nIterations = 1000;

        try (ExecutorService ex = Executors.newFixedThreadPool(nThreads)) {
            for (int i = 0; i < nThreads; i++) {
                ex.submit(() -> {
                    Map<String, UserProfile> saved = new HashMap<>();
                    IntStream.range(0, nProfiles)
                            .mapToObj(id -> "user" + id)
                            .forEach(userId -> {
                                UserProfile profile = p.getUserProfile(userId);
                                saved.put(userId, profile);
                            });

                    for (int iter = 0; iter < nIterations; iter++) {
                        IntStream.range(0, nProfiles)
                                .mapToObj(id -> "user" + id)
                                .forEach(userId -> {
                                    UserProfile profile = p.getUserProfile(userId);
                                    assertSame(profile, saved.get(userId));
                                });
                    }
                });
            }

            ex.shutdown();
            ex.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}