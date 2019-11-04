package week1.assignment;

import edu.princeton.cs.algorithms.BreadthFirstDirectedPaths;
import edu.princeton.cs.algorithms.Digraph;

public class SAP {

    private Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    // Complexity: O(E + V) time | O(E + V) space; E and V are respectively the number Edge and Vertices of the Digraph.
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException();
        digraph = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    // Complexity: O(E + V) time
    public int length(int v, int w) {
        if (!validIndex(v) || !validIndex(w)) throw new IllegalArgumentException();

        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(this.digraph, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(this.digraph, w);

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(i)) {
                int distance = bfdpV.distTo(i) + bfdpW.distTo(i);
                if (distance < minDistance) minDistance = distance;
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // Complexity: O(E + V) time
    public int ancestor(int v, int w) {
        if (!validIndex(v) || !validIndex(w)) throw new IllegalArgumentException();

        BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(this.digraph, v);
        BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(this.digraph, w);

        int minDistance = Integer.MAX_VALUE;
        int shortestCommonAncestor = -1;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(i)) {
                int distance = bfdpV.distTo(i) + bfdpW.distTo(i);
                if (distance < minDistance) {
                    minDistance = distance;
                    shortestCommonAncestor = i;
                }
            }
        }

        return shortestCommonAncestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    // Complexity: O(E + V) time
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        return -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    // Complexity: O(E + V) time
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        return -1;
    }

    private boolean validIndex(int i) {
        return (0 <= i && i < digraph.V());
    }
}
