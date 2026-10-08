import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnimalGuesser {
    public static void main(String[] args){
        ArrayCollection<String> animals = new ArrayCollection<>(566);

        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Scanner inputScanner = new Scanner(System.in);

        try {
            File file = new File("Animals.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String animal = scanner.nextLine().toUpperCase();
                animals.add(animal);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        boolean continueGuessing = true;
        while (continueGuessing) {
            ArrayCollection<String> guessedAnimals = new ArrayCollection<>();

            int letterIndex = (int) (Math.random() * letters.length);
            String letter = letters[letterIndex];

            while (true) {
                System.out.println("Guess an animal that starts with the letter: " + letter);
                System.out.print("==> ");
                String guess = inputScanner.nextLine().trim().toUpperCase();

                if (guess.startsWith(letter)) {
                    if (animals.contains(guess)) {
                        guessedAnimals.add(guess);
                        System.out.println("\nCorrect! " + guess + " is an animal that starts with " + letter + ".\n");
                    } else {
                        System.out.println("\nIncorrect. " + guess + " is not an animal that starts with " + letter + ".");
                        System.out.println("You guessed " + guessedAnimals.size() + " correct animals that start with " + letter + ".\n");

                        System.out.println("Do you want to play again? (yes/no)");
                        String response = inputScanner.nextLine().trim().toLowerCase();
                        if (response.equals("no") || response.equals("n")) {
                            continueGuessing = false;
                            System.out.println("\nThanks for playing!");
                            break;
                        } else if (response.equals("yes") || response.equals("y")) {
                            break;
                        } else {
                            System.out.println("\nInvalid response. Exiting the game.");
                            continueGuessing = false;
                            break;
                        }
                    }
                } else {
                    System.out.println("\nYou guessed " + guessedAnimals.size() + " correct animals that start with " + letter + ".\n");
                    System.out.println("Invalid response. Exiting the game.");
                    continueGuessing = false;
                    break;
                }
            }
        inputScanner.close();
        }
    }
}