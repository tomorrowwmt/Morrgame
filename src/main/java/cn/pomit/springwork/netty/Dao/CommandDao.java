package cn.pomit.springwork.netty.Dao;


import cn.pomit.springwork.netty.entity.Command;

import java.util.List;

public interface CommandDao {
    /*
    查询所有命令
     */
    List<Command> querycommand();
}
