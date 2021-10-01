package com.zqk.poidemo.test;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author zhengqiku
 * @date 2021/9/8
 */
public class ListSortTest {
    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("AC");
        list.add("AD");
//        list.add("DA");
        list.add("k");
        list.add("AB");
        list.add("BE");
//        list.add("DB");
        list.add("YC");


        System.out.println("排序前：");
        print(list);

        list.sort(new StringComparator());

        System.out.println("排序后：");
        print(list);


        Map<Integer, Integer> map = isContinuity(list);
        map.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v));
    }

    private static Map<Integer, Integer> isContinuity(List<String> list) {
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        int start = 0;
        int end = 1;

        Map<Integer, Integer> map = new HashMap<>();
        for (int p1 = 0, p2 =1; p2 < list.size(); ) {
            String s1 = list.get(p1);
            String s2 = list.get(p2);

            int len1 = s1.length();
            int len2 = s2.length();
            if(len1 != len2){
                map.put(start, start);
                start++;
                end++;
            }else{
                int i = 0;
                Boolean isContinuity = false;
                while(i < s1.length()){
                    char c1 = s1.charAt(i);
                    char c2 = s2.charAt(i);
                    if(c1 == c2){
                        i++;
                    }else{
                        Integer s1Ascii = Integer.valueOf(c1);
                        Integer s2Ascii = Integer.valueOf(c2);
                        //TODO 处理 AD BE这种都是绝对值为1 的情况
                        if ((Math.abs(s1Ascii - s2Ascii) % 24) == 1) {
                            isContinuity = true;
                            i++;
                            continue;
                        }else{
                            isContinuity = false;
                            break;
                        }
                    }
                }
                if(isContinuity){
                    end++;
                }else {
                    map.put(start, end - 1);
                    start = end;
                    end = start + 1;
                }
            }
            if(end == list.size()){
                map.put(start, end - 1);
            }
            p1++;
            p2++;
        }
        return map;

    }

    public static void print(List<String> list){
        System.out.println(Arrays.asList(list.toArray()));
    }

}