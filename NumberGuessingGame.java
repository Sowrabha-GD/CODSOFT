import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner  scanner = new Scanner(System.in);
        Random random = new Random();
        
        int totalScore = 0;
        int round = 1;
        boolean playAgain = true;
        double percentage = 0;
        
        System.out.println("Welcome to the Number Guessing Game!!!");
        while (playAgain) {
            System.out.println("\n--- Round " + round + " ---");
            int numberToGuess = random.nextInt(100) + 1; // Generates a number between 1 and 100
            int attemptsLeft = 10;
            int roundScore = 0;

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (1-100): ");
                int guess;

                // Validate user input
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    continue;
                }

                if (guess < 1 || guess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                    continue;
                }

                // Check guess
                if (guess == numberToGuess) {
                    roundScore = attemptsLeft * 10; // Higher score for fewer attempts
                    System.out.println(" Correct! You guessed the number " + numberToGuess + " in " + (10 - attemptsLeft + 1) + " attempts.");
                    break;
                }else if ((guess < numberToGuess) && ((guess+10) >= numberToGuess ) ){
                    System.out.println("Low! But almost there");
                }else if ((guess > numberToGuess) && ((guess-10) <= numberToGuess ) ){
                    System.out.println("High! But almost there");
                }
                 else if (guess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (attemptsLeft == 0) {
                System.out.println("😞 Out of attempts! The correct number was " + numberToGuess + ".");
            }

            totalScore += roundScore;
            percentage =totalScore/round;
            System.out.println("Round " + round + " score: " + roundScore);
            System.out.println("Total score: " + totalScore + "/" + round*100);
            System.out.println("Percentage: " + percentage);
            // Ask if the user wants to play again
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("\nThank you for playing!");
                System.out.println("Your final score after " + round + " round(s): " + totalScore);
            }

            round++;
        }

        scanner.close();
    }
}

