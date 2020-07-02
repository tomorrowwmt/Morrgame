package cn.pomit.springwork.netty.Excel;

import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class PeiZhi {
    public static Map<String ,Object>jiazai( ) throws Exception {
        //地图
       String[] keys = new String[]{"mid","name","desc","neighbor","monsterStr"};
       String filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Ditu.xlsx").getPath();
        List<Map<String, Object>> maps = ExcelUtil.imp(filepath, keys);
        //Monster
         keys=new String[]{"id","name","hp"};
        filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Monster.xlsx").getPath();
        List<Map<String, Object>> moster = ExcelUtil.imp(filepath, keys);
        //SKill
        keys=new String[]{"id","name","type","cd","mp"};
         filepath=Thread.currentThread().getContextClassLoader().getResource("Excel/Skill.xlsx").getPath();
        List<Map<String, Object>> Skill= ExcelUtil.imp(filepath, keys);
        //输出全部
        //System.out.println("\n"+maps+"\n"+moster+"\n"+NPC+"\n"+Skill);
        Map<String, Object> immutableMap = new ImmutableMap.Builder<String, Object>()
                .put("Ditu", maps)
                .put("Skill", Skill)
                .put("Monster", moster).build();
        return immutableMap ;
    }

    public static void main(String[] args) throws Exception {
        Map<String,Object> m= jiazai();
        System.out.println("============================");
        System.out.println(m.get("NPC"));


    }

}
