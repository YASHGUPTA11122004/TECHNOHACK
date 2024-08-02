import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {

    // Define character sets
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for password criteria
        System.out.print("Enter the length of the password: ");
        int length = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Include lowercase letters? (yes/no): ");
        boolean includeLowercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include uppercase letters? (yes/no): ");
        boolean includeUppercase = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include digits? (yes/no): ");
        boolean includeDigits = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Include special characters? (yes/no): ");
        boolean includeSpecial = scanner.nextLine().equalsIgnoreCase("yes");

        // Generate password
        String password = generatePassword(length, includeLowercase, includeUppercase, includeDigits, includeSpecial);

        // Display the result
        System.out.println("Generated password: " + password);

        scanner.close();
    }

    private static String generatePassword(int length, boolean includeLowercase, boolean includeUppercase,
                                           boolean includeDigits, boolean includeSpecial) {
        StringBuilder charSet = new StringBuilder();
        if (includeLowercase) charSet.append(LOWERCASE);
        if (includeUppercase) charSet.append(UPPERCASE);
        if (includeDigits) charSet.append(DIGITS);
        if (includeSpecial) charSet.append(SPECIAL_CHARACTERS);

        if (charSet.length() == 0) {
            throw new IllegalArgumentException("At least one character set must be selected.");
        }

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charSet.length());
            password.append(charSet.charAt(index));
        }

        return password.toString();
    }
}
