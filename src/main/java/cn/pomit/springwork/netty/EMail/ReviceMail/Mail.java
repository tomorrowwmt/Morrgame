package cn.pomit.springwork.netty.EMail.ReviceMail;

import cn.pomit.springwork.netty.Bag.Entity.Bag;
import cn.pomit.springwork.netty.Mapper.BagMapper;
import cn.pomit.springwork.netty.EMail.Mapper.UsermailMapper;
import cn.pomit.springwork.netty.EMail.SysMail;
import cn.pomit.springwork.netty.Mapper.UserMapper;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.User.Entity.User;
import cn.pomit.springwork.netty.User.Entity.UserMail;
import cn.pomit.springwork.netty.User.Service.UserService;
import cn.pomit.springwork.netty.UtilSpring.SpringUtil;
import cn.pomit.springwork.netty.EMail.Mapper.SysmailMapper;

import java.util.List;

public class Mail {
    public void revicepersonMail(User user , UserMail userMail){
        //先查询是否有个人邮件
        UsermailMapper usermailMapper =SpringUtil.getBean(UsermailMapper.class);
        //Example example=new Example(UserMail.class);
        //List<UserMail> userMails= usermailMapper.selectByExample(example);
        UserMail uM = usermailMapper.selectByPrimaryKey(1L);
        //查询后将邮件领取更新个人邮件状态
        if(uM.getMailId()!=null && uM.getStatus().equals(0)){
            System.out.println(uM.getTitle());
            userMail.setMailId(1L);
            userMail.setStatus(1);
            usermailMapper.updateByPrimaryKeySelective(userMail);
        } else if("金钱".equals(uM.getAnnex())){
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
        }if("圣域令".equals(uM.getDaoju())){
            //发送道具插入背包当中
            System.out.println(uM.getDaoju());
            BagMapper bagMapper=SpringUtil.getBean(BagMapper.class);
            Bag bag=new Bag();
            IdWorker worker=new IdWorker(1,1,1);
            long l=worker.nextId();
            bag.setId(l);
            bag.setIname("圣域令");
            bag.setCapacity(10);
            bag.setCount(1);
            int insert = bagMapper.insertSelective(bag);
        }
    }
    public void SystemMail(User user, SysMail sysMail){
        //查询游戏中玩家有多少
        UserMapper UserMapper= SpringUtil.getBean(UserMapper.class);
        List<User> users =UserMapper.selectAll();
        //查询sysMial邮件title
        SysmailMapper sysmailMapper=SpringUtil.getBean(SysmailMapper.class);
        List<SysMail> sysMails = sysmailMapper.selectAll();
        System.out.println("邮件标题："+"\n"+sysMails.get(0).getTitle()+
                "邮件内容"+"\n"+sysMails.get(0).getContect());
        System.out.println("====================");
        System.out.println("邮件标题："+sysMails.get(1).getTitle()+ "邮件内容"+"\n"+sysMails.get(1).getContect());
        //用户读取系统邮件更改状态
        if(user.getUid()!=null){
            user.setUid(users.get(0).getUid());
            user.setStatus(1);
            UserMapper.updateByPrimaryKeySelective(user);
        }
    }

}
