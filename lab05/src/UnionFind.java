import java.sql.Array;
import java.util.Set;

public class UnionFind {
    // TODO: Instance variables
    public int[] disjoint;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        disjoint = new int[N];
        for (int i = 0; i < disjoint.length; i++) {
            disjoint[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int root = find(v);
        return Math.abs(disjoint[root]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (v > disjoint.length - 1 || v < 0) {
            throw new IllegalArgumentException("Cannot find an out of range vertex!");
        }
        if (disjoint[v] < 0){
            return v;
        }
        return disjoint[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1) == find(v2) ){
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        int root = parent(v);
        while ( disjoint[root] > 0) {
            root = parent(root);
        }
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if ( v1 == v2)
            return;
        int s1 = sizeOf(v1);
        int s2 = sizeOf(v2);
        if (s1 > s2){
            disjoint[find(v2)] = find(v1);
            disjoint[find(v1)] = -s1 - s2;
        }
        else {
            disjoint[find(v1)] = find(v2);
            disjoint[find(v2)] = -s2 - s1;
        }
    }

}
