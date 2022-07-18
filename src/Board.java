import java.util.Arrays;
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

    public Board(String word) {
        this.word = word;
        this.guess = "     ";

        statusLayout = new ArrayList<>();
        lettersLayout = new ArrayList<>();

        CellStatus[] statuses = new CellStatus[5];
        Arrays.fill(statuses, CellStatus.UNGUESSED);
        List<CellStatus> statusList = Arrays.asList(statuses);

        String[] Strings = new String[5];
        Arrays.fill(Strings, "_");
        List<String> letterList = Arrays.asList(Strings);

        for(int i = 0; i < 5; i++) {
            statusLayout.add(statusList);
            lettersLayout.add(letterList);
        }
    }

    /**
     * takes a guess made by the user and adds the guess to the next available row in the lettersLayout
     * @param guess
     */
    public void makeGuess(String guess) {
        this.guess = guess;
        int layoutIndex = 0;

        // find the next available row in lettersLayout
        while(statusLayout.get(layoutIndex).get(0) != CellStatus.UNGUESSED) {
            layoutIndex++;
        }

        // iterates through each character in the guess and adds it to the lettersLayout
        for(int i = 0; i < 5; i++) {
            lettersLayout.get(layoutIndex).set(i, guess.substring(i, i+1));
            System.out.println("lettersLayout is " + lettersLayout);
        }
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
        return String.format(visualBoard + "\n" + word);
    }
}
