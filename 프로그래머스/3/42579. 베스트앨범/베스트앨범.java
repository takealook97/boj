import java.util.*;

class Solution {
    static int N;
    static HashMap<String, Integer> map = new HashMap<>();
    static PriorityQueue<Song> pq = new PriorityQueue<>(Collections.reverseOrder());
    static ArrayList<Song> list = new ArrayList<>();
    static int[] answer;
    
    
    static class Song implements Comparable<Song> {
        int idx, count, sum;
        String genre;
        
        public Song(int idx, int count, int sum, String genre) {
            this.idx = idx;
            this.count = count;
            this.sum = sum;
            this.genre = genre;
        }
        
        @Override
        public int compareTo(Song song) {
            if(this.sum == song.sum) {
                if(this.count == song.count) {
                    return song.idx - this.idx;
                }
                return this.count - song.count;
            }
            return this.sum - song.sum;
        }
        
        @Override
        public boolean equals(Object o) {
            Song song = (Song) o;
            return this.genre.equals(song.genre);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.genre);
        }
    }
        
    public int[] solution(String[] genres, int[] plays) {
        N = genres.length;
        for(int i = 0; i < N; i++) {
            if(!map.keySet().contains(genres[i])) {
                map.put(genres[i], plays[i]);
            } else {
                map.put(genres[i], map.get(genres[i]) + plays[i]);
            }
        }
        
        for(int i = 0; i < N; i++) {
            pq.add(new Song(i, plays[i], map.get(genres[i]), genres[i]));
        }
        
        while(!pq.isEmpty()) {
            Song song = pq.poll();
            if(Collections.frequency(list, song) < 2) {
                list.add(song);
            }
        }
        
        answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).idx;
        }
        
        return answer;
    }
}
