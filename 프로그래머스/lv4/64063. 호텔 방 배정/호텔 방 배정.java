import java.util.*;

class Solution {
    static HashMap<Long, Long> map = new HashMap<>();
    static HashSet<Long> set = new HashSet<>();

    public long[] solution(long k, long[] room_number) {
        long[] result = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            long cur = room_number[i];
            long next = getNext(cur);
            result[i] = next;
        }
        return result;
    }

    public long getNext(long cur) {
        if (!map.containsKey(cur)) {
            map.put(cur, cur + 1);
            set.add(cur);
            return cur;
        }
        long next = getNext(map.get(cur));
        map.put(cur, next);
        return next;
    }
}
