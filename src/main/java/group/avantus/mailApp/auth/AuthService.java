package group.avantus.mailApp.auth;

import group.avantus.mailApp.jwt.dto.JwtRequest;
import group.avantus.mailApp.user.dto.RegistrationUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest);

    ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto);


}
