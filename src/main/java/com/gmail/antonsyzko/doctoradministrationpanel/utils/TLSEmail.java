package com.gmail.antonsyzko.doctoradministrationpanel.utils;

/**
 * Created by Admin on 11.08.2016.
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class TLSEmail {

    public static void main(String[] args) {
        final String fromEmail = "antonsyzko@gmail.com";
        final String password = "uspehsomnoi";
        final String toEmail = "antonio.shizko@gmail.com";

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body \r\n Thanks for joinig Private Doctor Administration  Panel");

    }

}
