package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月09日 14:40
 * 
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 
 * 提示：
 * 0 <= num < 231
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer46 {
    public static void main(String[] args) {
        System.out.println(new Solution().translateNum(11258));
    }

    //其实就是有条件的青蛙跳格子
    //https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/solution/mian-shi-ti-46-ba-shu-zi-fan-yi-cheng-zi-fu-chua-6/
    static class Solution {
        public int translateNum(int num) {
            String str = String.valueOf(num);
            int[] dp = new int[str.length() + 1];
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= str.length(); i++) {
                //截取2位字符串，如果大于10且小于25，表示是组合的字母，大于10是因为可能出现09，08等的情况
                String temp = str.substring(i - 2, i);
                if (temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
            return dp[str.length()];
        }
    }
}
