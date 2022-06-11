package com.example.rdc_touristique.Email;

import com.sun.mail.smtp.SMTPMessage;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EngistrementEmail {

    public void envoyer(String adresse, String sujet, String message) throws MessagingException {

        String from = "mobembotshishi";
        String pass = "boraxnlygtbqbnkc";
        String[] to = {adresse};
        String host = "smtp.gmail.com";

        // création d'une session
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] toAddress = new InternetAddress[to.length];

        for (int i = 0; i < to.length; i++){
            toAddress[i] = new InternetAddress(to[i]);
        }

        for (InternetAddress address : toAddress) {
            msg.setRecipient(Message.RecipientType.TO, address);
        }


        msg.setSubject(sujet);
        msg.setContent(message, "text/html; charset=utf-8");
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
}
