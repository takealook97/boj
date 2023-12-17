import java.io.*;
import java.util.*;

class Solution {

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = getMatch(h2, m2, s2) - getMatch(h1, m1, s1);
        if((h1 == 0 || h1 == 12) && m1 == 0 && s1 == 0) {
            answer++;
        }
        return answer;
    }

    static int getMatch(int h, int m, int s) {
        double hour = (double) ((h * 30 + m * 0.5 + s * 0.5 / 60) % 360);
        double min = (double) ((m * 6 + s * 0.1) % 360);
        double sec = (double) s * 6;
        
        int count = -1;
        if (sec >= min) {
            count++;
        }
        if (sec >= hour) {
            count++;
        }

        count += (h * 60 + m) * 2;
        count -= h;
        if (h >= 12) {
            count -= 2;
        }
        
        return count;
    }
}
