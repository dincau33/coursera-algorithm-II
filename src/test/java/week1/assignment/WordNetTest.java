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
    void createTinyWordnet() {
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
    void failDistanceOrAncestorNotNoun() {
        WordNet wordNet = createTestDataset("tinySynsets.txt", "tinyHypernyms.txt");
        assertThrows(IllegalArgumentException.class, () -> wordNet.distance("abc", "1750s"));
        assertThrows(IllegalArgumentException.class, () -> wordNet.sap("abc", "1750s"));
    }

    @Test
    void distanceAndAncestorTinyWordnet() {
        WordNet wordNet = createTestDataset("tinySynsets.txt", "tinyHypernyms.txt");
        assertThat(wordNet.distance("1760s", "1820s")).isEqualTo(6);
        assertThat(wordNet.sap("1760s", "1820s")).isEqualTo("'hood");
        assertThat(wordNet.distance("1750s", "15_May_Organization")).isEqualTo(2);
        assertThat(wordNet.sap("1750s", "15_May_Organization")).isEqualTo("1530s");
    }

    @Test
    void distanceWordnet() {
        WordNet wordNet = createTestDataset("synsets.txt", "hypernyms.txt");
        assertThat(wordNet.distance("AIDS acquired_immune_deficiency_syndrome", "brucellosis undulant_fever Malta_fever Gibraltar_fever Rock_fever Mediterranean_fever")).isEqualTo(2);
        assertThat(wordNet.sap("AIDS acquired_immune_deficiency_syndrome", "brucellosis undulant_fever Malta_fever Gibraltar_fever Rock_fever Mediterranean_fever")).isEqualTo("infectious_disease");
    }

    @Test
    void nullOrEmptyAreNotANoun() {
        WordNet wordNet = new WordNet("", "");
        assertThat(wordNet.isNoun(null)).isFalse();
        assertThat(wordNet.isNoun("")).isFalse();
    }
}