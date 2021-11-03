package com.ashuo.leetcode.sword_offer;

/**
 * @author AShuo
 * @date 2021年10月26日 11:58
 * 剑指 Offer 67. 把字符串转换成整数
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * 
 * 
 * 
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 
 * 说明：
 * 
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * 
 * 示例 1:
 * 
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * 
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * 
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * 
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * 
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Offer67 {
    public static void main(String[] args) {
        System.out.println(new Solution().strToInt("4193 with words"));
    }

    static class Solution {
        //删除首部空格，再获取符号位，结果需要加上符号位，当符号位之后接下来的字符不是数字，那么该及之后字符串不符合题意，直接返回返回改字符之前的数字
        //数字还原可以使用：当前计算的数字=上一次计算的数字*10+当前字符
        //在数字进行计算前，需要计算数字是否已经越界了[-2^32,2^31-1]即[-2147483648,2147483647]
        //最后再将计算的数字加上符号位返回
        //https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/solution/mian-shi-ti-67-ba-zi-fu-chuan-zhuan-huan-cheng-z-4/
        public int strToInt(String str) {
            char[] chars = str.trim().toCharArray();
            if (chars.length == 0) {
                return 0;
            }
            int binary = Integer.MAX_VALUE / 10;
            int index = 1;
            int sign = 1;
            int res = 0;
            if (chars[0] == '-') {
                sign = -1;
            } else if (chars[0] != '+') {
                index = 0;
            }
            for (int i = index; i < chars.length; i++) {
                if ('0' <= chars[i] && chars[i] <= '9') {
                    if (res > binary || res == binary && chars[i] > '7') {
                        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }
                    res = res * 10 + (chars[i] - '0');
                } else {
                    break;
                }
            }
            return sign * res;
        }
    }
}