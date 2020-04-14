import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_DELIMITER_PATTERN = "\\s+";
    private static final String NEWLINE_DELIMITER = "\n";
    private static final String SPACE_DELIMITER = " ";
    private static final String CALCULATE_ERROR_MESSAGE = "Calculate Error";

    public String getResult(String inputStr) {
        if (inputStr.split(SPACE_DELIMITER_PATTERN).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split(SPACE_DELIMITER_PATTERN);

                List<Input> inputList = new ArrayList<>();
                for (String s : arr) {
                    Input input = new Input(s, 1);
                    inputList.add(input);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(NEWLINE_DELIMITER);
                for (Input w : inputList) {
                    String s = w.getValue() + SPACE_DELIMITER + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR_MESSAGE;
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
