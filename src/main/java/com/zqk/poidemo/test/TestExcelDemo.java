package com.zqk.poidemo.test;

import com.zqk.poidemo.pojo.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:刘德安
 * @Date: 2021/1/11 10:37
 */
public class TestExcelDemo {

    public static void main(String[] args) {
        Student student =new Student();
        student.setName("XXX ");
        student.setResult("95");
        student.setClazz("二班");
        student.setGrade("五年级");
        student.setNumber("66");
        List<Student> list =new ArrayList<>();
        list.add(student);
        list.add(student);
        list.add(student);
        testExcelDemo(list);
    }
    /**
     *
     * @param list 需要写入excel的数据 从数据库或者其他途径读取
     */
    public static void testExcelDemo(List<Student> list) {
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = workBook.createSheet();
        // 表头
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("年级");
        row.createCell(3).setCellValue("班别");
        row.createCell(4).setCellValue("成绩");
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(list.get(i).getNumber());
            row.createCell(1).setCellValue(list.get(i).getName());
            row.createCell(2).setCellValue(list.get(i).getGrade());
            row.createCell(3).setCellValue(list.get(i).getClazz());
            row.createCell(4).setCellValue(list.get(i).getResult());
        }
        String filePath = "/Users/zhengqiku/github/poidemo/excel/";
        String fileName = "testExcelDemo.xlsx";
        File file = new File(filePath + fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            // 写入磁盘
            workBook.write(fos);
            fos.close();//记得关闭
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

