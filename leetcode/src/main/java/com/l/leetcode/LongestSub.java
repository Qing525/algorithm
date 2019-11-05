package com.l.leetcode;

/**
 * @author : sin97.cn
 * @date : 2019/10/30 18:30
 */
public class LongestSub {

    private  static int lengthOfLongestSubString(String s) {

        int left = 0, right = 0, max = 0;
        int n = s.length();
        boolean[] ever = new boolean[128];

        while (right < n) {
            if (!ever[s.charAt(right)]) {
                ever[s.charAt(right)] = true;
                right++;
            } else {
                max = Math.max(max, right - left);
                while (left < right && s.charAt(right) != s.charAt(left)) {
                    ever[s.charAt(left)] = false;
                    left++;
                }
                left++;
                right++;
            }
        }
        return Math.max(max, right - left);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubString("abcdb"));
    }
}
