import java.util.*;

public class TopKArtists {

    static class Artist {
        String name;
        long listeners;

        Artist(String n, long l) {
            name = n;
            listeners = l;
        }
    }

    static List<Artist> topK(List<Artist> all, int k) {

        PriorityQueue<Artist> minHeap =
            new PriorityQueue<>((a, b) ->
                Long.compare(a.listeners, b.listeners));

        for (Artist artist : all) {

            if (minHeap.size() < k) {
                minHeap.offer(artist);
            }
            else if (artist.listeners > minHeap.peek().listeners) {
                minHeap.poll();
                minHeap.offer(artist);
            }
        }

        List<Artist> result = new ArrayList<>(minHeap);

        result.sort((a, b) ->
            Long.compare(b.listeners, a.listeners));

        return result;
    }

    public static void main(String[] args) {

        List<Artist> artists = Arrays.asList(
            new Artist("Artist1",45),
            new Artist("Artist2",12),
            new Artist("Artist3",78),
            new Artist("Artist4",23),
            new Artist("Artist5",56),
            new Artist("Artist6",89),
            new Artist("Artist7",34),
            new Artist("Artist8",67),
            new Artist("Artist9",18),
            new Artist("Artist10",91),
            new Artist("Artist11",50),
            new Artist("Artist12",39)
        );

        List<Artist> top5 = topK(artists, 5);

        System.out.println("Top 5 Artists:");

        for (Artist a : top5)
            System.out.println(a.name +
                " : " + a.listeners + "M");
    }
}
