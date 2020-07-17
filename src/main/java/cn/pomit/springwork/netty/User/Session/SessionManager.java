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
	private static IdWorker WORKER=new IdWorker(1,1,1);
	/**
	 * 在线会话
	 */
	private static final Map<Long, Session> onlineSessions = new ConcurrentHashMap<>();
	private static  final Map<Long, Channel> userchannels=new ConcurrentHashMap<>();
	/**
	 * 加入
	 * @return
	 */
	public static boolean putSession(long uid, Session session){
		if(!onlineSessions.containsKey(uid)){
			return onlineSessions.putIfAbsent(uid, session) == null;
		}
		return false;
	}
	public  static  void add(Long uid, Channel channel){
		User user=new User();
		user.setUid(WORKER.nextId());
		userchannels.put(user.getUid(),channel);
	}
	public  static Channel getChanell(Long uid){
		return  userchannels.get(uid);
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
