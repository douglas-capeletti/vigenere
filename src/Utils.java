import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

    public static List<String> generateKeySequences(String text, Integer keySize) {
        List<String> sequences = new ArrayList<>();
        for (int key = 0; key < keySize; key++) {
            StringBuilder builder = new StringBuilder();
            for (int jump = 0; jump < (text.length() - key); jump += keySize) {
                builder.append(text.charAt(key + jump));
            }
            sequences.add(builder.toString());
        }
        return sequences;
    }

    public static Map<String, Integer> charFrequencyMap(String text) {
        return text.chars().mapToObj(Character::toString).collect(Collectors.toMap(Function.identity(), x -> 1, (prev, nex) -> prev + 1));
    }

    public static String getMostFrequentChar(String sequence) {
        return charFrequencyMap(sequence).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");
    }

    public static String getInputFilePath(String[] args) {
        if (args.length == 0) {
            System.err.println("Please inform a encrypted file path as param");
            System.err.println("app (filepath) [outputFilepath]");
            System.exit(-1);
        }
        return args[0];
    }

    public static Optional<String> getOutputFilePath(String[] args) {
        if (args.length > 1) {
            return Optional.ofNullable(args[1]).map(value -> value + "_" + System.currentTimeMillis());
        }
        return Optional.empty();
    }

}
