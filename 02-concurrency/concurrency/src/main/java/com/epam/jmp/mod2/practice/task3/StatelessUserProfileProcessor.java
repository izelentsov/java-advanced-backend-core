package com.epam.jmp.mod2.practice.task3;


import java.util.Map;



public class StatelessUserProfileProcessor {


    public UserProfile getUserProfile(String userId, Map<String, UserProfile> cache) {
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
