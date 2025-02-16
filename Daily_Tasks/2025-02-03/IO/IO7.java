package Example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO7 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your input: ");
        try {
            String input = reader.readLine();
            System.out.println("You entered: " + input);
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
    }
}