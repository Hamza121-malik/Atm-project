package com.javacourse.controller;

import com.javacourse.model.AtmMachine;
import com.javacourse.model.DataProcessor;
import com.javacourse.user.User;
import com.javacourse.view.UserInterface;

import java.io.IOException;

import static com.javacourse.model.AtmMachine.login;
import static com.javacourse.model.AtmMachine.signup;
import static com.javacourse.view.UserInterface.print;
import static com.javacourse.view.UserInterface.showOptionPrompt;
import static utility.Utility.getUserInput;
import static utility.Utility.isUserInputValid;

public class AtmController {
    AtmMachine atmMachine;
    DataProcessor dataProcessor;
    UserInterface userInterface;

    public AtmController(AtmMachine atmMachine, DataProcessor dataProcessor, UserInterface userInterface) {
        this.atmMachine = atmMachine;
        this.dataProcessor = dataProcessor;
        this.userInterface = userInterface;
    }

    public void init() {
        this.dataProcessor.initialize();
        processMenuOption();
    }

    public static void processMenuOption() {
        showOptionPrompt();
        final String userInput = getUserInput();
        int mainMenuSelectionOption = 0;
        if (isUserInputValid(userInput, 3)) {
            mainMenuSelectionOption = Integer.parseInt(userInput);

            switch (mainMenuSelectionOption) {
                case 1:
                    print("Login Menu", true);
                    // Call from ATM Machine model
                   login();
                    break;
                case 2:
                    print("Signup Menu", true);
                    // Call from ATM Machine model
                    signup();
                case 3:
                    System.exit(0);
                default:
                    print("Wrong Choice Selected:",false);
                    processMenuOption();
            }
        }
    }
}
