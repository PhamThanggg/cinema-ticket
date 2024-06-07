package com.example.cinematicket.service;

import com.example.cinematicket.dto.request.AuthenticationRequest;
import com.example.cinematicket.dto.request.TokenRequest;
import com.example.cinematicket.dto.response.AuthenticationResponse;
import com.example.cinematicket.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse authentication(AuthenticationRequest request);

    IntrospectResponse introspect(TokenRequest request) throws ParseException, JOSEException;


}
