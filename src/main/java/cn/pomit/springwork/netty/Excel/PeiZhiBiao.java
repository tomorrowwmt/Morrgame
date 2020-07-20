package cn.pomit.springwork.netty.Excel;

import java.util.List;
import java.util.Objects;

public class PeiZhiBiao {
    static  ExcelUtil excelUtil=new ExcelUtil();
    public static List<List<String>> ditu() throws Exception {
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx")).getPath();
        return excelUtil.readXlsx(filepath);
    }
    public static List<List<String>> monster() throws Exception {
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Excel/Monster.xlsx")).getPath();
        return excelUtil.readXlsx(filepath);
    }
    public static List<List<String>> boss() throws Exception {
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Excel/Boss.xlsx")).getPath();
        return excelUtil.readXlsx(filepath);
    }
    public static List<List<String>> skill() throws Exception {
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Excel/Skill.xlsx")).getPath();
        return excelUtil.readXlsx(filepath);
    }
     /*
   public static  Map<String, Object> immutableMap;
    static {
        try {
            immutableMap = new ImmutableMap.Builder<String, Object>()
                    .put("Ditu",ditu())
                    .put("Monster",monster())
                    .put("Skill",skill()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  static String ditu() throws Exception {
        //地图
        String[] keys = new String[]{"mid","name","desc","neighbor","monsterStr"};
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx")).getPath();
        List<Map<String, Object>> maps = ExcelUtil.imp(filepath, keys);
        return maps.toString();
    }
    public  static String monster() throws Exception{
       String[] keys=new String[]{"id","name","hp"};
        String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Excel/Monster.xlsx")).getPath();
        List<Map<String, Object>> moster = ExcelUtil.imp(filepath, keys);
        return moster.toString();
    }
    public  static String skill() throws Exception{
       String[] keys=new String[]{"id","name","type","cd","mp"};
       String filepath= Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Excel/Skill.xlsx")).getPath();
        List<Map<String, Object>> skill = ExcelUtil.imp(filepath, keys);
        return skill.toString();
    }
    public static Object getpeizhiSevice(String key)
    {
        return  immutableMap.get(key);
    }
     */
}