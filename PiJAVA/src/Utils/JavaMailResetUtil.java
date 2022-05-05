/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Said Eya
 */
public class JavaMailResetUtil {

    public static void senMail(String recepient ,int reset_code) throws Exception {

        System.out.println("message en cours de  preparation ");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String myAcountEmail = "eyatest240@gmail.com";
        final String Password = "eya123456";
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAcountEmail, Password);
            }
        });

        Message message = prepareMessage(session, myAcountEmail, recepient,reset_code);
        Transport.send(message);
        System.out.println("message envoyé avec succès");

    }

    private static Message prepareMessage(Session session, String myAcountEmail, String recepient,int reset_code) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAcountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Reset Password");
            String htmlCode ="<h1> Bonjour,</h1> </br><h2> Cher(e) Client,"+"Votre code de réinitialisation est : \n" + reset_code+".</h2> ";
            message.setContent(htmlCode,"text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailResetUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
