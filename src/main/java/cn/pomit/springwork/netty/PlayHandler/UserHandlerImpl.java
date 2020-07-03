package cn.pomit.springwork.netty.PlayHandler;

import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.request.LoginRequest;
import cn.pomit.springwork.netty.request.RegisterRequest;
import cn.pomit.springwork.netty.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
public class UserHandlerImpl implements  UserHandler{
    @Autowired
    UserService userService;
    @Override
    public String login(Session session, String data) {
            String result=null;
            LoginRequest loginRequest=new LoginRequest();
            //参数判空
            if(StringUtils.isEmpty(loginRequest.getUsername()) || StringUtils.isEmpty(loginRequest.getPassword())) {
                return null;
            }
            //执行任务
         result=userService.login(session,loginRequest.getUsername(),loginRequest.getPassword());
        return result;
    }

    @Override
    public String register(Session session, String data) {
        RegisterRequest registerRequest=new RegisterRequest();
        String result=null;
        //参数判空
        if(StringUtils.isEmpty(registerRequest.getUsername()) || StringUtils.isEmpty(registerRequest.getPassword())) {
            return null;
        }
        //执行任务
        result=userService.login(session,registerRequest.getUsername(),registerRequest.getPassword());
        return result;
    }
}
