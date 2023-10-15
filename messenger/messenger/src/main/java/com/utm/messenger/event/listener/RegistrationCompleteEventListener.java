package com.utm.messenger.event.listener;

import com.utm.messenger.user.User;
import com.utm.messenger.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import com.utm.messenger.event.RegistrationCompleteEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the newly registered user
        User theUser = event.getUser();

        // 2. Create a verification token for the user
        String verificationToken = UUID.randomUUID().toString();

        // 3. Save the verification token for the user
        userService.saveUserVerificationToken(theUser, verificationToken);

        // 4. Build the verification URL to be sent to the user
        String url = event.getApplicationUrl() + ":/register/verifyEmail?token=" + verificationToken;

        // 5. Save the email.
        log.info("Click the link to verify your registration: {}", url);
    }
}
