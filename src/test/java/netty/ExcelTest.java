package netty;

import cn.pomit.springwork.netty.Excel.ExcelUtil;

import java.util.List;
import java.util.Map;

public class ExcelTest {
    public static void main(String[] args) throws Exception{
        //ExcelReader excelReader = new ExcelReader();
        //List<List<String>> result1 = excelReader.readXlsx("src\\main\\resources\\Excel\\Monster.xlsx");
        //List<List<String>> result2 = excelReader.readXlsx("D:\\test\\Skill.xlsx");
        //.out.println(result1);
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
        String[] keys = new String[]{"mid","name","neighbor"};
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        List<Map<String, Object>> maps = ExcelUtil.imp(filepath, keys);
        System.out.println(maps);
        // System.out.println(filepath);
        String[] key1=new String[]{"id","name","hp"};
        String filepath1=Thread.currentThread().getContextClassLoader().getResource("Excel/Monster.xlsx").getPath();
        List<Map<String, Object>> moster = ExcelUtil.imp(filepath1, key1);
        System.out.println(moster);
        String[] key2=new String[]{"id","mname","name","talk"};
        String filepath2=Thread.currentThread().getContextClassLoader().getResource("Excel/NPC.xlsx").getPath();
        List<Map<String, Object>> NPC = ExcelUtil.imp(filepath2, key2);
        System.out.println(NPC);
        String[] key3=new String[]{"id","name","type","cd","mp"};
        String filepath3=Thread.currentThread().getContextClassLoader().getResource("Excel/Skill.xlsx").getPath();
        List<Map<String, Object>> Skill= ExcelUtil.imp(filepath3, key3);
        System.out.println(Skill);

    }
}
