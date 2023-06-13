import java.util.*;

class Solution {
    static ArrayList<Integer>[] arr;
    static boolean[] visit;
    static HashSet<Object> set = new HashSet<>();
    static int banSize = 0;

    public int solution(String[] user_id, String[] banned_id) {
        arr = new ArrayList[banned_id.length];
        visit = new boolean[user_id.length];
        for (int i = 0; i < banned_id.length; i++) {
            arr[i] = new ArrayList<>();
        }
        banSize = banned_id.length;

        for (int i = 0; i < banned_id.length; i++) {
            String banned = banned_id[i];
            for (int j = 0; j < user_id.length; j++) {
                String id = user_id[j];
                if (check(id, banned)) {
                    arr[i].add(j);
                }
            }
        }
        for (ArrayList<Integer> temp : arr) {
            System.out.println(temp);
        }
        dfs(0);

        System.out.println();

        for (Object temp : set) {
            System.out.println(temp);
        }


        return set.size();
    }

    static boolean check(String id, String banned) {
        if (id.length() == banned.length()) {
            for (int i = 0; i < id.length(); i++) {
                if (banned.charAt(i) != '*' && id.charAt(i) != banned.charAt(i)) return false;
            }
            return true;
        }
        return false;
    }

    static void dfs(int depth) {
        if (depth == arr.length) {
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < visit.length; i++) {
                if (visit[i]) {
                    list.add(i);
                }
            }
            Collections.sort(list);
            set.add(list);
            return;
        }

        for (int i : arr[depth]) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
}
