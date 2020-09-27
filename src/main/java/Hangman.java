import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Hangman {
    private final String word;
    private final int maxMistake;
    private final AtomicInteger countMistake;
    private final Scanner scanner;

    public Hangman(String word, int maxMistake, Scanner scanner) {
        this.word = word;
        this.maxMistake = maxMistake;
        this.countMistake = new AtomicInteger(0);
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        List<String> dictionary = fillDictionary(new ArrayList<>());
        Hangman hangman = new Hangman(chooseWord(dictionary), 5, new Scanner(System.in));
        hangman.play();
    }

    public void play() {
        char[] hiddenWord = word.toCharArray();
        char[] response = new char[hiddenWord.length];
        Arrays.fill(response, '*');
        while (true) {
            print("Guess a letter:");
            char letter = readLetter(scanner);
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

    private static ArrayList<String> fillDictionary(ArrayList<String> dictionary) {
        dictionary.add("hello");
        dictionary.add("human");
        dictionary.add("mother");
        dictionary.add("father");
        return dictionary;
    }

    private static String chooseWord(List<String> dictionary) {
        return dictionary.get(new Random().nextInt(dictionary.size()));
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private static char readLetter(Scanner scanner) {
        return scanner.next().charAt(0);
    }
}
