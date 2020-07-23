package cn.pomit.springwork.netty.User.Session;
import cn.pomit.springwork.netty.Twitter.IdWorker;
import cn.pomit.springwork.netty.User.Entity.User;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话管理者
 *
 */
@Component
public class SessionManager {
	private static final IdWorker WORKER=new IdWorker(1,1,1);
	/**
	 * 在线会话
	 */
	private static final Map<Long , Session> ONLINE_SESSIONS = new ConcurrentHashMap<>();
	private static  final Map<User, Channel> USERCHANNELS =new ConcurrentHashMap<>();
	/**
	 * 加入
	 * @return
	 */
	public static boolean putSession(long uid, Session session){
		if(!ONLINE_SESSIONS.containsKey(uid)){
			return ONLINE_SESSIONS.putIfAbsent(uid, session) == null;
		}
		return false;
	}
	public  static  void add(User user, Channel channel){
		user.setUid(WORKER.nextId());
		USERCHANNELS.put(user,channel);
	}
	/**
	 * 移除
	 * @param
	 */
	public static Session removeSession(Long uid){
		return  ONLINE_SESSIONS.remove(uid);
	}
	
	/**
	 * 是否在线
	 * @param
	 * @return
	 */
	public static boolean isOnlinePlayer(Long uid){
		return ONLINE_SESSIONS.containsKey(WORKER.nextId());
	}

}
