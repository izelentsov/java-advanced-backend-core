package com.epam.jmp.mod2.practice.task3;


import java.util.HashMap;
import java.util.Map;



public class UserProfileProcessor {

    private final Map<String, UserProfile> cache = new HashMap<>();


    public UserProfile getUserProfile(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        UserProfile profile = fetchProfileFromDatabase(userId);
        cache.put(userId, profile);
        return profile;
    }


    private UserProfile fetchProfileFromDatabase(String userId) {
        // Simulates fetching from the database
        return new UserProfile(userId, "Name_" + userId);
    }
}
