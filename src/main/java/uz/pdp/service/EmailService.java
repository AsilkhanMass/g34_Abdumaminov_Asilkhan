package uz.pdp.service;

public interface EmailService {
    boolean sendEmail(String from, String to, String password);
}
