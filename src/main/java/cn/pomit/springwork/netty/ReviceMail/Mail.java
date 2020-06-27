package cn.pomit.springwork.netty.ReviceMail;

import cn.pomit.springwork.netty.Entity.User;
import cn.pomit.springwork.netty.Entity.UserMail;
import cn.pomit.springwork.netty.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import cn.pomit.springwork.netty.mapper.UsermailMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class Mail {
    public void revicepersonMail(User user ,UserMail userMail){
        //先查询是否有个人邮件
        UsermailMapper usermailMapper =SpringUtil.getBean(UsermailMapper.class);
        //Example example=new Example(UserMail.class);
        //List<UserMail> userMails= usermailMapper.selectByExample(example);
        UserMail uM = usermailMapper.selectByPrimaryKey(1L);
        //查询后将邮件领取更新个人邮件状态
        if(uM.getMailId()!=null && uM.getStatus().equals(0)){
            userMail.setMailId(1L);
            userMail.setStatus(1);
            usermailMapper.updateByPrimaryKeySelective(userMail);
        } else if(uM.getAnnex().equals("金钱")){
            userMail.setMailId(1L);
            userMail.setRecevice(1);
            usermailMapper.updateByPrimaryKeySelective(userMail);
            //如果是金钱，给予奖励金币moeny200
            //查询用户金钱额度
            UserService userService =SpringUtil.getBean(UserService.class);
            Integer money = userService.queryById(1L).getMoney();
            money+=200;
            //更新
            user.setUid(1L);
            user.setMoney(money);
            userService.update(user);
        }
    }
}
