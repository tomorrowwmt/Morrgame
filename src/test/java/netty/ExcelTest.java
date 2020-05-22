package netty;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import org.junit.Test;

import java.util.List;

public class ExcelTest {
    @Test
    public void test() throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> result = excelReader.readXlsx("D:\\test\\ditu.xlsx");
       //List<List<String>> result1 = excelReader.readXlsx("D:\\test\\hh.xlsx");
        System.out.println(result.toString());
    }
}
