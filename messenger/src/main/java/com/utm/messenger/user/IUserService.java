package com.utm.messenger.user;

import com.utm.messenger.registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    public void save(User user);

    void saveUserVerificationToken(User theUser, String token);
}