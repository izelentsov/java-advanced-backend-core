package com.epam.jmp.mod2.practice.task3;


import java.util.HashMap;
import java.util.Map;



public class ThreadLocalUserProfileProcessor {

    private final ThreadLocal<Map<String, UserProfile>> cache =
            ThreadLocal.withInitial(HashMap::new);


    public UserProfile getUserProfile(String userId) {
        Map<String, UserProfile> c = cache.get();
        if (c.containsKey(userId)) {
            return c.get(userId);
        }
        UserProfile profile = fetchProfileFromDatabase(userId);
        c.put(userId, profile);
        return profile;
    }


    private UserProfile fetchProfileFromDatabase(String userId) {
        // Simulates fetching from the database
        return new UserProfile(userId, "Name_" + userId);
    }
}
