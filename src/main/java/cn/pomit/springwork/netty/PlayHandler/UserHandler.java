package cn.pomit.springwork.netty.PlayHandler;

import cn.pomit.springwork.netty.Command.Command;
import cn.pomit.springwork.netty.Command.SoCommand;
import cn.pomit.springwork.netty.Command.SoModule;
import cn.pomit.springwork.netty.Command.Module;
import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.session.Session;

@SoModule(model = Module.Auth)
public interface UserHandler {
    @SoCommand(command= Command.LOGIN)
    //登陆
    ResultCode login(Session session,String data);
    //注册
    ResultCode register(Session session,String data);
}
