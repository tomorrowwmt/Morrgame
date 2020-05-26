package cn.pomit.springwork.netty.entity;

import cn.pomit.springwork.netty.Excel.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class Attack1 {
    public static void main(String[] args) throws Exception {
        User user =new User();
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> ret = excelReader.readXlsx("D:\\test\\Monster.xlsx");
    }
}
