package cn.pomit.springwork.netty.PlayHandler;

import cn.pomit.springwork.netty.DTO.ResultCode;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class UserHandlerImpl implements  UserHandler{
    @Autowired
    UserService userService;
    @Override
    public ResultCode login(Session session, String data) {
        return null;
    }

    @Override
    public ResultCode register(Session session, String data) {
        return null;
    }
}
