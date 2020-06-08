package netty;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Monster.Monster;
import org.junit.Test;

import java.util.List;

public class ExcelTest {
    @Test
    public void test() throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> result1 = excelReader.readXlsx("D:\\test\\Monster.xlsx");
        //List<List<String>> result2 = excelReader.readXlsx("D:\\test\\Skill.xlsx");
       // System.out.println(result2);
        Monster[] monsters = new Monster[3];
    }
}
