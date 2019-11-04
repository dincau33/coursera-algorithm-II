package week1.assignment;

import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class OutcastTest {
    private static final String FILE_PATH_FOLDER = "./src/test/resources/week1/assignment/wordnet/";

    private String[] createOutcastInput(String outcastFileName) {
        String outcastFilePath = FILE_PATH_FOLDER + outcastFileName;
        In in = new In(outcastFilePath);
        return in.readLine().split(" ");
    }

    private WordNet createWordnet(String synsetsFileName, String hypernymsFileName) {
        String synsetsFilePath = FILE_PATH_FOLDER + synsetsFileName;
        String hypernymsFilePath = FILE_PATH_FOLDER + hypernymsFileName;
        return new WordNet(synsetsFilePath, hypernymsFilePath);
    }

    @Test
    void testOutcast5() {
        String[] outcastInput = createOutcastInput("outcast5.txt");
        WordNet wordNet = createWordnet("synsets.txt", "hypernyms.txt");
        assertThat(new Outcast(wordNet).outcast(outcastInput)).isEqualTo("table");
    }

    @Test
    void testOutcast8() {
        String[] outcastInput = createOutcastInput("outcast8.txt");
        WordNet wordNet = createWordnet("synsets.txt", "hypernyms.txt");
        assertThat(new Outcast(wordNet).outcast(outcastInput)).isEqualTo("bed");
    }

    @Test
    void testOutcast11() {
        String[] outcastInput = createOutcastInput("outcast11.txt");
        WordNet wordNet = createWordnet("synsets.txt", "hypernyms.txt");
        assertThat(new Outcast(wordNet).outcast(outcastInput)).isEqualTo("potato");
    }

}