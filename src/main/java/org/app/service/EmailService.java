package org.app.service;

import org.app.requests.LeadRequest;
import org.app.requests.NotificationRequest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements NotificationService{
    private JavaMailSender javaMailSender;
    EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    @Override
    public String notify(NotificationRequest notificationRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notificationRequest.getRecipient());
            message.setSubject(notificationRequest.getTitle());
            message.setText(notificationRequest.getMessage());

            javaMailSender.send(message);
            return "Email sent successfully";
        } catch (MailException e) {
            // Log the error
            System.err.println("Failed to send email to " + notificationRequest.getRecipient() + ": " + e.getMessage());
            // Optionally, you can use a logger instead of System.err
//             logger.error("Failed to send email", e);

            // Handle failure: return message or throw custom exception
            throw e;
        }
    }
    NotificationRequest getNotificationRequest(LeadRequest leadRequest) {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setTitle("test");
        notificationRequest.setRecipient("singhsurbhi.in@gmail.com");
        notificationRequest.setMessage("Hi surbhi, "+ leadRequest.getName()+" just filled your form and requested a callback. Please reach out on "+ leadRequest.getContact()+" Thank you!");
        return notificationRequest;
    }
}
