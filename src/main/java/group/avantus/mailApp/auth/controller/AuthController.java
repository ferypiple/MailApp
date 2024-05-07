package group.avantus.mailApp.auth.controller;

import group.avantus.mailApp.auth.AuthService;
import group.avantus.mailApp.jwt.dto.JwtRequest;
import group.avantus.mailApp.user.dto.RegistrationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authServiceImpl;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authServiceImpl.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authServiceImpl.createNewUser(registrationUserDto);
    }
}
