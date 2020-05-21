package cn.pomit.springwork.netty.mapper;


import cn.pomit.springwork.netty.entity.Command;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommandMapper {
    /*
    查询所有命令
     */
    @Select("select * from tb_command")
    List<Command> querycommand();
}
