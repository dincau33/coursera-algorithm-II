package week1.assignment;

public class WordNet {

    // constructor takes the name of the two input files
    // Complexity: O((S + H) * log (S + H)) time | O(S + H) space
    public WordNet(String synsets, String hypernyms) {

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return null;
    }

    // is the word a WordNet noun?
    // Complexity: O(log(N)) time
    public boolean isNoun(String word) {
        return true;
    }

    // distance between nounA and nounB (defined below)
    // Complexity: O(S + H) time
    public int distance(String nounA, String nounB) {
        return -1;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    // Complexity: O(S + H) time
    public String sap(String nounA, String nounB) {
        return "";
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }

}
