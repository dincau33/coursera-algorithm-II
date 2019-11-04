package week1.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class WordNetTest {

    private static final String FILE_PATH_FOLDER = "./src/test/resources/week1/assignment/wordnet/";

    private WordNet createTestDataset(String synsetsFileName, String hypernymsFileName) {
        String synsetsFilePath = FILE_PATH_FOLDER + synsetsFileName;
        String hypernymsFilePath = FILE_PATH_FOLDER + hypernymsFileName;
        return new WordNet(synsetsFilePath, hypernymsFilePath);
    }

    @Test
    void createWordnetTiny() {
        WordNet wordNet = createTestDataset("tinySynsets.txt", "tinyHypernyms.txt");
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