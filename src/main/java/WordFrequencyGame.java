import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_DELIMITER_PATTERN = "\\s+";
    private static final String NEWLINE_DELIMITER = "\n";
    private static final String SPACE_DELIMITER = " ";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String sentence) {
        try {
            String[] sentenceSplitBySpace = splitBySpace(sentence);
            List<WordCount> wordCountList = calculateWordCount(sentenceSplitBySpace);
            return formatWordCountResult(wordCountList);
        } catch (Exception e) {
            return CALCULATE_ERROR_MESSAGE;
        }
    }

    private String[] splitBySpace(String sentence) {
        //split the input string with 1 to n pieces of spaces
        return sentence.split(SPACE_DELIMITER_PATTERN);
    }

    private List<WordCount> calculateWordCount(String[] sentenceSplitBySpace) {
        List<WordCount> wordCountList = new ArrayList<>();
        for (String word : sentenceSplitBySpace) {
            WordCount wordCount = new WordCount(word, 1);
            wordCountList.add(wordCount);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordCount>> map = getListMap(wordCountList);

        List<WordCount> list = new ArrayList<>();
        for (Map.Entry<String, List<WordCount>> entry : map.entrySet()) {
            WordCount wordCount = new WordCount(entry.getKey(), entry.getValue().size());
            list.add(wordCount);
        }
        wordCountList = list;

        wordCountList.sort((wordCount1, wordCount2) -> wordCount2.getCount() - wordCount1.getCount());
        return wordCountList;
    }

    private Map<String, List<WordCount>> getListMap(List<WordCount> wordCountList) {
        Map<String, List<WordCount>> map = new HashMap<>();
        for (WordCount wordCount : wordCountList) {
            if (!map.containsKey(wordCount.getWord())) {
                List<WordCount> arr = new ArrayList<>();
                arr.add(wordCount);
                map.put(wordCount.getWord(), arr);
            } else {
                map.get(wordCount.getWord()).add(wordCount);
            }
        }
        return map;
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
