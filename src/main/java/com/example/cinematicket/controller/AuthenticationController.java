package com.example.cinematicket.controller;

import com.example.cinematicket.dto.request.AuthenticationRequest;
import com.example.cinematicket.dto.request.TokenRequest;
import com.example.cinematicket.dto.response.ApiResponse;
import com.example.cinematicket.dto.response.AuthenticationResponse;
import com.example.cinematicket.dto.response.IntrospectResponse;
import com.example.cinematicket.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> token(@RequestBody AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authentication(request))
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> tokenIntrospect(@RequestBody TokenRequest request)
            throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }
}
