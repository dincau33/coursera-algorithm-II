package week1.assignment;

import edu.princeton.cs.algorithms.Digraph;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class SAPTest {
    private static final String FILE_PATH_FOLDER = "./src/test/resources/week1/assignment/wordnet/";

    Digraph digraph;
    SAP sap;

    @BeforeEach
    void setup() {
        digraph = loadDigraph(FILE_PATH_FOLDER + "digraphTiny.txt");
        sap = new SAP(digraph);
    }

    @Test
    void failToInstantiateNullSAP() {
        assertThrows(IllegalArgumentException.class, () -> new SAP(null));
    }

    @Test
    void createSAP() {
        assertThat(digraph.V()).isEqualTo(10);
    }

    @Test
    void failToFindSAPForOutOfRangeVertex() {
        assertThrows(IllegalArgumentException.class, () -> sap.length(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> sap.length(5, 44));
    }

    @Test
    void getLengthOfSAPofDigraphTiny() {
        assertThat(sap.length(2, 5)).isEqualTo(2);
        assertThat(sap.length(4, 7)).isEqualTo(4);
        assertThat(sap.length(9, 2)).isEqualTo(4);
        assertThat(sap.length(4, 6)).isEqualTo(-1);
    }

    @Test
    void failToFindCommonAncestorForOutOfRangeVertex() {
        assertThrows(IllegalArgumentException.class, () -> sap.ancestor(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> sap.ancestor(5, 44));
    }

    @Test
    void getAncestorOfSAPofDigraphTiny() {
        assertThat(sap.ancestor(2, 5)).isEqualTo(0);
        assertThat(sap.ancestor(4, 7)).isEqualTo(1);
        assertThat(sap.ancestor(9, 2)).isEqualTo(0);
        assertThat(sap.ancestor(4, 6)).isEqualTo(-1);
    }

    @Test
    void failToFindSAPOfNullIterable() {
    }

    @Test
    void failToFindCommonAncestorOfNullIterable() {
    }

    private static Digraph loadDigraph(String filePath) {
        In in = new In(filePath);
        return new Digraph(in);
    }

}