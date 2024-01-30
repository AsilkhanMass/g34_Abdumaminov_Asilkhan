package uz.pdp.service;

import uz.pdp.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public interface UserService {
    List<User> USERS = new ArrayList<>();

    User login(String password, String email);
    void register(String name, String password, String email);
    void changePassword(User user, String newPassword);
}
