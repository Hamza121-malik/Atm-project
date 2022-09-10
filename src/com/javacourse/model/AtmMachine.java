package com.javacourse.model;

import com.javacourse.account.Account;
import com.javacourse.user.User;

import java.util.List;
import java.util.Scanner;

import static com.javacourse.controller.AtmController.processMenuOption;
import static com.javacourse.model.DataProcessor.initMainMenu;
import static com.javacourse.view.UserInterface.*;
import static utility.Utility.*;

public class AtmMachine {

    static Scanner input = new Scanner(System.in);
    static Scanner menuInput = new Scanner(System.in);
    
    public static void login() {

        // Retry to Enter credentials
        int retry = 0;

        String userName = "";
        String password = "";
        do {
            try {
                print("Enter Your User Name: ", false);
                userName = getUserInput();

                print("Enter Your Password: ", false);
                password = getUserInput();

            } catch (Exception e) {
                showWarning();
                retry++;
            }

            if (authenticate(userName, password)) {
                processAccount(userName);
            } else {
                showWarning();
                retry++;
            }
        } while (retry < 3);

        print("No of retries done, try again after sometime", true);

    }

    public static void signup() {
        print("Enter FullName of user:", false);
        final String fullName = getUserInput();

        print("Enter UserName:", false);
        final String userName = getUserInput();

        print("Enter Password:", false);
        final String password = getUserInput();

        User user = new User(fullName, userName, password);
        userData.put(userName, user);
        accountData.put(user.getAccount().getAccountNumber(), user);

        print("You will be redirected to Main menu, there you can login with user you just created", true);
        initMainMenu();
        processMenuOption();
    }

    public static double viewBalance(User user) {
        return user.getAccount().getBalance();
    }

    public static double withdrawMoney(User user, double amount) {
        Account acc = user.getAccount();
        double balance = acc.withdraw(amount);
        return balance;
    }

    public static double depositMoney(User user, double amount) {
        Account acc = user.getAccount();
        double balance = acc.deposit(amount);
        return balance;
    }

    public void transferMoney() {

    }

    public static void processAccount(String userName) {
        print("Account Options: ", true);
        print("", true);
        final List<String> accountMenuOptions = mapOptionToIndex(accountOptions);
        showMenuOptions(accountMenuOptions);
        showOptionPrompt();
        final String userInput = getUserInput();
        int selection = 0;
        if (isUserInputValid(userInput, 6)) {
            selection = Integer.parseInt(userInput);

            switch (selection) {
                case 1:
                    final double balance = viewBalance(userData.get(userName));
                    flushScreen();
                    print("Account Balance: " + moneyFormat.format(balance) + "\n",true);
                    processAccount(userName);
                    break;

                case 2:
                    // Process withdraw funds
                    flushScreen();
                    processWithdrawOrDeposit(userName, "Withdraw");
                    processAccount(userName);
                    break;

                case 3:
                    // Process deposit funds
                    flushScreen();
                    processWithdrawOrDeposit(userName, "Deposit");
                    processAccount(userName);
                    break;

                case 4:
                    flushScreen();
                    processTransfer(userName);
                    processAccount(userName);
                    break;

                case 5:
                    flushScreen();
                    updateSettings(userName);
                    break;

                case 6:
                    logout();
                    break;

                default:
                    print("\n" + "Invalid choice." + "\n", true);
                    processAccount(userName);
            }
        }
    }
    
     public static void processTransfer(String userName) {
         final User user = userData.get(userName);
         final double balance = user.getAccount().getBalance();

         print("Enter the Account Number where to transfer money:", false);
         final String accountNumber = getUserInput();
         if (isAccountNumberExists(accountNumber)) {
             print("Account Balance: " + moneyFormat.format(balance), true);
             print("Amount you want to transfer : ", false);
             double amount = input.nextDouble();
             if ((balance - amount) >= 0) {
                 final User userToTransferMoney = accountData.get(accountNumber);
                 double updatedBalanceSender = withdrawMoney(user, amount);
                 double updatedBalanceReciever = depositMoney(userToTransferMoney, amount);
             } else {
                 print("Balance cannot be negative for transfer." + "\n",true);
             }

         } else {
             print("Wrong Account Number specified." + "\n",true);
         }

     }
     
    public static void processWithdrawOrDeposit(String userName, String requestType) {
        final User user = userData.get(userName);
        final double balance = user.getAccount().getBalance();
        print("Account Balance: " + moneyFormat.format(balance), true);
        print("Amount you want to withdraw/deposit : ",false);
        double amount = input.nextDouble();

        if (requestType.equalsIgnoreCase("Withdraw")) {
            if ((balance - amount) >= 0) {
                double newBalance = withdrawMoney(user, amount);
                print("New Account balance: " + moneyFormat.format(newBalance) + "\n", true);
            } else {
                print("Balance cannot be negative." + "\n",true);
            }
        } else if(requestType.equalsIgnoreCase("Deposit")) {
            if ((balance + amount) >= 0) {
                double newBalance = depositMoney(userData.get(userName), amount);
                print("New Account balance: " + moneyFormat.format(newBalance) + "\n", true);
            } else {
                print("Balance cannot be negative." + "\n", true);
            }
        }

    }

    public static void updateSettings(String userName) {
        print("Account Setting: ", true);

        print("[1] - Update User Name",true);
        print("[2] - Update Password",true);
        print("[3] - Back to Main",true);

        print("Enter your Choice: ",false);

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1:
                print("Current User Name is:" + userName,true);
                print("Enter New User Name: ",false);
                String newUserName = getUserInput();
                User user = userData.get(userName);
                user.updateUserName(newUserName);

                userData.remove(userName);
                userData.put(newUserName, user);
                print(" UserName Updated Successfully! ",false);
                print(" You will be logout as you updated the user name ",true);
                // Logout as username is updated so session is closed
                // Login again with new credentials

                logout();
                break;

            case 2:
                print("You are updating the password for :" + userName,true);
                print("Enter New Password: ",false);
                String newPassword = getUserInput();
                User userObject = userData.get(userName);
                userObject.updatePassword(newPassword);
                print(" Password Updated Successfully! ", false);
                print(" You will be logout as you updated the password ",true);

                // Logout as password is updated so session is closed
                // Login again with new credentials
                logout();
                break;

            case 3:
                processAccount(userName);
                break;

            default:
                showError();
                flushScreen();
                updateSettings(userName);
                break;
        }
    }

    public static void logout() {
        print("Thank You for using this ATM, bye.",true);
        showWelcomeMessage();
        initMainMenu();
        processMenuOption();
    }

}