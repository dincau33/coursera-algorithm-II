package week1.assignment;

import java.io.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class WordNetTest {

    private static final String FILE_PATH_FOLDER = "./src/test/resources/week1/assignment/wordnet/";

    private WordNet createTestDataset(String synsets, String hypernyms) {
        synsets = FILE_PATH_FOLDER + synsets;
        hypernyms = FILE_PATH_FOLDER + hypernyms;
        return new WordNet(synsets, hypernyms);
    }

    @Test
    void createWordnetTiny() {
        WordNet wordNet = createTestDataset("synsetsTiny.txt", "hypernymsTiny.txt");
        assertThat(wordNet.isNoun("1750s")).isTrue();
        assertThat(wordNet.isNoun("abc")).isFalse();
        assertThat(wordNet.isNoun("18-karat_gold")).isTrue();
    }

    @Test
    void createWordnet() {
        WordNet wordNet = createTestDataset("synsets.txt", "hypernyms.txt");
        assertThat(wordNet.isNoun("1750s")).isTrue();
        assertThat(wordNet.isNoun("abc")).isFalse();
        assertThat(wordNet.isNoun("18-karat_gold")).isTrue();

    }

    @Test
    void nullOrEmptyAreNotANoun() {
        WordNet wordNet = new WordNet("", "");
        assertThat(wordNet.isNoun(null)).isFalse();
        assertThat(wordNet.isNoun("")).isFalse();
    }
}