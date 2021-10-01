package com.zqk.poidemo.test;

import java.util.Comparator;

/**
 * @author zhengqiku
 * @date 2021/9/8
 */
public class StringComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if(SortTest.isNumeric(o1) && SortTest.isNumeric(o2)){
            return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
        }

        int len1 = o1.length();
        int len2 = o2.length();
        if(len1 != len2){
            return len1 - len2;
        }
        int i = 0;
        while(i < len1 && i < len2){
            char c1 = o1.charAt(i);
            char c2 = o2.charAt(i);
            if(c1 != c2){
                return c1 - c2;
            }
            i++;
        }

        return 0;
    }
}