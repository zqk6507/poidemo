package com.zqk.poidemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengqiku
 * @date 2021/9/20
 */
public class NumTest {

    public static void main(String[] args) {
        System.out.println("version1");
        Integer a = 1;
        changeValue(a);
        System.out.println("a = " + a);

        List<String> list = new ArrayList<String>();
        list.add("1");
        System.out.println("list = " + list);
        changeList(list);
        System.out.println("list = " + list);

        Integer b = 200;
        Integer b1 = Integer.valueOf(200);
        System.out.println(b == b1);

        String str = "123";
        channelStr(str);
        System.out.println(str);
    }

    private static void channelStr(String str) {
        str = "456";
    }

    private static void changeList(List<String> list) {
        list.add("2");
    }

    public static void changeValue(Integer a){
        a = 10;
    }
}