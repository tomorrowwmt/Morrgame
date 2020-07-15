package cn.pomit.springwork.netty.Excel;

import cn.pomit.springwork.netty.Ditu.Ditu;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PeiZhi {
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
    public static void main(String[] args) throws Exception {
        Object ditu1 = immutableMap.get("Ditu");
        String ditu = ditu();
        System.out.println(ditu);
    }

}
