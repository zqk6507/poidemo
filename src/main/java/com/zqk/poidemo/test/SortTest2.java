package com.zqk.poidemo.test;

import java.util.Arrays;

/**
 * @author zhengqiku
 * @date 2021/9/7
 */
public class SortTest2 {

    public static int compare(String s1, String s2) {
        char[] arr1 = s1.toCharArray(), arr2 = s2.toCharArray();
        int index = 0, len1 = arr1.length, len2 = arr2.length;
        int len = len1 < len2 ? len1 : len2;
        while (index < len) {
            char c1 = arr1[index], c2 = arr2[index];
            char c1_ = (char) (c1 >= 'a' ? c1 - ('a' - 'A') : c1);
            char c2_ = (char) (c2 >= 'a' ? c2 - ('a' - 'A') : c2);
            if (c1_ == c2_) {
                if (c1 != c2)
                    return c1 - c2;
            } else
                return c1_ - c2_;
            index++;
        }
        if (len1 == len2)
            return 0;
        else if (len1 > len2)
            return arr1[len];
        else
            return -arr2[len];
    }

    public static int compare2(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int len1 = c1.length, len2  = c2.length;

        int len = len1 > len2 ? len2: len1;
        if(len1 == len2){
            return s1.compareTo(s2);
        }else {
            int index = 0;
            while(index < len){
                char a = s1.charAt(index);
                char b = s2.charAt(index);
                if(a == b){
                    index++;
                }else{

                }
            }
        }
        return 0;
    }



    public static void sort(String[] src) {
        String temp;
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = 0; j < src.length - i - 1; j++) {
                if (compare(src[j], src[j + 1]) > 0) {
                    temp = src[j];
                    src[j] = src[j + 1];
                    src[j + 1] = temp;
                }
            }
        }
    }

    public static void sort2(String[] src) {
        int[][] arr = new int[src.length][2];
        int count = 0, index = 0;
        arr[0][0] = 0;
        arr[0][1] = src.length - 1;
        while (index <= count) {
            int left = arr[index][0];
            int right = arr[index][1];
            int[] dir = { 0, 1 };
            int i = left, j = right;
            String temp;
            while (i < j) {
                if (compare(src[i], src[j]) > 0) {
                    temp = src[i];
                    src[i] = src[j];
                    src[j] = temp;
                    dir[0] = 1 - dir[0];
                    dir[1] = 1 - dir[1];
                }
                i += dir[0];
                j -= dir[1];
            }
            if (j - left > 1) {
                count++;
                arr[count][0] = left;
                arr[count][1] = j - 1;
            }
            if (right - i > 1) {
                count++;
                arr[count][0] = i + 1;
                arr[count][1] = right;
            }
            index++;
        }
    }

    public static void main(String[] args) {
        String[] s = { "a", "c","b","Bc", "Ad", "aC", "Hello", "Xman", "little", "During",
                "day" };
        System.out.println("Before sort: " + Arrays.toString(s));
//        sort(s);

        sort2(s);
        System.out.println("After sort: " + Arrays.toString(s));
    }
}