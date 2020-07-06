package cn.pomit.springwork.netty.Command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CmdServcieFactory {
    static Map<String, Command> operates = new HashMap<String, Command>();
    static {
        operates.put("aoi",new Aoi());
    }
    public static  Command getCommandSevice(String  operateType){
        return  operates.get(operateType);
    }
}
