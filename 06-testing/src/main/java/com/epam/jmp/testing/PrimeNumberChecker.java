package com.epam.jmp.testing;


import java.util.LinkedHashMap;



public class PrimeNumberChecker {

    private static final LinkedHashMap<Integer, Boolean> cache = new LinkedHashMap<>(16, 0.75f, true);
    private static final int MAX_SIZE = 100;



    public static boolean isPrime(int i) {
        if (cache.containsKey(i)) {
            return cache.get(i);
        }

        boolean result = true;
        if (i <= 1) {
            result = false;

        } else {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    result = false;
                    break;
                }
            }
        }

        cache.put(i, result);
        return result;
    }


    private void cacheResult(int i, boolean result) {
        if (cache.size() > MAX_SIZE) {
            cache.remove(cache.firstEntry().getKey());
        }
        cache.put(i, result);
    }



    }
