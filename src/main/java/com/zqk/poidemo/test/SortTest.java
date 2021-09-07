package com.zqk.poidemo.test;

import com.zqk.poidemo.pojo.FundWeeklyInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhengqiku
 * @date 2021/9/7
 */
public class SortTest {

    public static void main(String[] args) {
        List<FundWeeklyInfo> list = getData();

        Map<String, List<FundWeeklyInfo>> listMap = list.stream().collect(Collectors.groupingBy(FundWeeklyInfo::getEstinateReturn));

        for(Map.Entry<String, List<FundWeeklyInfo>> entry: listMap.entrySet()){
            String key = entry.getKey();
            System.out.println("净值 = " + key);

            List<FundWeeklyInfo> subFund = entry.getValue();
            subFund.sort(new FundWeeklyInfoCompator());

            for(FundWeeklyInfo f: subFund){
                System.out.print(f.getShowName() + ",");
            }
            System.out.println();
            List<FundWeeklyInfo> resultSub = getMergeSub(subFund);
            for(FundWeeklyInfo f: resultSub){
                System.out.println("子基金：code=[" + f.getCode() + "] showName=[" +f.getShowNamePre() + f.getShowName() + f.getShowNameSuf() +"] 净值=["+f.getEstinateReturn()+"]");
            }

            System.out.println();
            System.out.println("=================");
        }
        System.out.println("结束");

    }

    private static List<FundWeeklyInfo> getMergeSub(List<FundWeeklyInfo> subFund) {
        if(CollectionUtils.isEmpty(subFund)){
            return null;
        }
        if(subFund.size() == 1 || subFund.size() == 2){
            return subFund;
        }
        int start = 0;
        int end = 1;

        //
        //1,2,3,5,6,7,9
        Map<Integer, Integer> map = new HashMap<>();
        for (int p1 = 0, p2=1; p2 < subFund.size();) {
            String s1 = subFund.get(p1).getShowName();
            String s2 = subFund.get(p2).getShowName();
                Integer s1Ascii = Integer.valueOf(s1.toCharArray()[0]);
                Integer s2Ascii = Integer.valueOf(s2.toCharArray()[0]);
                if(Math.abs(s1Ascii - s2Ascii) == 1){
                    end++;
                }else{
                    map.put(start, end -1);
                    start = end;
                    end = start + 1;
                }
                if(end == subFund.size()){
                    map.put(start, end -1);
                }
                p1++;
                p2++;
        }
        List<FundWeeklyInfo> result = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            Integer s1 = entry.getKey();
            Integer s2 = entry.getValue();

            FundWeeklyInfo fundWeeklyInfo = new FundWeeklyInfo();
            FundWeeklyInfo f1 = subFund.get(s1);
            BeanUtils.copyProperties(f1, fundWeeklyInfo);
            if(s1 != s2){
                FundWeeklyInfo f2 = subFund.get(s2);
                String s = f1.getShowName() + "-" + f2.getShowName();
                fundWeeklyInfo.setShowName(s);
            }

            result.add(fundWeeklyInfo);
        }

        return result;
    }

    public static boolean isNumeric(final CharSequence cs) {
        // 判断是否为空，如果为空则返回false
        if (StringUtils.isEmpty(cs)) {
            return false;
        }
        // 通过 length() 方法计算cs传入进来的字符串的长度，并将字符串长度存放到sz中
        final int sz = cs.length();
        // 通过字符串长度循环
        for (int i = 0; i < sz; i++) {
            // 判断每一个字符是否为数字，如果其中有一个字符不满足，则返回false
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        // 验证全部通过则返回true
        return true;
    }

    private static List<FundWeeklyInfo> getData() {
        List<FundWeeklyInfo> result = new ArrayList<>();
        result.add(new FundWeeklyInfo("code1","","A","期","1"));
        result.add(new FundWeeklyInfo("code2","","B","期","1"));
        result.add(new FundWeeklyInfo("code3","","C","期","1"));
        result.add(new FundWeeklyInfo("code4","","D","期","1"));
        result.add(new FundWeeklyInfo("code5","","E","期","1"));
        result.add(new FundWeeklyInfo("code6","","F","期","2"));
        result.add(new FundWeeklyInfo("code7","","G","期","2"));
        result.add(new FundWeeklyInfo("code8","","H","期","1"));
        result.add(new FundWeeklyInfo("code9","","I","期","1"));
        result.add(new FundWeeklyInfo("code10","","J","期","1"));
        result.add(new FundWeeklyInfo("code11","","K","期","3"));


        result.add(new FundWeeklyInfo("code21","","1","期","11"));
        result.add(new FundWeeklyInfo("code22","","2","期","11"));
        result.add(new FundWeeklyInfo("code23","","3","期","11"));
        result.add(new FundWeeklyInfo("code24","","4","期","12"));
        result.add(new FundWeeklyInfo("code25","","5","期","11"));
        result.add(new FundWeeklyInfo("code26","","6","期","14"));
        result.add(new FundWeeklyInfo("code27","","7","期","11"));
        result.add(new FundWeeklyInfo("code28","","9","期","11"));
        result.add(new FundWeeklyInfo("code29","","10","期","11"));
        return result;
    }
}