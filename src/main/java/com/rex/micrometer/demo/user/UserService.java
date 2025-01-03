package com.rex.micrometer.demo.user;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> userRepository = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.values());
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.get(id));
    }

    public User createUser(User user) {
        long id = counter.incrementAndGet();
        user.setId(id);
        userRepository.put(id, user);
        return user;
    }

    public Optional<User> updateUser(Long id, User user) {
        if (userRepository.containsKey(id)) {
            user.setId(id);
            userRepository.put(id, user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        return userRepository.remove(id) != null;
    }
}
