import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF UF;
    private int[] Percolations;
    private int n;
    private int numberOfOpenSites = 0;

    public Percolation(int N) {
        UF = new WeightedQuickUnionUF(N * N);
        Percolations = new int[N * N];
        n = N;
        for (int i = 0; i < N * N;i ++) {
            Percolations[i] = 1;
        }
    }

    public void open(int row, int col) {
        int site = row * n + col;
        Percolations[site] = 0;
        numberOfOpenSites += 1;
        unionHelper(site);
    }
    private void unionHelper(int site) {
       int[] nearby = {site - n, site + n, site - 1, site + 1};
       for (int i : nearby){
           if ( i >= 0 && i < n * n) {
               if (isOpen(i / n , i % n)) {
                   UF.union(site,i);
               }
           }
       }
    }

    public boolean isOpen(int row, int col) {
        int site = row * n + col;
        if ( Percolations[site] == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        int site = row * n + col;
        if (isOpen(row, col)) {
            for (int i = 0; i < n; i++) {
                if (UF.connected(i, site)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        int bottomIndex = n - 1;
        for (int i = 0; i < n; i++) {
            if (isFull(n - 1,bottomIndex)){
                return true;
            }
            bottomIndex--;
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
