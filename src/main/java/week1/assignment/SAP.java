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
        return new ShortestCommonAncestor(v, w).distance;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // Complexity: O(E + V) time
    public int ancestor(int v, int w) {
        if (!validIndex(v) || !validIndex(w)) throw new IllegalArgumentException();
        return new ShortestCommonAncestor(v, w).ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    // Complexity: O(E + V) time
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        if (!validateIndices(v) || !validateIndices(w)) throw new IllegalArgumentException();

        int minDistance = Integer.MAX_VALUE;
        for (Integer vx : v) {
            for (Integer wx : w) {
                int distance = new ShortestCommonAncestor(vx, wx).distance;
                if (distance < minDistance) minDistance = distance;
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    // Complexity: O(E + V) time
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) throw new IllegalArgumentException();
        if (!validateIndices(v) || !validateIndices(w)) throw new IllegalArgumentException();

        int minDistance = Integer.MAX_VALUE;
        int shortestPath = -1;
        for (Integer vx : v) {
            for (Integer wx : w) {
                ShortestCommonAncestor sca = new ShortestCommonAncestor(vx, wx);
                int distance = sca.distance;
                if (distance < minDistance) {
                    minDistance = distance;
                    shortestPath = sca.ancestor;
                }
            }
        }

        return shortestPath;
    }

    private boolean validIndex(int i) {
        return (0 <= i && i < digraph.V());
    }

    private boolean validateIndices(Iterable<Integer> indices) {
        for (Integer i : indices) {
            if (!validIndex(i)) return false;
        }
        return true;
    }

    private class ShortestCommonAncestor {

        int distance = Integer.MAX_VALUE;
        int ancestor = -1;

        ShortestCommonAncestor(int v, int w) {
            BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(digraph, v);
            BreadthFirstDirectedPaths bfdpW = new BreadthFirstDirectedPaths(digraph, w);

            for (int i = 0; i < digraph.V(); i++) {
                if (bfdpV.hasPathTo(i) && bfdpW.hasPathTo(i)) {
                    int currentDistance = bfdpV.distTo(i) + bfdpW.distTo(i);
                    if (currentDistance < distance) {
                        distance = currentDistance;
                        ancestor = i;
                    }
                }
            }

            distance = distance == Integer.MAX_VALUE ? -1 : distance;
        }
    }
}
