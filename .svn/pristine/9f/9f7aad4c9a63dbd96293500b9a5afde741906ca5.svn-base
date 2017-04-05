package org.com.zrhx.mina.service;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.com.zrhx.mina.base.ServerConfig;
import org.com.zrhx.mina.eneity.Client;
import org.com.zrhx.mina.eneity.DataFrame;
/**
 * 作为线程运行，负责接受来自客户的请求
 * @author gs
 *
 */
public class MinaAcceptHander implements IoHandler {

	private Logger logger = Logger.getLogger(MinaAcceptHander.class);
	
	private ServerConfig config;
	
	public MinaAcceptHander(ServerConfig config) {
		this.config  = config;
	}
	/**
	 * 捕获异常时
	 */
	public void exceptionCaught(IoSession session, Throwable e)
			throws Exception {
		e.printStackTrace();
		session.close(true);
		logger.error(e.getMessage(), e);
	}
    /**
     * 接受到数据时
     */
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		DataFrame m = (DataFrame) message;
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String clientIP = inetSocketAddress.getAddress().getHostAddress();
////		c.getId();//由于它不保证唯一性的关键
		int port = inetSocketAddress.getPort();
		m.setIpport(clientIP+port);
		if (m.getMessageType() ==0x1007) {
			logger.info(clientIP+":"+port+"接收到客户端心跳包………………");
		} else {
			logger.info(clientIP+":"+port+"接收到客户端数据包………………");
			config.getReceivedHandler().onReceived(m);
		}
		session.setAttribute("receivetime", System.currentTimeMillis());
	}
    /**
     * 消息调用IoSession.write写的(对象)
     */
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("消息调用IoSession.write写的(对象)");
	}
	/**
	 * 在关闭连接时调用，包括自己关闭和客户端关闭
	 */
	public void sessionClosed(IoSession session) throws Exception {
		/*表明客户端已下线，某些地方需要验证，所以记录下来*/
//		session.setAttribute("islive", false);	
		config.getAcceptHandler().lostConnection(session);
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String clientIP = inetSocketAddress.getAddress().getHostAddress();
		int port = inetSocketAddress.getPort();
		logger.info(clientIP+":"+port+"关闭连接");
	}
    /**
     * session创建时
     */
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("session创建");
	}
	/**
	 * 当设置了idletime时，会定时调用该方法
	 */
	public void sessionIdle(IoSession session, IdleStatus arg1)
			throws Exception {
		Long lastrecesivetime = (Long) session.getAttribute("receivetime");
		Long nowtime = System.currentTimeMillis();
		if(null==lastrecesivetime||(nowtime-lastrecesivetime)>config.getTimeOut()*1000){
			session.close(true);
			logger.error("连接超时......"+session.isConnected()+":"+session.isClosing());
		}

	}
    /**
     * 
     */
	public void sessionOpened(final IoSession session) throws Exception {
		InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
		String clientIP = inetSocketAddress.getAddress().getHostAddress();
//		c.getId();//由于它不保证唯一性的关键
		int port = inetSocketAddress.getPort();
		Client client = new Client(clientIP+port, session);
		config.getAcceptHandler().accept(client);
		logger.info(clientIP+":"+port+"连接建立成功");
	}
	/**
	 * 
	 */
	@Override
	public void inputClosed(IoSession session) throws Exception {
		session.close(true);
		logger.error("inputClosed");
		
	}

}
