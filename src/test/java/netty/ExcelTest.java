package netty;

import cn.pomit.springwork.netty.Excel.ExcelReader;
import cn.pomit.springwork.netty.Monster.Monster;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelTest {
    @Test
    public void test() throws Exception {
        ExcelReader excelReader = new ExcelReader();
        List<List<String>> result1 = excelReader.readXlsx("src\\main\\resources\\Excel\\Monster.xlsx");
        //List<List<String>> result2 = excelReader.readXlsx("D:\\test\\Skill.xlsx");
        System.out.println(result1);
        /*
        URL l1=Thread.currentThread().getContextClassLoader().getResource("Excel/Monster.xlsx");
        System.out.println(l1);
        ClassLoader classLoader = ExcelTest.class.getClassLoader();
        URL resource = classLoader.getResource("Excel/Monster.xlsx");
        String path = resource.getPath();
        System.out.println(path);
        String path1 = ExcelTest.class.getClassLoader().getResource("Excel/Monster.xlsx").getPath();
        System.out.println(path1);
         */
    }
}
