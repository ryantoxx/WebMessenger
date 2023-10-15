package com.utm.messenger.user;

import com.utm.messenger.registration.RegistrationRequest;
import com.utm.messenger.registration.token.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);

    void saveUserVerificationToken(User theUser, String token);

    String validateToken(String theToken);
}