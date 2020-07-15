package cn.pomit.springwork.netty.Excel;

import java.util.List;

public class PeiZhiBiao {
    static  ExcelUtil excelUtil=new ExcelUtil();
    public static List<List<String>> ditu() throws Exception {
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        return excelUtil.readXlsx(filepath);
    }
    public static List<List<String>> monster() throws Exception {
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Monster.xlsx").getPath();
        return excelUtil.readXlsx(filepath);
    }
}