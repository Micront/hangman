import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HangmanTest {
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    }

    @Test
    public void losingTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        Scanner scanner = new Scanner(in);
        Hangman hangman = new Hangman("hello", 1, scanner);
        hangman.play();
        assertEquals("Guess a letter:\n" +
                "Missed, mistake 1 out of 1.\n" +
                "The word: *****\n" +
                "You lost!\n", outContent.toString());
    }

    @Test
    public void victoryTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("a".getBytes());
        Scanner scanner = new Scanner(in);
        Hangman hangman = new Hangman("a", 1, scanner);
        hangman.play();
        assertEquals("Guess a letter:\n" +
                "Hit!\n" +
                "The word: a\n" +
                "You won!\n", outContent.toString());
    }
}
