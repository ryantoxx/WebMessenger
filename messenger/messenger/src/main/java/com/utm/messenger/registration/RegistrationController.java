package com.utm.messenger.registration;

import com.utm.messenger.event.RegistrationCompleteEvent;
import com.utm.messenger.exception.UserAlreadyExistsException;
import com.utm.messenger.registration.token.VerificationToken;
import com.utm.messenger.registration.token.VerificationTokenRepository;
import com.utm.messenger.user.User;

import com.utm.messenger.event.RegistrationCompleteEvent;
import com.utm.messenger.user.User;
import com.utm.messenger.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userService.registerUser(registrationRequest);
        // publish registration event
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Successful. Check your email to complete your registration";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()) {
            return "This account has already been verified, please log in";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("Valid")) {
            return "Email verified successfully, now you can log in to your account.";
        }
        return "Invalid verification token";

    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
