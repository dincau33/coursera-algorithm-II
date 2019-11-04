package week1.assignment;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class Outcast {
    private WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        if (nouns == null) throw new IllegalArgumentException();
        int maxDistance = 0;
        String maxNoun = null;
        for (String n1:nouns) {
            int distance = 0;
            for (String n2:nouns) if(!n1.equals(n2)) distance += wordNet.distance(n1, n2);
            if (distance > maxDistance) {
                maxDistance = distance;
                maxNoun = n1;
            }
        }
        return maxNoun;
    }
}
