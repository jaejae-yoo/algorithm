//2357 세그먼트 트리

import java.util.Arrays;
import java.util.Scanner;

class SegmentTree {
    int[] array;
    int[] tree;

    public SegmentTree() {
        int[] arr = new int[100001];
        Arrays.fill(arr, (int)1e9);
        this.array = arr;

        int[] treeArr = new int[400001];
        Arrays.fill(treeArr, (int)1e9);
        this.tree = treeArr;
    }

    public int init(String minAndMax, int start, int end, int node) {
        if (start == end) {
            return tree[node] = array[start];
        }

        int mid = (start + end) / 2;
        if (minAndMax.equals("min")) {
            return tree[node] = Math.min(init(minAndMax, start, mid, node*2), init(minAndMax,mid+1, end, node*2 + 1));
        } else {
            return tree[node] = Math.max(init(minAndMax, start, mid, node*2), init(minAndMax,mid+1, end, node*2 + 1));
        }
    }

    public int query(String minAndMax, int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            if (minAndMax.equals("min")) {
                return (int)1e9;
            } else {
                return 0;
            }
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        if (minAndMax.equals("min")) {
            return Math.min(query(minAndMax, start, mid, node*2, left, right), query(minAndMax, mid+1, end, node*2+1, left, right));
        } else {
            return Math.max(query(minAndMax, start, mid, node*2, left, right), query(minAndMax, mid+1, end, node*2+1, left, right));
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        SegmentTree minTree = new SegmentTree();
        SegmentTree maxTree = new SegmentTree();

        for (int idx = 0; idx < n; idx++) {
            int num = sc.nextInt();
            minTree.array[idx+1] = num;
            maxTree.array[idx+1] = num;
        }

        minTree.init("min", 0, n, 1);
        maxTree.init("max", 0, n, 1);

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            System.out.print(minTree.query("min", 0, n, 1, start, end) + " ");
            System.out.print(maxTree.query("max", 0, n, 1, start, end));
            System.out.println();
        }
    }
}
