package Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email_Col {

    public static void send(String Text, String mailEtudiant) {

        final String username = "pfe.esprit1920@gmail.com";
        final String password = "pfeesprit1920ca";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pfe.esprit1920@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailEtudiant));
            message.setSubject("Rappel");

            message.setText(Text);

            Transport.send(message);

            System.out.println("Mail sent succesfully!");

        } catch (MessagingException e) {
            System.out.println("nnn");
            throw new RuntimeException(e);
        }
    }
}
