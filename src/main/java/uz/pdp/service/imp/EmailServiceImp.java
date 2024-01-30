package uz.pdp.service.imp;

import uz.pdp.service.EmailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class EmailServiceImp implements EmailService {
    public boolean sendEmail(String from, String to, String password) {
        Scanner scanner = new Scanner(System.in);
        String host = "smtp.gmail.com";
        String port = "587";
        Random random = new Random();

        String randomNumber = String.valueOf(random.nextInt(100000, 999999));

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress());

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Verification code");

            message.setText(randomNumber);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password );
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Email sent successfully to " + to);
            System.out.print("Enter code (it must be 6 numbers)");
            String code = scanner.nextLine();
            if (randomNumber.equals(code)) {
                return true;
            } else {
                System.out.print("Incorrect code, try again!");
                return false;
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
