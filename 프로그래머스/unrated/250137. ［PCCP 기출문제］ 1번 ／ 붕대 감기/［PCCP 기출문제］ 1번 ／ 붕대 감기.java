import java.io.*;
import java.util.*;

class Solution {
    static int t, x, y, hp, maxHealth;
    static HashMap<Integer, Integer> attackMap;
    
    static final int DEATH = -1;
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        // 총 회복량 = t * x + y 
        t = bandage[0];
        x = bandage[1];
        y = bandage[2]; // (추가체력)
        hp = health;
        maxHealth = health;
        
        setAttackMap(attacks);
        
        int time = 0;
        int attackTime = 0;
        int gauge = 0;
        
        while(hp > 0) {
            if(attackTime == attacks.length) {
                break;
            }
            
            time++;
            
            // if attack
            if(attackMap.containsKey(time)) {
                hp -= attackMap.get(time);
                gauge = 0;
                attackTime++;
            } else {
                // health plus
                getHp();
                gauge++;
                
                if(gauge == t) {
                    hp += y;
                    gauge = 0;
                }
            }
        }
        
        if(hp <= 0) {
            return DEATH;
        }
        return hp;
    }
    
    static void setAttackMap(int[][] attacks) {
        attackMap = new HashMap<>();
        for(int i = 0; i < attacks.length; i++) {
            attackMap.put(attacks[i][0], attacks[i][1]);
        }
    }
    
    static void getHp() {
        if(hp + x < maxHealth) {
            hp += x;
            return;
        }
        hp = maxHealth;
    }
}