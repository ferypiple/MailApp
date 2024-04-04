package group.avantis.email.controllers;

import group.avantis.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@RestController
@RequestMapping("/mailer/api/v0/")
public class MailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/mail/{id}/status")
    public ResponseEntity<String> getMessageStatus(@PathVariable("id") String messageId) {
        // TODO: 04.04.2024 Status Response
        return null;
    }


    @PostMapping("/mail")
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text,
            @RequestParam(value = "attachments",required = false) MultipartFile[] attachments) {
        try {
            emailService.sendEmailWithAttachment(from, to, subject, text, attachments);
            return ResponseEntity.ok("id " + 1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("timestamp: " + Instant.now().toEpochMilli() +"\n message: " + e.getMessage());
        }
    }


}
