package cn.pomit.springwork.netty.DTO;

public interface ResultCode {
	
	/**
	 * 成功
	 */
	int SUCCESS = 0;
	
	/**
	 * 找不到命令
	 */
	int NO_INVOKER = 1;
	/**
	 * 未知异常
	 */
	int UNKOWN_EXCEPTION = 2;

	/**
	 * 玩家名已使用
	 */
	int PLAYER_EXIST = 3;
	
	/**
	 * 玩家不存在
	 */
	int PLAYER_NO_EXIST = 4;
	
	/**
	 * 密码错误
	 */
	int PASSWARD_ERROR = 5;
	
	/**
	 * 登录失败
	 */
	int LOGIN_FAIL = 6;
	
	/**
	 * 玩家不在线
	 */
	int PLAYER_NO_ONLINE = 7;
	
	/**
	 * 请先登录
	 */
	int LOGIN_PLEASE = 8;
	//移动成功
	int MOVE_SUCESS=9;
}
