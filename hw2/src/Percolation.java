import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF UF;
    private boolean[] grid;
    private int n;
    private int numberOfOpenSites = 0;
    private int virtualTop;
    private int virtualBottom;

    public Percolation(int N) {
        UF = new WeightedQuickUnionUF(N * N + 2); // 增加虚拟节点
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        // 初始化虚拟节点连接
        for (int i = 0; i < N; i++) {
            UF.union(virtualTop, i);       // 虚拟顶节点连接首行
            UF.union(virtualBottom, N*(N-1) + i); // 虚拟底节点连接末行
        }
        grid = new boolean[N * N];
        n = N;
    }

    private int xyToSite(int row, int col){
        return row * n + col;
    }

    public void open(int row, int col) {
        if (grid[xyToSite(row,col)]) {
            return; // 已打开的格子直接返回
        }
        grid[xyToSite(row,col)] = true;
        numberOfOpenSites ++;
        unionHelper(row,col);
    }
    private void unionHelper(int row ,int col) {
        int site = xyToSite(row,col);
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
        if ( grid[xyToSite(row,col)]) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        int site = xyToSite(row,col);
        if (isOpen(row,col)) {
            return UF.connected(site, virtualTop);
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean percolates() {
        return UF.connected(virtualBottom,virtualTop);
    }

}
