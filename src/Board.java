import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private String word;
    private String guess;
    private ArrayList<List<CellStatus>> statusLayout;
    private ArrayList<List<String>> lettersLayout;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * constructor that initializes the word, guess, statusLayout, and lettersLayout
     * @param word the word that the user is trying to guess
     */
    public Board(String word) {
        this.word = word;
        this.guess = "     ";

        statusLayout = new ArrayList<>();
        lettersLayout = new ArrayList<>();

        CellStatus[] statuses;
        String[] strings;

        // set every value in the statusLayout to CellStatus.UNGUESSED and every value in lettersLayout to "_"
        for(int i = 0; i < 5; i++) {
            statuses = new CellStatus[5];
            Arrays.fill(statuses, CellStatus.UNGUESSED);
            strings = new String[5];
            Arrays.fill(strings, "_");

            statusLayout.add(Arrays.asList(statuses));
            lettersLayout.add(Arrays.asList(strings));
        }
    }

    /**
     * takes a guess made by the user and adds the guess to the next available row in the lettersLayout
     * @param guess
     */
    public void makeGuess(String guess) {
        this.guess = guess;
        int layoutIndex = 0;
        String guessedLetter;
        String correctLetter;


        // find the next available row in lettersLayout
        while(statusLayout.get(layoutIndex).get(0) != CellStatus.UNGUESSED) {
            layoutIndex++;
        }

        // iterates through each letter in the guess and adds it to the lettersLayout
        for(int i = 0; i < 5; i++) {
            lettersLayout.get(layoutIndex).set(i, guess.substring(i, i+1));
        }

        // update the statusLayout depending on whether the user was correct, incorrect, or got the letter in the wrong position
        for(int i = 0; i < 5; i++) {
            guessedLetter = guess.substring(i, i+1); 
            correctLetter = word.substring(i, i+1); 
            
            HashMap<String, Integer> correctLetterFreq = getFrequencies(word);

            if(guessedLetter.equals(correctLetter)) {
                statusLayout.get(layoutIndex).set(i, CellStatus.CORRECT);
            } else if(word.contains(guessedLetter) /* && 
                    */) {
                statusLayout.get(layoutIndex).set(i, CellStatus.WRONG_CELL);
            } else {
                statusLayout.get(layoutIndex).set(i, CellStatus.INCORRECT);
            }
        }

    }

    /**
     * 
     * @param letters list of letters whose frequencies are desired
     * @return hashmap containing each letter in the letters list and how many times each letter occurs
     */
    public HashMap<String, Integer> getFrequencies(List<String> letters) {
        HashMap<String, Integer> frequencies = new HashMap<>();
        
        for(int i = 0; i < 5; i++) {
            frequencies.put(letters.get(i), Collections.frequency(letters, letters.get(i)));
        }

        return frequencies;
    }

    /**
     * overloaded getFrequencies method for when a String is given as an argument
     */
    public HashMap<String, Integer> getFrequencies(String word) {
        HashMap<String, Integer> frequencies = new HashMap<>();
        String[] lettersArr = new String[5];
        List<String> letters;

        for(int i = 0; i < 5; i++) {
            lettersArr[i] = word.substring(i, i+1);
        }

        letters = Arrays.asList(lettersArr);

        for(int i = 0; i < 5; i++) {
            frequencies.put(letters.get(i), Collections.frequency(letters, letters.get(i)));
        }

        return frequencies;
    }


    /**
     * @return lettersLayout
     */
    public ArrayList<List<String>> getLettersLayout() {
        return lettersLayout;
    }

    /**
     * @return statusLayout
     */
    public ArrayList<List<CellStatus>> getStatusLayout() {
        return statusLayout;
    }

    /**
     * @return a visual representation of the Wordle board
     */
    @Override
    public String toString() {
        String visualBoard = "";
        String ansi_start = "";
        String ansi_end = "";

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                visualBoard += ansi_start + lettersLayout.get(i).get(j) + ansi_end;
                visualBoard += " ";
            }
            visualBoard += "\n"; 
        }
        return String.format(visualBoard);
    }
}
