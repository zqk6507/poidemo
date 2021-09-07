package com.zqk.poidemo.test;

import com.zqk.poidemo.pojo.Fund;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:刘德安
 * @Date: 2021/1/11 10:37
 */
public class TestFundExcelWithMutiDemo {

    public static void main(String[] args) {
        Fund fund =new Fund();
        fund.setProductCode("SGR588");
        fund.setProductName("1-2号");
        fund.setNetValue("0.9819");
        fund.setNetRate("-3.53%");
        fund.setHus("-3.57%");
        fund.setHengs("-6.78%");
        fund.setPosition("较高");

        Fund fund2 =new Fund();
        fund2.setProductCode("SLH094");
        fund2.setProductName("1期");
        fund2.setNetValue("1.5871");
        fund2.setNetRate("-5.79%");
        fund2.setHus("-3.57%");
        fund2.setHengs("-4.55%");
        fund2.setPosition("较高");

        List<Fund> list =new ArrayList<>();
        list.add(fund);
        list.add(fund2);
        testExcelDemo(list);
    }
    /**
     *
     * @param list 需要写入excel的数据 从数据库或者其他途径读取
     */
    public static void testExcelDemo(List<Fund> list) {
        /** 第一步，创建一个Workbook，对应一个Excel文件  */
        XSSFWorkbook wb = new XSSFWorkbook();
        /** 第二步，在Workbook中添加一个sheet,对应Excel文件中的sheet  */
        XSSFSheet sheet = wb.createSheet("excel导出标题");
        /** 第三步，设置样式以及字体样式*/
        XSSFCellStyle titleStyle = createTitleCellStyle(wb);
        XSSFCellStyle headerStyle = createHeadCellStyle(wb);
        XSSFCellStyle contentStyle = createContentCellStyle(wb);
        /** 第四步，创建标题 ,合并标题单元格 */
        // 行号
        int rowNum = 0;
        // 创建第一页的第一行，索引从0开始
        XSSFRow row0 = sheet.createRow(rowNum++);
        row0.setHeight((short) 800);// 设置行高

        String title = "高毅晓峰系列产品预估净值";
        XSSFCell c00 = row0.createCell(0);
        c00.setCellValue(title);
        c00.setCellStyle(titleStyle);
        // 合并单元格，参数依次为起始行，结束行，起始列，结束列 （索引0开始）
        String[] row_third = {"产品代码", "产品名称", "本周预估净值","本周预估收益率", "同期沪深300", "同期恒生国企指数","目前仓位"};
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, row_third.length - 1));//标题合并单元格操作，6为总列数

        XSSFRow row1 = sheet.createRow(rowNum++);
        XSSFCell cell = row1.createCell(0);
        cell.setCellValue("截止日期:");
        cell.setCellStyle(headerStyle);
        XSSFCell cell1 = row1.createCell(1);
        cell1.setCellValue("2021/9/1");
        cell1.setCellStyle(headerStyle);
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, row_third.length - 1));
        //第二行
        XSSFRow row2 = sheet.createRow(rowNum++);
        row2.setHeight((short) 700);
        for (int i = 0; i < row_third.length; i++) {
            XSSFCell tempCell = row2.createCell(i);
            tempCell.setCellValue(row_third[i]);
            tempCell.setCellStyle(headerStyle);
        }
        for (Fund fund : list) {
            XSSFRow tempRow = sheet.createRow(rowNum++);
            tempRow.setHeight((short) 500);
            // 循环单元格填入数据
            for (int j = 0; j < row_third.length; j++) {
                XSSFCell tempCell = tempRow.createCell(j);

                contentStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());//背景颜色
                tempCell.setCellStyle(contentStyle);
                String tempValue = "";
                if (j == 0) {
                    // 学号
                    tempValue = fund.getProductCode();
                } else if (j == 1) {
                    // 姓名
                    tempValue = fund.getProductName();
                } else if (j == 2) {
                    // 年级
                    tempValue = fund.getNetValue();
                } else if (j == 3) {
                    // 班级
                    tempValue = fund.getNetRate();
                } else if (j == 4) {
                    // 成绩
                    tempValue = fund.getHus();
                }else if (j == 5) {
                    // 成绩
                    tempValue = fund.getHengs();
                }else if (j == 6) {
                    // 成绩
                    tempValue = fund.getPosition();
                }
                tempCell.setCellValue(tempValue);
            }
        }
        String filePath = "/Users/zhengqiku/github/poidemo/excel/";
        String fileName = "testExcelDemo2.xlsx";
        File file = new File(filePath + fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            // 写入磁盘
            wb.write(fos);
            fos.close();//记得关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 创建标题样式
     * @param wb
     * @return
     */
    private static XSSFCellStyle createTitleCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直对齐
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//背景颜色与这个搭配使用
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());//背景颜色

        XSSFFont headerFont1 = (XSSFFont) wb.createFont(); // 创建字体样式
        headerFont1.setBold(true); //字体加粗
        headerFont1.setFontName("黑体"); // 设置字体类型
        headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
        cellStyle.setFont(headerFont1); // 为标题样式设置字体样式
        return cellStyle;
    }

    /**
     * 创建表头样式
     * @param wb
     * @return
     */
    private static XSSFCellStyle createHeadCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);// 设置自动换行
        cellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());//背景颜色
        cellStyle.setAlignment(HorizontalAlignment.CENTER); //水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //垂直对齐
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
//        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
//        cellStyle.setBorderLeft(BorderStyle.THIN); //左边框
//        cellStyle.setBorderRight(BorderStyle.THIN); //右边框
//        cellStyle.setBorderTop(BorderStyle.THIN); //上边框

        XSSFFont headerFont = (XSSFFont) wb.createFont(); // 创建字体样式
        headerFont.setBold(true); //字体加粗
        headerFont.setFontName("黑体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 12); // 设置字体大小
        cellStyle.setFont(headerFont); // 为标题样式设置字体样式

        return cellStyle;
    }

    /**
     * 创建内容样式
     * @param wb
     * @return
     */
    private static XSSFCellStyle createContentCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        cellStyle.setWrapText(true);// 设置自动换行
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN); //左边框
        cellStyle.setBorderRight(BorderStyle.THIN); //右边框
        cellStyle.setBorderTop(BorderStyle.THIN); //上边框

        // 生成12号字体
        XSSFFont font = wb.createFont();
        font.setColor((short)8);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);

        return cellStyle;
    }
}

