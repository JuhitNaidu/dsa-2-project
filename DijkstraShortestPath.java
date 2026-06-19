import java.util.*;

public class DijkstraShortestPath {

    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int[] dijkstra(List<List<Edge>> adj, int source) {

        int n = adj.size();
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        pq.offer(new int[]{0, source});

        while (!pq.isEmpty()) {

            int[] top = pq.poll();
            int d = top[0];
            int u = top[1];

            if (d > dist[u])
                continue;

            for (Edge e : adj.get(u)) {

                int v = e.to;
                int w = e.weight;

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        int V = 7;

        List<List<Edge>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        // Graph edges
        adj.get(0).add(new Edge(1, 8));   // IND -> KOR
        adj.get(0).add(new Edge(3, 14));  // IND -> JPN

        adj.get(1).add(new Edge(2, 8));   // KOR -> MGR
        adj.get(1).add(new Edge(4, 4));   // KOR -> HSR

        adj.get(4).add(new Edge(5, 4));   // HSR -> BTM
        adj.get(4).add(new Edge(6, 8));   // HSR -> SRJ

        adj.get(5).add(new Edge(6, 15));  // BTM -> SRJ
        adj.get(5).add(new Edge(3, 12));  // BTM -> EC

        int source = 1; // KOR

        int[] dist = dijkstra(adj, source);

        System.out.println("Shortest Distance from KOR:");

        for (int i = 0; i < dist.length; i++)
            System.out.println("Node " + i + " = " + dist[i]);
    }
}
