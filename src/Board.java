import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private ArrayList<List<CellStatus>> statusLayout;
    private ArrayList<List<Character>> charsLayout;

    public Board() {
        statusLayout = new ArrayList<>();
        charsLayout = new ArrayList<>();

        CellStatus[] statuses = new CellStatus[5];
        Arrays.fill(statuses, CellStatus.UNGUESSED);
        List<CellStatus> statusList = Arrays.asList(statuses);

        Character[] characters = new Character[5];
        Arrays.fill(characters, "_");
        List<Character> charList = Arrays.asList(characters);

        for(int i = 0; i < 5; i++) {
            statusLayout.add(statusList);
            charsLayout.add(charList);
        }
    }

    // 
    public void makeGuess(String guess) {

    }

    @Override
    public String toString() {
        
        return String.format();
    }
}
