package cn.pomit.springwork.netty.Command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CmdServcieFactory {
    static Map<String, Command> operates = new HashMap<String, Command>();
    static {
        operates.put("login",new Login());
        operates.put("register",new Register());
        operates.put("aoi",new Aoi());
        operates.put("move",new Move());
        operates.put("attack",new Attack());
        operates.put("fuben",new Fuben());
    }
    public static Command getCommandSevice(String  operateType)
    {
        return  operates.get(operateType);
    }

}
