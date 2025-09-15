//Program name: Hangman.java
// 
// This program creates a hangman game!!
//***************************************************************************************************

//All the necessary imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class hangman {

    private static boolean printWordDisplay(String word, List<Character> guesses) {
        int correctCount = 0;
        //this method converts and prints the word as (_)
        for (int i = 0; i < word.length(); i++) {
            if (guesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);//returns if player guesses all letters 
    }

    private static boolean getPlayerGuess(Scanner k, String word, List<Character> guesses) {
        //method receives user input and if correct replaces (_) with letter input 
        System.out.println("Please enter a letter:");
        String LG = k.nextLine();
        guesses.add(LG.charAt(0));

        return word.contains(LG);
    }

    public static void printHM(Integer wrongCount) {
        //method prints the hangman, by increments depending on wrong count
        System.out.println(" -------");
        System.out.println(" |     |");

        if (wrongCount >= 1) {
            System.out.println(" O     |");
        }

        if (wrongCount >= 2){
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/    |");

            }else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |     |");
        }

        if (wrongCount >= 5){
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\    |");

            } else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    public static void main(String[] args) throws FileNotFoundException {
        //initializes scanner
        Scanner k = new Scanner(System.in);

        System.out.println("How many players: 1 or 2?");
        String players = k.nextLine();
        String word;

        if (players.equals("1")) {
            //if only 1 player will run game using a generated word from the word list
            File wordFile = new File("hangman_word_list.txt"); //hangman_word_list.txt has to match your file directory

            Scanner m = new Scanner(wordFile);


            List<String> words = new ArrayList<>();//stores the list of words

            while (m.hasNext()) {
                words.add(m.nextLine());

            }

            Random rand = new Random();//initializes random class

            //allows program to pick a random word
            word = words.get(rand.nextInt(words.size()));
        } else {

            System.out.println("Player 1, please enter your word:");
            word = k.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("It's your turn player 2! Good Luck!");
        }

        //System.out.println(word); <--Allows user to see the word. Used for testing

        List<Character> guesses = new ArrayList<>();
        //sets the wrong count value as 0
        int wrongCount = 0;

        while(true) {

            //calls printHM method
            printHM(wrongCount);

            //if wrongCount is >=6 the player will lose
            if (wrongCount >= 6) {
                System.out.println("OH NO! You lost!");
                System.out.println("The correct word was: " + word);
                break;
            }

            printWordDisplay(word, guesses);
            //if condition is not met, it will add 1 to wrongCount
            if (!getPlayerGuess(k, word, guesses)) {
                wrongCount++;
            }
            if(printWordDisplay(word, guesses)) {
                System.out.println("Correct!!!");
                break;
            }

            //allows user to guess full word as well
            System.out.println("Or enter your guess for the word:");
            if (k.nextLine().equals(word)) {
                System.out.println("Congratulations, You WON!!!");
                break;
            } else {
                System.out.println("Incorrect! Please try again!.");
            }
        }
    }
}