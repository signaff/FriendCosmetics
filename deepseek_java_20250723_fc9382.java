package com.example.friendcosmetics;

import java.util.HashSet;
import java.util.Set;

public class FriendManager {
    private static final Set<String> friends = new HashSet<>();
    
    public static void addFriend(String username) {
        friends.add(username.toLowerCase());
    }
    
    public static void removeFriend(String username) {
        friends.remove(username.toLowerCase());
    }
    
    public static boolean isFriend(String username) {
        return friends.contains(username.toLowerCase());
    }
    
    public static Set<String> getFriends() {
        return new HashSet<>(friends);
    }
}