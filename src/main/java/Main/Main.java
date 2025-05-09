package org.mailtrap;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        // provide recipient's email ID
        String to = "your.recipient@email.com";
        // provide sender's email ID
        String from = "john.doe@your.domain";

        // provide Mailtrap's username
        final String username = "994a047d0fb931";
        final String password = "9c8aee72e7e4ebdd64b57bf2df163e1e";

        // provide Mailtrap's host address
        String host = "sandbox.smtp.mailtrap.io";

        // configure Mailtrap's SMTP details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // recommended, other options: 2525 or 25

        // create the Session object
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // create a MimeMessage object
            Message message = new MimeMessage(session);
            // set From email field
            message.setFrom(new InternetAddress(from));
            // set To email field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // set email subject field
            message.setSubject("This is a test email!");
            // set the content of the email message
            message.setText("Everything works correctly? Let's go!");

            // send the email message
            Transport.send(message);

            System.out.println("Email Message Sent Successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}