class SegTreeMax {
    static final long NEG_INF = Long.MIN_VALUE;
    long[] tree;      // tree[node] = max in node's segment
    int n;

    SegTreeMax(int n) {
        this.n = n;
        tree = new long[4 * n];
    }

    // Range Maximum Query
    long queryMax(int node, int lo, int hi, int l, int r) {

        // No overlap
        if (r < lo || hi < l)
            return NEG_INF;

        // Full overlap
        if (l <= lo && hi <= r)
            return tree[node];

        // Partial overlap
        int mid = (lo + hi) / 2;

        return Math.max(
                queryMax(2 * node, lo, mid, l, r),
                queryMax(2 * node + 1, mid + 1, hi, l, r)
        );
    }

    // Point Update
    void pointUpdate(int node, int lo, int hi, int idx, long newVal) {

        if (lo == hi) {
            tree[node] = newVal;
            return;
        }

        int mid = (lo + hi) / 2;

        if (idx <= mid)
            pointUpdate(2 * node, lo, mid, idx, newVal);
        else
            pointUpdate(2 * node + 1, mid + 1, hi, idx, newVal);

        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }
}
