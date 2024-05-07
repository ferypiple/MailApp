package group.avantus.mailApp.auth.exception;


import lombok.Data;

import java.util.Date;

@Data
public class AuthException {
    private int status;
    private String message;
    private Date timestamp;

    public AuthException(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}