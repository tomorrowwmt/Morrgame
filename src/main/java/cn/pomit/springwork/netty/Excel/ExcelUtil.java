package cn.pomit.springwork.netty.Excel;

import cn.pomit.springwork.netty.Ditu.Ditu;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ExcelUtil {
    /**
     * excel导入
     * @param keys		字段名称数组，如  ["id", "name", ... ]
     * @param filePath	文件物理地址
     */
    public static List<Map<String, Object>> imp(String filePath, String[] keys) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        if(null == keys) {
            throw new Exception("keys can not be null!");
        }
        if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
            throw new Exception("The file is not excel document!");
        }
        // 读取文件
        FileInputStream fis = null;
        Workbook wookbook = null;
        try {
            fis = new FileInputStream(filePath);
            if(filePath.endsWith(".xls")) {
                wookbook = new HSSFWorkbook(fis);
            } else if(filePath.endsWith(".xlsx")) {
                wookbook = new XSSFWorkbook(fis);
            }
            // 获取第一个工作表信息
            Sheet sheet = wookbook.getSheetAt(0);
            //获得数据的总行数
            int totalRowNum = sheet.getLastRowNum();
            // 获得表头
            Row rowHead = sheet.getRow(0);
            // 获得表头总列数
            int cols = rowHead.getPhysicalNumberOfCells();
            // 传入的key数组长度与表头长度不一致
            if(keys.length != cols) {
                throw new Exception("keys length does not match head row's cols!");
            }
            Row row = null;
            Cell cell = null;
            Object value = null;
            // 遍历所有行
            for (int i = 1; i <= totalRowNum; i++) {
                // 清空数据，避免遍历时读取上一次遍历数据
                row = null;
                cell = null;
                value = null;
               map = new HashMap<String, Object>();
                row = sheet.getRow(i);
                // 若该行第一列为空，则默认认为该行就是空行
                if(null == row) {
                    continue;
                }
                // 遍历该行所有列
                for (short j = 0; j < cols; j++) {
                    cell = row.getCell(j);
                    // 为空时，下一列
                    if(null == cell) {
                        continue;
                    }
                    // 根据poi返回的类型，做相应的get处理
                    if(CellType.STRING==cell.getCellTypeEnum()) {
                        value = cell.getStringCellValue();
                    } else if(CellType.NUMERIC ==cell.getCellTypeEnum()) {
                        value = cell.getNumericCellValue();
                        // 由于日期类型格式也被认为是数值型，此处判断是否是日期的格式，若时，则读取为日期类型
                        if(cell.getCellStyle().getDataFormat() > 0)  {
                            value = cell.getDateCellValue();
                        }
                    } else if(CellType.BOOLEAN== cell.getCellTypeEnum()){
                        value = cell.getBooleanCellValue();
                    } else if(CellType.BLANK==cell.getCellTypeEnum() ) {
                        value = cell.getDateCellValue();
                    } else {
                        throw new Exception("At row: %s, col: %s, can not discriminate type!");
                    }
                    map.put(keys[j], value);
                }

                list.add(map);
            }
        } catch (Exception e) {
            throw new Exception("analysis excel exception!", e);
        } finally {
            if(null != fis) {
                fis.close();
            }
        }

        return list;
    }
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
    public static void main(String[] args) throws Exception {
        //String filePath = "src\\main\\resources\\Excel\\Ditu.xlsx";
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        String[] keys = new String[]{"mid","mname","desc","neighbor","monsterStr"};
        List<Map<String, Object>> impList;
        try {
            impList = ExcelUtil.imp(filepath,keys);
            for (Map<String, Object> map : impList) {
                //System.out.println(map.get("name"));
                System.out.println(map.get("neighbor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filepath1=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        ExcelUtil excelUtil=new ExcelUtil();
        String s = excelUtil.readXlsx(filepath1).get(0).get(3);
        System.out.println(s);
    }

}
