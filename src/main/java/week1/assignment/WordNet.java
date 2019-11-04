package week1.assignment;

import edu.princeton.cs.algorithms.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private Map<Integer, String> synsets;

    // constructor takes the name of the two input files
    // Complexity: O((S + H) * log (S + H)) time | O(S + H) space
    public WordNet(String synsets, String hypernyms) {
        this.synsets = new HashMap<>();
        loadSynsets(synsets);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return new ArrayList<String>(synsets.values());
    }

    // is the word a WordNet noun?
    // Complexity: O(log(N)) time
    public boolean isNoun(String word) {
        return synsets.containsValue(word);
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

    private void loadSynsets(String synsets){
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            int id = Integer.valueOf(line[0]);
            String noun = line[1];
            this.synsets.put(id, noun);
        }
    }
}
