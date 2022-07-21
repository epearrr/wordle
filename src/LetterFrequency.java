import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LetterFrequency {
    private List<String> letters;
    private HashMap<String, Integer> frequencies;

    public LetterFrequency(List<String> letters) {
        this.letters = letters;
        this.frequencies = new HashMap<String, Integer>();
    }

    public HashMap getFrequencies() {
        for(int i = 0; i < 5; i++) {
            frequencies.put(letters.get(i), Collections.frequency(letters, letters.get(i)));
        }

        return frequencies;
    }
}
