import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Hangman {
    private static final List<String> words = new ArrayList<String>();
    private static final int maxMistake = 5;
    private static final Scanner scanner = new Scanner(System.in);
    private static final AtomicInteger countMistake = new AtomicInteger(0);

    public static void main(String[] args) {
        fillDictionary();
        char[] hiddenWord = guessWord();
        char[] response = new char[hiddenWord.length];
        Arrays.fill(response, '*');
        while (true) {
            print("Guess a letter:");
            char letter = readLetter();
            boolean hit = false;
            for (int i = 0; i < hiddenWord.length; i++) {
                if (hiddenWord[i] == letter) {
                    response[i] = letter;
                    hit = true;
                }
            }
            if (hit) {
                print("Hit!");
            } else {
                print(String.format("Missed, mistake %d out of %d.", countMistake.incrementAndGet(), maxMistake));
            }
            print("The word: " + String.valueOf(response));
            if (Arrays.equals(hiddenWord, response)) {
                print("You won!");
                break;
            } else if (countMistake.get() == maxMistake) {
                print("You lost!");
                break;
            }
        }
    }

    private static void fillDictionary() {
        words.add("hello");
        words.add("human");
        words.add("mother");
        words.add("father");
    }

    private static char[] guessWord() {
        return words.get(new Random().nextInt(words.size())).toCharArray();
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private static char readLetter() {
        return scanner.next().charAt(0);
    }
}
