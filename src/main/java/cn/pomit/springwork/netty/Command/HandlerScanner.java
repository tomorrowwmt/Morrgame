package cn.pomit.springwork.netty.Command;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class HandlerScanner implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<? extends Object> clazz = bean.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        if(interfaces != null && interfaces.length > 0){
            //扫描类的所有接口父类
            for (Class<?> interFace : interfaces) {
                //判断是否为Handler接口类
                SoModule hutuModule = interFace.getAnnotation(SoModule.class);
                if (hutuModule == null) {
                    continue;
                }
                //找出命令方法
                Method[] methods=interFace.getMethods();
                if(methods != null && methods.length > 0){
                    for(Method method : methods){
                        SoCommand hutuCommand = method.getAnnotation(SoCommand.class);
                        if(hutuCommand == null){
                            continue;
                        }

                        final int module = hutuModule.model();
                        final String cmd = hutuCommand.command();
                        if(InvokerManager.getInvoker(module, cmd) == null){
                            InvokerManager.addInvoker(module, cmd, Invoker.valueOf(method, bean));
                        }else{
                            System.out.println("重复命令"+cmd);
                        }
                    }
                }

            }
        }
        return bean;
    }
}
