package cn.pomit.springwork.netty.User.Session;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话管理者
 *
 */
public class SessionManager {

	/**
	 * 在线会话
	 */
	private static final Map<Long, Session> onlineSessions = new ConcurrentHashMap<>();
	
	/**
	 * 加入
	 * @return
	 */
	public static boolean putSession(long uid, Session session){
		if(!onlineSessions.containsKey(uid)){
			boolean success = onlineSessions.putIfAbsent(uid, session)== null? true : false;
			return success;
		}
		return false;
	}
	
	/**
	 * 移除
	 * @param
	 */
	public static Session removeSession(long uid){
		return onlineSessions.remove(uid);
	}
	
	/**
	 * 是否在线
	 * @param
	 * @return
	 */
	public static boolean isOnlinePlayer(long uid){
		return onlineSessions.containsKey(uid);
	}
	
	/**
	 * 获取所有在线玩家
	 * @return
	 */
	public static Set<Long> getOnlinePlayers() {
		return Collections.unmodifiableSet(onlineSessions.keySet());
	}
}
