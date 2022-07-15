import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Word {
    private Scanner fileScan;
    private String[] wordsArray;
    private String word;

    /**
     * Constructor that picks a random word from a file
     */
    public Word(){
        try{
            fileScan = new Scanner(new File("src/words.txt"));
        }
        catch(FileNotFoundException ex){
            System.out.println("'words' file not found - Terminating program.");
            System.exit(1);
        }
        
        // wordsArray is an array of every word from the words.txt file
        wordsArray = fileScan.nextLine().split(" ");
        // word is a randomly selected word from the array
        word = wordsArray[new Random().nextInt(wordsArray.length)];
    }

    public String getWord() {
        return word;
    }

    /**
     * toString method returns the randomly selected word
     */
    @Override
    public String toString() {
        return word;
    }
}
