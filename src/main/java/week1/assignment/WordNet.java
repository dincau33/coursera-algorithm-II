package week1.assignment;

import edu.princeton.cs.algorithms.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordNet {

    private Map<Integer, String> nounById;
    private Map<String, Integer> idByNoun;
    private SAP graph;

    // constructor takes the name of the two input files
    // Complexity: O((S + H) * log (S + H)) time | O(S + H) space
    public WordNet(String synsets, String hypernyms) {
        nounById = new HashMap<>();
        idByNoun = new HashMap<>();
        loadNounById(synsets);
        loadHypernyms(hypernyms, nounById.size());
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return new ArrayList<String>(nounById.values());
    }

    // is the word a WordNet noun?
    // Complexity: O(log(N)) time
    public boolean isNoun(String word) {
        return idByNoun.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    // Complexity: O(S + H) time
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        int nounsIdA = idByNoun.get(nounA);
        int nounsIdB = idByNoun.get(nounB);
        return graph.length(nounsIdA, nounsIdB);
    }

    // a synset (second field of nounById.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    // Complexity: O(S + H) time
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        int nounsIdA = idByNoun.get(nounA);
        int nounsIdB = idByNoun.get(nounB);
        int ancestor = graph.ancestor(nounsIdA, nounsIdB);
        return nounById.get(ancestor);
    }

    private void loadNounById(String synsets) {
        In in = new In(synsets);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            int id = Integer.valueOf(line[0]);
            String noun = line[1];
            nounById.put(id, noun);
            idByNoun.put(noun, id);
        }
    }

    private void loadHypernyms(String hypernyms, int nounsCount) {
        Digraph digraph = new Digraph(nounsCount);

        In in = new In(hypernyms);
        while (in.hasNextLine()) {
            String[] line = in.readLine().split(",");
            int v = Integer.valueOf(line[0]);
            for (int i = 1; i < line.length; i++) {
                int w = Integer.valueOf(line[i]);
                digraph.addEdge(v, w);
            }
        }

        graph = new SAP(digraph);
    }


}
