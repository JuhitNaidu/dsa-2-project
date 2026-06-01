import java.util.*;

public class RelianceEnergyMST {

    static final int V = 8;
    static final int INF = Integer.MAX_VALUE;

    static char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'M'};

    static int minKey(int[] key, boolean[] mstSet) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    static void printMST(int[] parent, int[][] graph) {
        int totalCost = 0;

        System.out.println("\nEdge \tWeight");

        for (int i = 0; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println(
                    vertices[parent[i]] + " - " +
                    vertices[i] + "\t" +
                    graph[i][parent[i]]
                );
                totalCost += graph[i][parent[i]];
            }
        }

        System.out.println("\nTotal MST Cost = " + totalCost);
    }

    static void primMST(int[][] graph) {

        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, INF);

        key[7] = 0;
        parent[7] = -1;

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < V; v++) {

                if (graph[u][v] != 0 &&
                    !mstSet[v] &&
                    graph[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {

        int[][] graph = {
            {0, 2, 0, 1, 0, 0, 0, 4},
            {2, 0, 2, 0, 0, 0, 0, 3},
            {0, 2, 0, 0, 0, 0, 3, 5},
            {1, 0, 0, 0, 3, 0, 0, 0},
            {0, 0, 0, 3, 0, 2, 0, 4},
            {0, 0, 0, 0, 2, 0, 4, 0},
            {0, 0, 3, 0, 0, 4, 0, 6},
            {4, 3, 5, 0, 4, 0, 6, 0}
        };

        primMST(graph);
    }
}
