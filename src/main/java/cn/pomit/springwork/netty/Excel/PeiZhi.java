package cn.pomit.springwork.netty.Excel;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public class PeiZhi {
    public static  void jiazai() throws Exception {
        //地图
        String[]  keys = new String[]{"mid","name","neighbor"};
        String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        List<Map<String, Object>> maps = ExcelUtil.imp(filepath, keys);
        //Monster
        String[] key1=new String[]{"id","name","hp"};
        String filepath1=Thread.currentThread().getContextClassLoader().getResource("Excel/Monster.xlsx").getPath();
        List<Map<String, Object>> moster = ExcelUtil.imp(filepath1, key1);
        //NPC
        String[] key2=new String[]{"id","mname","name","talk"};
        String filepath2=Thread.currentThread().getContextClassLoader().getResource("Excel/NPC.xlsx").getPath();
        List<Map<String, Object>> NPC = ExcelUtil.imp(filepath2, key2);
        //SKill
        String[] key3=new String[]{"id","name","type","cd","mp"};
        String filepath3=Thread.currentThread().getContextClassLoader().getResource("Excel/Skill.xlsx").getPath();
        List<Map<String, Object>> Skill= ExcelUtil.imp(filepath3, key3);
        //输出全部
        System.out.println("\n"+maps+"\n"+moster+"\n"+NPC+"\n"+Skill);
    }

}
