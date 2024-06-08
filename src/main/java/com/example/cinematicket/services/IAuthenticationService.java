package com.example.cinematicket.services;

import com.example.cinematicket.dtos.requests.AuthenticationRequest;
import com.example.cinematicket.dtos.requests.TokenRequest;
import com.example.cinematicket.dtos.responses.AuthenticationResponse;
import com.example.cinematicket.dtos.responses.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse authentication(AuthenticationRequest request);

    IntrospectResponse introspect(TokenRequest request) throws ParseException, JOSEException;


}
