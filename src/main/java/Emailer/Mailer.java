package Emailer;

import Logger.MyLogger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Mailer
{
    private String toAdress, password;
    private static final String MESSAGE = "Thank you for your business, your order with Lakeview Lane Pro Shop is complete! \n" +
            "if you have any questions please feel free to give us a call at 315-593-8070 \n \n \n" +
            "NOTE: This is an automated message.";
    private static final String FROM = "lakeviewlanesproshop@gmail.com";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "465";
    private Properties config;
    private MyLogger logger = new MyLogger();

    public Mailer(String toAddress)
    {
        this.toAdress = toAddress;
        try
        {
            config = new Properties();
            FileReader fr = new FileReader("/Users/jondntryniski/490/src/main/resources/application.conf");
            config.load(fr);
            this.password = config.getProperty("email");

        }catch(IOException e)
        {
            logger.makeLog(e.getMessage());
        }
    }

    public void send(String ball)
    {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, password);
            }
        });

        session.setDebug(true);

        try{
            MimeMessage message = new MimeMessage(session);

            message.setFrom(FROM);

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAdress));

            message.setSubject("Work order for " + ball + " complete.");

            message.setText(MESSAGE);

            logger.makeLog("Sending message to " + toAdress);

            Transport.send(message);

            logger.makeLog("Succesfully sent email");
        }catch(MessagingException e)
        {
            logger.makeLog(e.getMessage());
        }
    }
}
