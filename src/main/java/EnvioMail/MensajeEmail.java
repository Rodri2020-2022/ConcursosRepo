package EnvioMail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MensajeEmail {
    private static String HOST = "sandbox.smtp.mailtrap.io";
    private static String PORT = "2525";
    private static String USERNAME = "576c18ec7c2f28";
    private static String PASSWD = "3c3e59fb69f69a";
    private static String TOKEN = "9b64660352bb1bd747958dcd09b1ade8";

    public void enviarEmail(String emailDestino, String asunto, String textoContenido){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWD);
                    }
                });

        try {
            // create a MimeMessage object
            Message message = new MimeMessage(session);
            // set From email field
            message.setFrom(new InternetAddress(USERNAME));
            // set To email field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestino));
            // set email subject field
            message.setSubject(asunto);
            // set the content of the email message
            message.setText(textoContenido);

            // send the email message
            Transport.send(message);

            System.out.println("Email Message Sent Successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
