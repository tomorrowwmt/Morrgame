package cn.pomit.springwork.netty.InVoker;

import cn.pomit.springwork.netty.InVoker.Invoker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
/*
 * @Description:命令执行器管理者
 */
@Component
public class InvokerManager {
    /**命令调用器*/
    private static Map<Integer, Map<String, Invoker>> invokers = new HashMap<>();

    /**
     * 添加命令调用
     * @param module
     * @param cmd
     * @param invoker
     */
    public static void addInvoker(int module, String cmd, Invoker invoker){
        Map<String, Invoker> map = invokers.get(module);
        if(map == null){
            map = new HashMap<>();
            invokers.put(module, map);
        }
        map.put(cmd,invoker);
    }


    /**
     * 获取命令调用
     * @param module
     * @param cmd
     */
    public static Invoker getInvoker(int module, String cmd){
        Map<String, Invoker> map = invokers.get(module);
        if(map != null){
            return map.get(cmd);
        }
        return null;
    }

}

