package com.utm.messenger.registration;

import org.hibernate.annotations.NaturalId;

public record RegistrationRequest(
        String username,
        String email,
        String password,
        String first_name,
        String last_name) {


}
