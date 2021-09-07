package com.zqk.poidemo.test;

import java.util.*;

/**
 * @author zhengqiku
 * @date 2021/9/7
 */
public class SortTest3 {

    public static void main(String[] args) {

        String[] array ={"b","a","c","a","1","3","E", "ab","ac","ab"};


        Arrays.sort(array,String.CASE_INSENSITIVE_ORDER);
        Set<String> str = new HashSet<String>();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            if(str.add(array[i])){
                list.add(array[i]);
            }
        }
        System.out.println("list"+list);
    }
}