package cn.pomit.springwork.netty.Command;
/*
指令操作
 */
public interface Command {
    public String handle(String content) throws Exception;
    String  LOGIN="login";
    String ZHUCE="zhuce";
    String MOVE="move";
    String AOR="aor";
    String TALK="talk";
    String ATTACK="attck";

}
