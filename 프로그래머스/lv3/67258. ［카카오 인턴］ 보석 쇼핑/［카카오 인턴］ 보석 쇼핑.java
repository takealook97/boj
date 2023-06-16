import java.util.*;

class Solution {
    static HashSet<String> set = new HashSet<>();
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<Integer, int[]> result = new HashMap<>();

    public static int[] solution(String[] gems) {
        makeMap(gems);
        int left = 0;
        int right = 0;
        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.get(gems[i]) + 1);
            right = i;
            if (checkAll()) break;
        }
        result.put(right - left, new int[]{left + 1, right + 1});

        while (true) {
            if (map.get(gems[left]) > 1) {
                map.put(gems[left], map.get(gems[left]) - 1);
                left++;
            } else {
                map.put(gems[left], map.get(gems[left]) - 1);
                left++;
                while (!checkAll()) {
                    right++;
                    if(right >= gems.length) break;
                    map.put(gems[right], map.get(gems[right]) + 1);
                }
            }
            if (!result.containsKey(right - left)) {
                result.put(right - left, new int[]{left + 1, right + 1});
            }
            if (right > gems.length - 1 || left == right) break;
        }

        int min = Integer.MAX_VALUE;
        for (int i : result.keySet()) {
            min = Math.min(min, i);
        }
        return result.get(min);
    }

    public static void makeMap(String[] gems) {
        set.addAll(Arrays.asList(gems));
        for (String key : set) {
            map.put(key, 0);
        }
    }

    public static boolean checkAll() {
        return !map.containsValue(0);
    }
}
