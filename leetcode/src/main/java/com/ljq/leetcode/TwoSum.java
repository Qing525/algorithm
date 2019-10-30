package com.ljq.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : LJQ
 * @date : 2019/10/30 17:45
 */
public class TwoSum {
    private static int[] twoSum(int[] no, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < no.length; i++) {
            int val = target - no[i];
            if (map.containsKey(val)) {
                res[0] = i;
                res[1] = map.get(val);
            } else {
                map.put(no[i],i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Arrays.stream(twoSum(new int[]{1, 2, 3, 6}, 7)).forEach(System.out::println);
    }

}
