package cn.pomit.springwork.netty.InVoker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * 命令执行器
 */
@Slf4j
public class Invoker {

    /**
     * 方法
     */
    private Method method;

    /**
     * 目标对象
     */
    private Object target;

    public static Invoker valueOf(Method method, Object target) {
        Invoker invoker = new Invoker();
        invoker.setMethod(method);
        invoker.setTarget(target);
        return invoker;
    }

    /**
     * @description 执行
     * @param paramValues
     * @return Object
     */
    public Object invoke(Object... paramValues) {
        try {
            return method.invoke(target, paramValues);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            log.error("", e);
        }
        return null;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
