package cn.pomit.springwork.netty.PlayHandler;

import cn.pomit.springwork.netty.Command.Command;
import cn.pomit.springwork.netty.Module.Module;
import cn.pomit.springwork.netty.annotation.SoCommand;
import cn.pomit.springwork.netty.annotation.SoModule;
import cn.pomit.springwork.netty.session.Session;
@SoModule(model = Module.Auth)
public interface UserHandler {
    @SoCommand(command = Command.LOGIN)
        //登陆
    String login(Session session,String data);
    @SoCommand(command = Command.ZHUCE)
        //注册
    String register(Session session, String data);
}
