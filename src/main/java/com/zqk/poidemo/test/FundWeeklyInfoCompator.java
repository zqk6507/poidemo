package com.zqk.poidemo.test;

import com.zqk.poidemo.pojo.FundWeeklyInfo;

import java.util.Comparator;

/**
 * @author zhengqiku
 * @date 2021/9/7
 */
public class FundWeeklyInfoCompator implements Comparator<FundWeeklyInfo> {
    @Override
    public int compare(FundWeeklyInfo o1, FundWeeklyInfo o2) {
        if(SortTest.isNumeric(o1.getShowName()) && SortTest.isNumeric(o2.getShowName())){
            return Integer.valueOf(o1.getShowName()).compareTo(Integer.valueOf(o2.getShowName()));
        }
        return o1.getShowName().compareTo(o2.getShowName());
    }
}