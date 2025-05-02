import java.util.*;

class SegmentTree {
    private int[] segTree;
    private int[] lazy;
    private int n;

    public SegmentTree(int[] arr) {
        this.n = arr.length;
        segTree = new int[4 * n];
        lazy = new int[4 * n];
        buildSegmentTree(arr, 0, 0, n - 1);
    }

    private void buildSegmentTree(int[] arr, int i, int l, int r) {
        if (l == r) {
            segTree[i] = arr[l];
            return;
        }
        int mid = l + (r - l) / 2;
        buildSegmentTree(arr, 2 * i + 1, l, mid);
        buildSegmentTree(arr, 2 * i + 2, mid + 1, r);
        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }

    private void pushLazy(int i, int l, int r) {
        if (lazy[i] != 0) {
            segTree[i] += (r - l + 1) * lazy[i];
            if (l != r) {
                lazy[2 * i + 1] += lazy[i];
                lazy[2 * i + 2] += lazy[i];
            }
            lazy[i] = 0;
        }
    }

    public void rangeUpdate(int st, int end, int value) {
        rangeUpdate(0, 0, n - 1, st, end, value);
    }

    private void rangeUpdate(int i, int l, int r, int st, int end, int value) {
        pushLazy(i, l, r);

        if (r < st || l > end)
            return;

        if (st <= l && r <= end) {
            segTree[i] += (r - l + 1) * value;
            if (l != r) {
                lazy[2 * i + 1] += value;
                lazy[2 * i + 2] += value;
            }
            return;
        }

        int mid = l + (r - l) / 2;
        rangeUpdate(2 * i + 1, l, mid, st, end, value);
        rangeUpdate(2 * i + 2, mid + 1, r, st, end, value);
        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }

    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    private void update(int i, int l, int r, int index, int value) {
    
        // Apply lazy updates at this node
        if (lazy[i] != 0) {
            segTree[i] += (r - l + 1) * lazy[i];
        
            if (l != r) {
                lazy[2 * i + 1] += lazy[i];
                lazy[2 * i + 2] += lazy[i];
            }
            lazy[i] = 0;
        }
    
        if (l == r) {
            segTree[i] = value;
            return;
        }
    
        int mid = l + (r - l) / 2;
    
        // Before moving down, push lazy to children
        if (lazy[2 * i + 1] != 0) {
            segTree[2 * i + 1] += (mid - l + 1) * lazy[2 * i + 1];
            if (l != mid) {
                lazy[2 * (2 * i + 1) + 1] += lazy[2 * i + 1];
                lazy[2 * (2 * i + 1) + 2] += lazy[2 * i + 1];
            }
            lazy[2 * i + 1] = 0;
        }
    
        if (lazy[2 * i + 2] != 0) {
            segTree[2 * i + 2] += (r - mid) * lazy[2 * i + 2];
            if (mid + 1 != r) {
                lazy[2 * (2 * i + 2) + 1] += lazy[2 * i + 2];
                lazy[2 * (2 * i + 2) + 2] += lazy[2 * i + 2];
            }
            lazy[2 * i + 2] = 0;
        }
    
        if (index <= mid) {
            update(2 * i + 1, l, mid, index, value);
        } else {
            update(2 * i + 2, mid + 1, r, index, value);
        }
    
        segTree[i] = segTree[2 * i + 1] + segTree[2 * i + 2];
    }

    public int query(int st, int end) {
        return query(0, 0, n - 1, st, end);
    }

    private int query(int i, int l, int r, int st, int end) {
        pushLazy(i, l, r);

        if (r < st || l > end)
            return 0;

        if (st <= l && r <= end)
            return segTree[i];

        int mid = l + (r - l) / 2;
        return query(2 * i + 1, l, mid, st, end) + query(2 * i + 2, mid + 1, r, st, end);
    }
}
