import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordFrequencyGameTest {

    @Test
    public void should_get_the_1_when_input_the() {
        String sentence = "the";
        String expectResult = "the 1";
        validate_input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words() {
        String sentence = "the is";
        String expectResult = "the 1\nis 1";
        validate_input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_spaces() {
        String sentence = "the      is";
        String expectResult = "the 1\nis 1";
        validate_input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_words_with_special_enter() {
        String sentence = "the   \n   is";
        String expectResult = "the 1\nis 1";
        validate_input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_two_same_words_with_sorted() {
        String sentence = "the the is";
        String expectResult = "the 2\nis 1";
        validate_input_words_process_to_expected_word(sentence, expectResult);
    }

    @Test
    public void should_process_sorted_with_count_descending() {
        String sentence = "the is is";
        String expectResult = "is 2\nthe 1";
        validate_input_words_process_to_expected_word(sentence, expectResult);
    }

    private void validate_input_words_process_to_expected_word(String sentence, String expectResult) {
        WordFrequencyGame game = new WordFrequencyGame();
        //When
        String result = game.getResult(sentence);
        //Then
        assertEquals(result, expectResult);
    }
}
