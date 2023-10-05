package com.utm.messenger.registration;

import com.utm.messenger.event.RegistrationCompleteEvent;
import com.utm.messenger.user.User;
import com.utm.messenger.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @PostMapping
    public String registerUser(RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userService.registerUser(registrationRequest);
        // publish registration event
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Successful. Check your email to complete your registration";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
