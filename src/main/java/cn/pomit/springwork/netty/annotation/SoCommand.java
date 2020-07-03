package cn.pomit.springwork.netty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author DELL
 */ /*
做一个netty消息分发的操作
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SoCommand {
    /*
    请求命令号
     */
    String command();
}
