import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    private static final String SPACE_DELIMITER_PATTERN = "\\s+";
    private static final String NEWLINE_DELIMITER = "\n";
    private static final String SPACE_DELIMITER = " ";

    public String getResult(String sentence) {
        String[] sentenceSplitBySpace = splitBySpace(sentence);
        List<WordCount> wordCountList = calculateWordCount(Arrays.asList(sentenceSplitBySpace));
        return formatWordCountResult(wordCountList);
    }

    private String[] splitBySpace(String sentence) {
        return sentence.split(SPACE_DELIMITER_PATTERN);
    }

    private List<WordCount> calculateWordCount(List<String> wordList) {
        Map<String, Long> wordCountMap = wordList.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<WordCount> wordCountList = wordCountMap.entrySet().stream()
                .map(entry -> new WordCount(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());

        return wordCountList.stream()
                .sorted(Comparator.comparing(WordCount::getCount).reversed())
                .collect(Collectors.toList());
    }

    private String formatWordCountResult(List<WordCount> wordCountList) {
        StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
        for (WordCount w : wordCountList) {
            String s = w.getWord() + SPACE_DELIMITER + w.getCount();
            joiner.add(s);
        }
        return joiner.toString();
    }
}
