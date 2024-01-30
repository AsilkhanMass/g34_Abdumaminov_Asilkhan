package uz.pdp.service.imp;

import uz.pdp.domain.User;
import uz.pdp.service.EmailService;
import uz.pdp.service.UserService;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class UserServiceImp implements UserService {
    static{
        User admin = User.builder()
                .name("Asilkhan")
                .password("qwe")
                .email("papanodir633@gmail.com")
                .build();
        USERS.add(admin);
    }
    @Override
    public User login(String password, String email) {
        User user = USERS.stream()
                .filter(u -> u.getPassword().equals(password) && u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        if(user!=null){
            System.out.println("Successfully login");
        }else{
            System.out.println("No such user!");
        }
        return user;
    }

    @Override
    public void register(String name, String password, String email) {
        EmailService emailService = new EmailServiceImp();
        User user = USERS.stream()
                .filter(u -> u.getPassword().equals(password) && u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        if(user==null && emailService.sendEmail("asilkhan566766@gmail.com", email, "sdkr cuxu aqcf hpqn")){
                user = User.builder()
                        .name(name)
                        .password(password)
                        .email(email)
                        .build();
                USERS.add(user);
        }else{
            System.out.println("User with such password or email already exists!");
        }
    }

    @Override
    public void changePassword(User user, String newPassword) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle("settings");

        EmailService emailService = new EmailServiceImp();
        if(emailService.sendEmail("asilkhan566766@gmail.com", user.getEmail(), "sdkr cuxu aqcf hpqn")){
            user.setPassword(newPassword);
            user.setUpdatedAt(LocalDateTime.now());
            System.out.println("Password updated successfully!");
        }


    }
}
