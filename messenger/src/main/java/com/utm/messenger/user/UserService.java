package com.utm.messenger.user;

import com.utm.messenger.exception.UserAlreadyExistsException;
import com.utm.messenger.registration.RegistrationRequest;
import com.utm.messenger.registration.token.VerificationToken;
import com.utm.messenger.registration.token.VerificationTokenRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Repository
@RequiredArgsConstructor
public class UserService implements  IUserService{

    private EntityManager current_user;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository tokenRepository;

    @Autowired
    public UserService(EntityManager current_user){
        this.current_user = current_user;
    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with email" + request.email() + " already exists");
        }
        var newUser = new User();
        newUser.setUsername(request.username());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));

        //TODO ROLE ADAPTATION
//        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void save(User user) {
        current_user.persist(user);
    }

    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verficationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verficationToken);

    }
}
