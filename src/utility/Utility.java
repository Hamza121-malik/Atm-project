package utility;

import com.javacourse.user.User;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Utility {

    // Map<AccountNumber, User>
    public static LinkedHashMap<String, User> accountData = new LinkedHashMap<>();

    // Map<UserName, User>
    public static HashMap<String, User> userData = new HashMap<String, User>();

    // Account Options
    public static ArrayList<String> accountOptions = new ArrayList<>();

    public static DecimalFormat moneyFormat = new DecimalFormat("'kr'###,##0.00");


    public static void populateData() {

        userData.put("user1", new User("user name1", "user1", "user1"));
        userData.put("user2", new User("user name2", "user2", "user2"));
        userData.put("user3", new User("user name3", "user3", "user3"));

        accountData.put("user1", new User("user name1", "user1", "user1"));
        accountData.put("user2", new User("user name2", "user2", "user2"));
        accountData.put("user3", new User("user name3", "user3", "user3"));

        accountOptions.add("View Balance");
        accountOptions.add("Withdraw Funds");
        accountOptions.add("Deposit Funds");
        accountOptions.add("Transfer Funds");
        accountOptions.add("Account Settings");
        accountOptions.add("Logout");
    }

    public static List<String> mapOptionToIndex(List<String> options) {
        List<String> optionList = IntStream.range(0, options.size())
                .mapToObj(index -> "[" + (index + 1) + "]" + " " + options.get(index))
                .collect(toList());
        return optionList;
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static boolean authenticate(String userName, String password ){
        for (Map.Entry<String, User> entry : userData.entrySet()) {
             if(entry.getKey().equalsIgnoreCase(userName) && entry.getValue().getPassword().equals(password)) {
                 return true;
             }
        }
        return false;
    }

    public static boolean isAccountNumberExists(String accountNumber){
        final boolean contains = accountData.entrySet().contains(accountNumber);
        for (Map.Entry<String, User> entry : accountData.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUserInputValid(String userInput, int limit) {
        if (isInteger(userInput)) {
            int userInputInt = Integer.parseInt(userInput);
            return (userInputInt >= 0 && userInputInt <= limit);
        }
        return false;
    }

    public static boolean isInteger(String input) {
        return isInteger(input,10);
    }

    public static boolean isInteger(String input, int radix) {
        if(input.isEmpty()) return false;
        for(int i = 0; i < input.length(); i++) {
            if(i == 0 && input.charAt(i) == '-') {
                if(input.length() == 1) return false;
                else continue;
            }
            if(Character.digit(input.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
