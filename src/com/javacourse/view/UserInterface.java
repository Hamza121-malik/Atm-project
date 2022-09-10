package com.javacourse.view;

import java.util.List;

public class UserInterface {

    public static void print(String value, boolean newline) {
        if (newline) {
            System.out.println(value);
        } else {
            System.out.print(value);
        }
    }

    public static void showMenuOptions(List<String> options) {
        options.forEach(value -> print(value,true));
    }

    public static void showOptionPrompt() {
        print("Choose an option and press Enter:", false);
    }

    public static void showWelcomeMessage() {
       // print("\033[0;32m" + "Welcome to the ATM Machine!" + "\033[0m", true);
       //  print("", true);
        print( "Welcome to the ATM Project!", true);
        print("", true);
    }

    public static void showWarning() {
       // print("\u001B[33m" + "!!!! Wrong Option Selected !!!!" + "\u001B[0m", true);
        print("!!!! Wrong Credentials !!!!", true);
    }

    public static void showError() {
        // print("\u001B[31m" + "!!!! This Could not be processed !!!!" + "\u001B[0m", true);
        print("!!!! This Could not be processed !!!!", true);
    }

    public static void flushScreen() {
        print("\033[4H\033[0J", false);
        System.out.flush();
    }
}
