import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyManager {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String LANGUAGE_MOST_COMMON_CHAR = "e";
    private static final double COINCIDENCE_INDEX = 0.065;

    public static String findKey(String text) {
        int keySize = KeyManager.findKeySize(text);
        List<String> sequences = Utils.generateKeySequences(text, keySize);
        return sequences.stream()
                .map(Utils::getMostFrequentChar)
                .map(mostFrequent -> Character.toString(getCharDistance(mostFrequent, LANGUAGE_MOST_COMMON_CHAR) + 97))
                .collect(Collectors.joining());
    }

    public static int getCharDistance(String a, String b) {
        int distance = a.charAt(0) - b.charAt(0);
        if (distance < 0) distance += 26;
        return distance;
    }

    private static int findKeySize(String text) {
        List<Double> ics = generateIcs(text);
        double delta = 0.001;
        double diff = 1;
        int keySize = 0;
        while (diff > delta) {
            keySize = 0;
            for (Double ic : ics) {
                if (diff > delta) {
                    diff = COINCIDENCE_INDEX - ic;
                    keySize++;
                }
            }
            delta += delta;
        }
        return keySize;
    }

    private static List<Double> generateIcs(String text) {
        List<Double> ics = new ArrayList<>();
        for (int i = 1; i <= ALPHABET.length(); i++) {
            List<String> sequences = Utils.generateKeySequences(text, i);
            Double sum = sequences.stream()
                    .map(KeyManager::coincidenceIndex)
                    .reduce(0.0, Double::sum);
            ics.add(sum / sequences.size());
        }
        return ics;
    }

    private static double coincidenceIndex(String text) {
        long length = text.length();
        Map<String, Integer> characterFrequencies = Utils.charFrequencyMap(text);

        long sum = ALPHABET.chars()
                .mapToLong(character -> characterFrequencies.getOrDefault(Character.toString(character), 0).longValue())
                .reduce(0, (accumulator, frequency) -> accumulator += frequency * (frequency - 1));

        return sum / ((double) (length * (length - 1)));
    }

}
