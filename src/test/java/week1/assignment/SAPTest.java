package week1.assignment;

import edu.princeton.cs.algorithms.Digraph;
import edu.princeton.cs.introcs.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class SAPTest {
    private static final String FILE_PATH_FOLDER = "./src/test/resources/week1/assignment/wordnet/";

    Digraph digraph;
    SAP sap;

    @BeforeEach
    void setup() {
        digraph = loadDigraph(FILE_PATH_FOLDER + "tinyDigraph.txt");
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
    void failToFindSAPOutOfRangeVertex() {
        assertThrows(IllegalArgumentException.class, () -> sap.length(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> sap.length(5, 44));
    }

    @Test
    void getLengthOfSAPTinyDigraph() {
        assertThat(sap.length(2, 5)).isEqualTo(2);
        assertThat(sap.length(4, 7)).isEqualTo(4);
        assertThat(sap.length(9, 2)).isEqualTo(4);
        assertThat(sap.length(4, 6)).isEqualTo(-1);
    }

    @Test
    void failToFindCommonAncestorOutOfRangeVertex() {
        assertThrows(IllegalArgumentException.class, () -> sap.ancestor(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> sap.ancestor(5, 44));
    }

    @Test
    void getCommonAncestorTinyDigraph() {
        assertThat(sap.ancestor(2, 5)).isEqualTo(0);
        assertThat(sap.ancestor(4, 7)).isEqualTo(1);
        assertThat(sap.ancestor(9, 2)).isEqualTo(0);
        assertThat(sap.ancestor(4, 6)).isEqualTo(-1);
    }

    @Test
    void failToFindSAPNullIterable() {
        List<Integer> v = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> sap.length(v, null));
        assertThrows(IllegalArgumentException.class, () -> sap.length(null, v));
    }

    @Test
    void failToFindSAPOOutOfRangeVertices() {
        List<Integer> v = new ArrayList<>();
        v.add(3);
        v.add(-1);
        List<Integer> w = new ArrayList<>();
        v.add(3);
        v.add(5);
        assertThrows(IllegalArgumentException.class, () -> sap.length(v, w));
    }

    @Test
    void getLengthOfSAPTinyDigraphMultipleSources() {
        List<Integer> v1 = new ArrayList<>();
        v1.add(4);
        v1.add(1);
        List<Integer> w1 = new ArrayList<>();
        w1.add(9);
        w1.add(7);
        List<Integer> w2= new ArrayList<>();
        w2.add(6);
        List<Integer> w3 = new ArrayList<>();
        assertThat(sap.length(v1, w1)).isEqualTo(2);
        assertThat(sap.length(v1, w2)).isEqualTo(-1);
        assertThat(sap.length(v1, w3)).isEqualTo(-1);
    }

    @Test
    void failToFindCommonAncestorNullIterable() {
        List<Integer> v = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> sap.length(v, null));
        assertThrows(IllegalArgumentException.class, () -> sap.length(null, v));
    }

    @Test
    void failToFindCommonAncestorOutOfRangeVertices() {
        List<Integer> v = new ArrayList<>();
        v.add(3);
        v.add(-1);
        List<Integer> w = new ArrayList<>();
        v.add(3);
        v.add(5);
        assertThrows(IllegalArgumentException.class, () -> sap.ancestor(v, w));
    }

    @Test
    void getCommonAncestorTinyDigraphMultipleSources() {
        List<Integer> v1 = new ArrayList<>();
        v1.add(4);
        v1.add(1);
        List<Integer> w1 = new ArrayList<>();
        w1.add(9);
        w1.add(7);
        List<Integer> w2= new ArrayList<>();
        w2.add(6);
        List<Integer> w3 = new ArrayList<>();
        assertThat(sap.ancestor(v1, w1)).isEqualTo(1);
        assertThat(sap.ancestor(v1, w2)).isEqualTo(-1);
        assertThat(sap.ancestor(v1, w3)).isEqualTo(-1);
    }

    private static Digraph loadDigraph(String filePath) {
        In in = new In(filePath);
        return new Digraph(in);
    }

}