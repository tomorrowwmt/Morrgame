package cn.pomit.springwork.netty.Excel;

import cn.pomit.springwork.netty.Ditu.Ditu;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static void main(String[] args) {
        //String filePath = "src\\main\\resources\\Excel\\Ditu.xlsx";
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        String[] keys = new String[]{"mid","name","desc","neighbor","monsterStr"};
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
    }

}
