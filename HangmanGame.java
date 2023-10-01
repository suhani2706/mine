import java.util.Scanner;
import java.util.Random;

public class HangmanGame {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "cherry", "grape", "kiwi", "orange", "pear", "strawberry"};
        Random random = new Random();
        String wordToGuess = words[random.nextInt(words.length)];
        int maxAttempts = 6;
        char[] guessedWord = new char[wordToGuess.length()];
        boolean[] letterGuessed = new boolean[26];
        int attempts = 0;
        boolean wordGuessed = false;

        System.out.println("Welcome to Hangman!");
        
        while (attempts < maxAttempts && !wordGuessed) {
            System.out.println("\nWord: " + displayWord(guessedWord));
            System.out.println("Attempts left: " + (maxAttempts - attempts));
            System.out.print("Guess a letter: ");
            char guess = getGuessFromUser(letterGuessed);
            letterGuessed[guess - 'a'] = true;
            
            if (wordToGuess.contains(String.valueOf(guess))) {
                updateGuessedWord(wordToGuess, guessedWord, guess);
            } else {
                attempts++;
                System.out.println("Incorrect guess.");
            }
            
            if (wordToGuess.equals(String.valueOf(guessedWord))) {
                wordGuessed = true;
                System.out.println("Congratulations! You guessed the word: " + wordToGuess);
            }
        }
        
        if (!wordGuessed) {
            System.out.println("\nSorry, you've run out of attempts. The word was: " + wordToGuess);
        }
    }

    private static String displayWord(char[] guessedWord) {
        StringBuilder result = new StringBuilder();
        for (char c : guessedWord) {
            if (c == 0) {
                result.append("_");
            } else {
                result.append(c);
            }
            result.append(" ");
        }
        return result.toString();
    }

    private static char getGuessFromUser(boolean[] letterGuessed) {
        Scanner scanner = new Scanner(System.in);
        char guess;
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                guess = input.charAt(0);
                if (!letterGuessed[guess - 'a']) {
                    break;
                } else {
                    System.out.print("You've already guessed that letter. Try again: ");
                }
            } else {
                System.out.print("Invalid input. Please enter a single letter: ");
            }
        }
        return guess;
    }

    private static void updateGuessedWord(String wordToGuess, char[] guessedWord, char guess) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedWord[i] = guess;
            }
        }
    }
}
