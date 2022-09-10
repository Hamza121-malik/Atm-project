package com.javacourse.model;

import com.javacourse.view.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.javacourse.view.UserInterface.*;
import static utility.Utility.*;

public class DataProcessor {

    AtmMachine atmMachine;

    public DataProcessor(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    public void initialize() {
        populateData();
        showWelcomeMessage();
        initMainMenu();
    }

    public static void initMainMenu() {
        List<String> mainMenuList = new ArrayList<String>();

        mainMenuList.add("Login");
        mainMenuList.add("Signup");
        mainMenuList.add("Quit");

        final List<String> mainMenuOptions = mapOptionToIndex(mainMenuList);
        showMenuOptions(mainMenuOptions);
    }

}
