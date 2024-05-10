package group.avantus.mailApp.user.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
}