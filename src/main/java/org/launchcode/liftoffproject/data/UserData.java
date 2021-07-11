package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserData {

    private static final Map<Integer, User> users = new HashMap<>();

    private static Collection<User> getAll() {
        return users.values();
    }

    private static User getById(int id) {
        return users.get(id);
    }

    public static void add(User user) {
        users.put(user.getId(), user);
    }

    public static void remove(int id) {
        users.remove(id);
    }
}
