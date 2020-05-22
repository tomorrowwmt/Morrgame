package cn.pomit.springwork.netty.Excel;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelReader {
    //需要解析的是xlsx文件
    public List<List<String>> readXlsx(String path) throws Exception{
        //获取文件输入流
        InputStream is = new FileInputStream(path);
        //获取读取的实例
        XSSFWorkbook xssfWorkbook=new XSSFWorkbook(is);
        //记录Excel结果的变量
        List<List<String>> result = new ArrayList<List<String>>();
        //遍历每一页
        //for(int index=0;index<hssfWorkbook.getNumberOfSheets();index++)
        //HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(index);
        for(int index=0;index<xssfWorkbook.getNumberOfSheets();index++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(index);
            if(xssfSheet == null) {
                continue;
            }
            //遍历每一行
            for(int row=1;row<=xssfSheet.getLastRowNum();row++) {
                XSSFRow xssfRow = xssfSheet.getRow(row);

                int minCol = xssfRow.getFirstCellNum();
                int maxCol = xssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                //遍历该行，获取每个celll元素
                for(int col = minCol;col<maxCol;col++) {
                    XSSFCell cell = xssfRow.getCell(col);
                    if(cell==null) {
                        continue;
                    }
                    rowList.add(cell.toString());
                }
                result.add(rowList);
            }
        }
        xssfWorkbook.close();
        return result;
    }

    //需要解析的是xls文件
    public List<List<String>> readXls(String path) throws Exception{
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook(is);
        List<List<String>> result = new ArrayList<List<String>>();
        for(int index=0;index<hssfWorkbook.getNumberOfSheets();index++) {
            HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(index);
            if(hssfSheet == null) {
                continue;
            }
            for(int row=1;row<=hssfSheet.getLastRowNum();row++) {
                HSSFRow hssfRow = hssfSheet.getRow(row);

                int minCol = hssfRow.getFirstCellNum();
                int maxCol = hssfRow.getLastCellNum();
                List<String> rowList = new ArrayList<String>();
                //遍历该行，获取每个celll元素
                for(int col = minCol;col<maxCol;col++) {
                    HSSFCell cell = hssfRow.getCell(col);
                    if(cell==null) {
                        continue;
                    }
                    rowList.add(cell.toString());
                }
                result.add(rowList);
            }
        }
        hssfWorkbook.close();
        return result;
    }


}
