package uz.pdp;

import uz.pdp.domain.User;
import uz.pdp.enums.MonthEnum;
import uz.pdp.service.ApiService;
import uz.pdp.service.EmailService;
import uz.pdp.service.UserService;
import uz.pdp.service.imp.ApiServiceImp;
import uz.pdp.service.imp.EmailServiceImp;
import uz.pdp.service.imp.UserServiceImp;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    static Scanner scStr = new Scanner(System.in);
    static Scanner scInt = new Scanner(System.in);
    static ApiService apiService = new ApiServiceImp();
    static UserService userService = new UserServiceImp();
    public static void main(String[] args) {
        while(true){
            System.out.println("""
                    1. Login
                    2. Register
                    0. Exit
                    """);
            int menu = scInt.nextInt();
            switch (menu){
                case 1 ->{
                    System.out.println("Your email: ");
                    String email = scStr.nextLine();
                    System.out.println("Your password: ");
                    String password = scStr.nextLine();
                    User user = userService.login(password, email);
                    if(user!=null){
                        userMenu(user);
                    }
                }
                case 2 ->{
                    System.out.println("Name: ");
                    String name = scStr.nextLine();
                    System.out.println("Your email: ");
                    String email = scStr.nextLine();
                    System.out.println("Your password: ");
                    String password = scStr.nextLine();
                    userService.register(name, password, email);
                }
                case 0 ->{
                    return;
                }
                default -> {
                    System.out.println("Choose proper number");
                }
            }
        }

    }
    public static void userMenu(User loggedUser){
        while(true){
            System.out.println("""
                    1. Change password
                    2. Get info by date
                    0. Exit
                    """);
            int menu = scInt.nextInt();
            switch (menu){
                case 1->{
                    System.out.println("Enter new password");
                    String newPassword = scStr.nextLine();
                    userService.changePassword(loggedUser, newPassword);
                }
                case 2->{
                    int month;
                    do {
                        System.out.println("Month: ");
                        month = scInt.nextInt();
                    } while (month > 0 && month < 13);
                    int day;
                    do {
                        System.out.println("Day: ");
                        day = scInt.nextInt();
                    }while(day > 0 && day <= MonthEnum.numOfDay(month));
                    String history = apiService.dayInfo(month, day);
                    System.out.println(history);
                }
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Choose proper number! ");
                }
            }
        }
    }
}