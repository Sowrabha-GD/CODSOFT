import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static int score = 0;
    static boolean timeUp = false;
    static final int TIMER_DURATION = 10; // Timer in seconds

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Quiz data: Questions, options, and correct answers
        String[][] quizData = {
                {"What is the capital of France?", "1. Berlin", "2. Paris", "3. Rome", "4. Madrid", "2"},
                {"Which planet is known as the Red Planet?", "1. Earth", "2. Venus", "3. Mars", "4. Jupiter", "3"},
                {"What is the largest mammal?", "1. Elephant", "2. Blue Whale", "3. Giraffe", "4. Hippopotamus", "2"},
                {"Which element has the chemical symbol 'O'?", "1. Oxygen", "2. Gold", "3. Osmium", "4. Iron", "1"},
                {"Who wrote 'Hamlet'?", "1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. Jane Austen", "2"}
        };

        String[] results = new String[quizData.length];

        // Loop through each question
        for (int i = 0; i < quizData.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + quizData[i][0]);
            for (int j = 1; j <= 4; j++) {
                System.out.println(quizData[i][j]);
            }

            // Start the timer
            Timer timer = new Timer();
            timeUp = false;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up!");
                }
            }, TIMER_DURATION * 1000);

            // Allow user to input answer within time limit
            int userAnswer = 0;
            while (!timeUp) {
                try {
                    System.out.print("Enter your answer (1-4): ");
                    userAnswer = scanner.nextInt();
                    if (userAnswer >= 1 && userAnswer <= 4) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please select a valid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear invalid input
                }
            }
            timer.cancel();

            // Check the answer
            if (!timeUp && Integer.toString(userAnswer).equals(quizData[i][5])) {
                System.out.println("Correct!");
                score++;
                results[i] = "Correct";
            } else {
                System.out.println("Incorrect! The correct answer was " + quizData[i][5]);
                results[i] = "Incorrect";
            }
        }

        // Display final results
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Your final score: " + score + "/" + quizData.length);
        System.out.println("\nQuestion-wise Summary:");
        for (int i = 0; i < results.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + results[i]);
        }

        scanner.close();
    }
}
